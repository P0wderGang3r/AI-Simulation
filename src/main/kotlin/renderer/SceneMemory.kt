package renderer

import environment.backrooms.classes.House
import environment.outdoors.classes.Outdoor
import renderer.classes.ComplexModel
import renderer.classes.Model
import renderer.classes.Texture

object SceneMemory {
    //---------------------------------------НАЗВАНИЕ КАРТЫ-----------------------------------------
    @JvmField
    var mapName: String? = null

    //------------------------------------НАБОР ОБЪЕКТОВ СЦЕНЫ--------------------------------------
    @JvmField
    var complexModels: ArrayList<ComplexModel>? = null

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
