package renderer

import renderer.computations.MapTransformations

//-5 5 90 #near_far_fov
object SceneGlobals {
    //----------------------------------ТЕКУЩИЙ ПОВОРОТ СЦЕНЫ---------------------------------------

    var rotationStatus = floatArrayOf(0f, 0f, 0f)

    //----------------------------------ТЕКУЩЕЕ СМЕЩЕНИЕ СЦЕНЫ--------------------------------------

    var positionStatus = floatArrayOf(0f, 0f, 0f)

    //----------------------------------ТЕКУЩИЙ РАЗМЕР СЦЕНЫ----------------------------------------

    var mapSizeStatus = 0.5f

    //-----------------------------------ТЕКУЩИЙ ЦВЕТ СЦЕНЫ-----------------------------------------

    var sceneColorStatus = floatArrayOf(0f, 0f, 0f)

    val sceneColorR: Float
        get() = sceneColorStatus[0]

    val sceneColorG: Float
        get() = sceneColorStatus[1]

    val sceneColorB: Float
        get() = sceneColorStatus[2]

    //-----------------------------------ТЕКСТУРЫ В ПАМЯТИ------------------------------------------
    lateinit var oglTextures: IntArray
}
