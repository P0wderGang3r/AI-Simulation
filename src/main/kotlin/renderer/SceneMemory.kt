package renderer

import renderer.primitives.ComplexModel
import renderer.primitives.Model
import renderer.primitives.Texture

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
}
