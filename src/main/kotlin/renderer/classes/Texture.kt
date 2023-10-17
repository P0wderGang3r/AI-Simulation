package renderer.classes

import java.nio.file.Files
import java.nio.file.Paths

class Texture(
    textureName: String, pathToTexture: String?,
    val width: Int, val height: Int
) {
    var numInMemory: Int = 0
    val textureName: String
    private lateinit var pixels: FloatArray


    private var lastCorner = 0.5.toFloat()
    private val corners = Array(2) {
        Array(3) {
            FloatArray(
                2
            )
        }
    }


    //---------------------Get-теры------------------------
    fun getPixels(): FloatArray {
        return pixels
    }

    fun getNextCorners(): Array<FloatArray> {
        lastCorner = -lastCorner
        return corners[(0.5 + lastCorner).toInt()]
    }

    //---------------------Остальное-----------------------
    private fun setPixel(pixels: FloatArray, i: Int, j: Int, r: Float, g: Float, b: Float) {
        pixels[3 * i + 3 * height * j] = r
        pixels[3 * i + 3 * height * j + 1] = g
        pixels[3 * i + 3 * height * j + 2] = b
    }

    private fun initFirstCorners() {
        corners[0][0][0] = 0.0f
        corners[0][0][1] = 1.0f
        corners[0][1][0] = 0.0f
        corners[0][1][1] = 0.0f
        corners[0][2][0] = 1.0f
        corners[0][2][1] = 0.0f
    }


    private fun initSecondCorners() {
        corners[1][0][0] = 1.0f
        corners[1][0][1] = 0.0f
        corners[1][1][0] = 1.0f
        corners[1][1][1] = 1.0f
        corners[1][2][0] = 0.0f
        corners[1][2][1] = 1.0f
    }


    private fun rawPixelReader(pathToTexture: String?) {
        pixels = FloatArray(width * height * 3)
        val bytes: ByteArray

        try {
            println(pathToTexture)
            bytes = Files.readAllBytes(Paths.get(pathToTexture!!))
            for (j in 0 until height) {
                for (i in 0 until width) {
                    setPixel(
                        pixels, i, j,
                        (bytes[3 * i + 3 * height * j].toUByte()).toFloat() / 255f,
                        (bytes[3 * i + 3 * height * j + 1].toUByte()).toFloat() / 255f,
                        (bytes[3 * i + 3 * height * j + 2].toUByte()).toFloat() / 255f
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            var j = 0
            while (j < height) {
                var i = 0
                while (i < width) {
                    setPixel(pixels, j, i, 1f, 1f, 1f)
                    i++
                }
                j++
            }
        }
    }


    init {
        initFirstCorners()
        initSecondCorners()

        this.textureName = textureName
        rawPixelReader(pathToTexture)
    }
}