package renderer.initializers

import org.lwjgl.opengl.GL20
import renderer.SceneGlobals
import renderer.SceneMemory

object BindTextures {
    fun bind() {
        SceneGlobals.oglTextures = IntArray(SceneMemory.textures!!.size)
        GL20.glGenTextures(SceneGlobals.oglTextures)
        for (index in SceneMemory.textures!!.indices) {
            GL20.glBindTexture(GL20.GL_TEXTURE_2D, SceneGlobals.oglTextures[index])

            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL20.GL_CLAMP_TO_EDGE)
            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_EDGE)

            //GL_LINEAR
            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_NEAREST)
            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_NEAREST)

            GL20.glTexImage2D(
                GL20.GL_TEXTURE_2D,
                0,
                GL20.GL_RGB,
                SceneMemory.textures!![index].width,
                SceneMemory.textures!![index].height,
                0,
                GL20.GL_RGB,
                GL20.GL_FLOAT,
                SceneMemory.textures!![index].getPixels()
            )

            SceneMemory.textures!![index].numInMemory = index
            GL20.glBindTexture(GL20.GL_TEXTURE_2D, 0)
        }
    }
}
