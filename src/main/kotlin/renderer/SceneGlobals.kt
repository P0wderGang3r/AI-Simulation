package renderer

//-5 5 90 #near_far_fov
object SceneGlobals {
    //----------------------------------ТЕКУЩИЙ ПОВОРОТ СЦЕНЫ---------------------------------------
    @JvmField
    var rotationStatus = floatArrayOf(0f, 0f, 0f)

    //----------------------------------ТЕКУЩЕЕ СМЕЩЕНИЕ СЦЕНЫ--------------------------------------
    @JvmField
    var positionStatus = floatArrayOf(0f, 0f, 0f)

    //----------------------------------ТЕКУЩИЙ РАЗМЕР СЦЕНЫ----------------------------------------
    @JvmField
    var mapSizeStatus = 0.5f

    //-----------------------------------ТЕКУЩИЙ ЦВЕТ СЦЕНЫ-----------------------------------------
    @JvmField
    var sceneColorStatus = floatArrayOf(0f, 0f, 0f)
    @JvmStatic
    val sceneColorR: Float
        get() = sceneColorStatus[0]
    @JvmStatic
    val sceneColorG: Float
        get() = sceneColorStatus[0]
    @JvmStatic
    val sceneColorB: Float
        get() = sceneColorStatus[0]

    //-----------------------------------ТЕКСТУРЫ В ПАМЯТИ------------------------------------------
    lateinit var oglTextures: IntArray
}
