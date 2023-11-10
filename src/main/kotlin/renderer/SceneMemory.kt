package renderer

import environment.outdoors.classes.Outdoor
import renderer.classes.Model
import renderer.classes.Texture
import renderer.classes.TexturedModel
import renderer.computations.PerspectiveConversions

object SceneMemory {
    //--------------------------------ПЕРСПЕКТИВНЫЕ ПРЕОБРАЗОВАНИЯ----------------------------------

    var PerspectiveConversions: PerspectiveConversions? = null

    //---------------------------------------НАЗВАНИЕ КАРТЫ-----------------------------------------
    @JvmField
    var mapName: String? = null

    //------------------------------------НАБОР ОБЪЕКТОВ СЦЕНЫ--------------------------------------
    @JvmField
    var texturedModels: ArrayList<TexturedModel>? = null

    //-----------------------------------------ТЕКСТУРЫ---------------------------------------------
    @JvmField
    var textures: ArrayList<Texture>? = null

    //------------------------------------------МОДЕЛИ----------------------------------------------
    @JvmField
    var models: ArrayList<Model>? = null

    //-------------------------------------------ДОМ------------------------------------------------
    @JvmField
    var outdoor: Outdoor? = null

    var currentFloor = 1
}
