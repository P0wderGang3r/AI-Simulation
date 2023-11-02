package renderer.initializers

import renderer.SceneMemory

object SceneController {
    //Управление созданием сцены через выбор карты
    fun mapInitialize(mapName: String?) {
        println("data/maps/${mapName}")
        SceneMemory.mapName = mapName
        InitAssets.loadAssets("data/maps/$mapName")
        InitMap.loadMap("data/maps/$mapName")
        //TODO: LightSourcesInit.initLightSources();
        BindTextures.bind()
    }
}
