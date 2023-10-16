package renderer.primitives

class Texture(textureName: String, pathToTexture: String?) {
    val textureName: String

    //---------------------Остальное-----------------------
    //private final Bitmap bitmap;
    var numInMemory = 0
    private var lastCorner = 0.5.toFloat()
    private val corners = Array(2) { Array(3) { FloatArray(2) } }
    val nextCorners: Array<FloatArray>
        //---------------------Get-теры------------------------
        get() {
            lastCorner = -lastCorner
            return corners[(0.5 + lastCorner).toInt()]
        }

    private fun initFirstCorners() {
        corners[0][0][0] = 0f
        corners[0][0][1] = 1f
        corners[0][1][0] = 0f
        corners[0][1][1] = 0f
        corners[0][2][0] = 1f
        corners[0][2][1] = 0f
    }

    private fun initSecondCorners() {
        corners[1][0][0] = 1f
        corners[1][0][1] = 0f
        corners[1][1][0] = 1f
        corners[1][1][1] = 1f
        corners[1][2][0] = 0f
        corners[1][2][1] = 1f
    }

    init {
        initFirstCorners()
        initSecondCorners()
        this.textureName = textureName

        /*
        int id = SceneGlobals.getContext().getResources()
                .getIdentifier(pathToTexture, null, SceneGlobals.getContext()
                        .getPackageName());
        this.bitmap = BitmapFactory.decodeResource(SceneGlobals.getContext().getResources(), id);
                 */
    }
}