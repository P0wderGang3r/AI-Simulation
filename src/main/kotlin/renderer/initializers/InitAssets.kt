package renderer.initializers

import jsonParser
import kotlinx.serialization.decodeFromString
import environment.network.default_NN.enums.ErrorType
import renderer.SceneMemory
import renderer.json.jAssets
import renderer.json.jModel
import renderer.json.jTexture
import renderer.classes.Model
import renderer.classes.Texture
import java.io.File
import java.util.ArrayList

object InitAssets {

    private fun fileParser(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val jAssetsDesc: jAssets
        try {
            jAssetsDesc = jsonParser.decodeFromString(jsonText)
        } catch (e: Exception) {
            return ErrorType.ParseError
        }

        val result = ErrorType.OK
        result.data = jAssetsDesc
        return result
    }

    private fun initModels(pathToDirectory: String, jModelsJSON: Array<jModel>) {
        val modelList = ArrayList<Model>()
        SceneMemory.models = modelList

        for (model in jModelsJSON) {
            modelList.add(Model(model.name, "$pathToDirectory/${model.path}"))
        }
    }

    private fun initTextures(pathToDirectory: String, jTexturesJSON: Array<jTexture>) {
        val textureList = ArrayList<Texture>()
        SceneMemory.textures = textureList

        for (texture in jTexturesJSON) {
            textureList.add(Texture(texture.name,
                "$pathToDirectory/${texture.path}",
                texture.width, texture.height)
            )
        }
    }

    fun loadAssets(path: String): ErrorType {
        val parseResult = fileParser("$path/assets.json")

        if (parseResult != ErrorType.OK)
            return parseResult

        val jAssetsJSON = parseResult.data as jAssets

        initModels(jAssetsJSON.modelsDir, jAssetsJSON.models)

        initTextures(jAssetsJSON.texturesDir, jAssetsJSON.textures)

        return ErrorType.OK
    }
}