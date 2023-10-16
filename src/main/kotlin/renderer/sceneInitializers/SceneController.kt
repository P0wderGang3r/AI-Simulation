package renderer.sceneInitializers

import renderer.SceneMemory

object SceneController {
    //Управление созданием сцены через выбор карты
    fun mapInitialize(mapName: String?) {
        SceneMemory.mapName = mapName
        initAssets.loadAssets("data/maps/$mapName")
        initMap.loadMap("data/maps/$mapName")
        //TODO: LightSourcesInit.initLightSources();
        //initSceneParameters()
        //TODO: bindTextures.bind();
    }
}
