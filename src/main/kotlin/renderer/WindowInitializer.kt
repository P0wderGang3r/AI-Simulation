package renderer

import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL20
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import renderer.SceneGlobals.rotationStatus
import renderer.initializers.SceneController
import runtime.Runtime
import java.lang.Thread.sleep
import kotlin.math.tan

class WindowInitializer (
    val runtime: Runtime
) {
    // The window handle
    private var window: Long = 0
    fun run() {
        println("LWJGL: " + Version.getVersion())
        init()
        loop()

        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(window)
        GLFW.glfwDestroyWindow(window)

        // Terminate GLFW and free the error callback
        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)!!.free()
    }

    fun floorEdit(delta: Int) {
        if (SceneMemory.currentFloor + delta > 0)
            if (SceneMemory.currentFloor + delta < 3)
                SceneMemory.currentFloor += delta
    }

    fun rotate(rotationStatus: Float, delta: Float): Float {
        if (rotationStatus + delta > 1.6)
            if (rotationStatus + delta < 2.4)
                return rotationStatus + delta

        return rotationStatus
    }

    private fun init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set()

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        check(GLFW.glfwInit()) { "Unable to initialize GLFW" }

        // Configure GLFW
        GLFW.glfwDefaultWindowHints() // optional, the current window hints are already the default
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE) // the window will stay hidden after creation
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE) // the window will be resizable

        // Create the window
        window = GLFW.glfwCreateWindow(1280, 720, "AI_Lab", MemoryUtil.NULL, MemoryUtil.NULL)
        if (window == MemoryUtil.NULL) throw RuntimeException("Failed to create the GLFW window")

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        GLFW.glfwSetKeyCallback(window) {
            window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) GLFW.glfwSetWindowShouldClose(window, true)

            if (key == GLFW.GLFW_KEY_W && action == GLFW.GLFW_REPEAT)
                rotationStatus[2] = rotate(rotationStatus[2], -0.05f)
            if (key == GLFW.GLFW_KEY_S && action == GLFW.GLFW_REPEAT)
                rotationStatus[2] = rotate(rotationStatus[2], 0.05f)
            if (key == GLFW.GLFW_KEY_A && action == GLFW.GLFW_REPEAT) rotationStatus[0] -= 0.05f
            if (key == GLFW.GLFW_KEY_D && action == GLFW.GLFW_REPEAT) rotationStatus[0] += 0.05f

            if (key == GLFW.GLFW_KEY_UP && action == GLFW.GLFW_RELEASE) floorEdit(1)
            if (key == GLFW.GLFW_KEY_DOWN && action == GLFW.GLFW_RELEASE) floorEdit(-1)

            if (key == GLFW.GLFW_KEY_R && action == GLFW.GLFW_RELEASE) runtime.requestRun()
            if (key == GLFW.GLFW_KEY_P && action == GLFW.GLFW_RELEASE) runtime.requestPause()
            if (key == GLFW.GLFW_KEY_EQUAL && action == GLFW.GLFW_RELEASE)
                runtime.getSimParams().setSimTickDelay(runtime.getSimParams().getSimTickDelay() * 2)
            if (key == GLFW.GLFW_KEY_MINUS && action == GLFW.GLFW_RELEASE)
                    runtime.getSimParams().setSimTickDelay(runtime.getSimParams().getSimTickDelay() / 2)
        }
        MemoryStack.stackPush().use { stack ->
            val pWidth = stack.mallocInt(1) // int*
            val pHeight = stack.mallocInt(1) // int*

            // Get the window size passed to glfwCreateWindow
            GLFW.glfwGetWindowSize(window, pWidth, pHeight)

            // Get the resolution of the primary monitor
            val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())

            // Center the window
            GLFW.glfwSetWindowPos(
                window,
                (vidmode!!.width() - pWidth[0]) / 2,
                (vidmode.height() - pHeight[0]) / 2
            )
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window)
        // Enable v-sync
        GLFW.glfwSwapInterval(1)

        // Make the window visible
        GLFW.glfwShowWindow(window)
    }


    private fun loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities()
        SceneController.mapInitialize("default_outdoor")

        println(SceneGlobals.sceneColorR)
        // Set the clear color
        GL20.glClearColor(
            SceneGlobals.sceneColorR,
            SceneGlobals.sceneColorG,
            SceneGlobals.sceneColorB,
            1f
        )

        while (SceneMemory.outdoor == null)
            sleep(10)

        GL20.glMatrixMode(GL20.GL_PROJECTION)
        GL20.glEnable(GL20.GL_DEPTH_TEST)
        GL20.glDepthFunc(GL20.GL_LESS)

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL20.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT) // clear the framebuffer

            SceneLoop.renderer()

            GLFW.glfwSwapBuffers(window) // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            GLFW.glfwPollEvents()
        }

        runtime.requestStop()
    }
}
