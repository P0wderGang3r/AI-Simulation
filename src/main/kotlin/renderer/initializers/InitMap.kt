package renderer.initializers

import jsonParser
import kotlinx.serialization.decodeFromString
import network.default_NN.enums.ErrorType
import renderer.SceneGlobals
import renderer.SceneMemory
import renderer.json.jMap
import renderer.json.jObjects
import renderer.json.jParameters
import renderer.classes.ComplexModel
import renderer.classes.Model
import renderer.classes.Texture
import java.io.File
import java.util.ArrayList

object InitMap {

    private fun fileParser(path: String): ErrorType {

        val jsonText: String
        try {
            val file = File(path)
            jsonText = file.readText()
        } catch (_: Exception) {
            return ErrorType.IOError
        }

        val jMapDesc: jMap
        try {
            jMapDesc = jsonParser.decodeFromString(jsonText)
        } catch (e: Exception) {
            return ErrorType.ParseError
        }

        val result = ErrorType.OK
        result.data = jMapDesc
        return result
    }

    private fun initSceneParameters(jParametersJSON: jParameters) {
        //Устанавливаем размер сцены
        SceneGlobals.mapSizeStatus = jParametersJSON.scene_size[0]

        //Устанавливаем смещение сцены
        SceneGlobals.positionStatus = jParametersJSON.scene_displacement

        //Устанавливаем поворот сцены
        SceneGlobals.rotationStatus = jParametersJSON.scene_rotation

        //Устанавливаем фон сцены
        SceneGlobals.sceneColorStatus = jParametersJSON.scene_ambient_lightning
    }

    private fun findModel(modelName: String): Model {
        for (model in SceneMemory.models!!) {
            if (model.modelName == modelName) {
                return model
            }
        }
        return Model("", "")
    }

    private fun findTexture(textureName: String): Texture {
        for (texture in SceneMemory.textures!!) {
            if (texture.textureName == textureName) {
                return texture
            }
        }
        return SceneMemory.textures!![0]
    }


    private fun initSceneObjects(jObjectsJSON: Array<jObjects>) {
        val complexObjectList = ArrayList<ComplexModel>()
        SceneMemory.complexModels = complexObjectList

        for (objectJSON in jObjectsJSON) {
            val complexObject = ComplexModel(
                findModel(objectJSON.model),
                findTexture(objectJSON.texture),
                objectJSON.size,
                objectJSON.position
            )
            complexObjectList.add(complexObject)
        }
    }

    fun loadMap(path: String): ErrorType {
        val parseResult = fileParser("$path/map.json")

        if (parseResult != ErrorType.OK)
            return parseResult

        val jMapJSON = parseResult.data as jMap

        initSceneParameters(jMapJSON.parameters)

        initSceneObjects(jMapJSON.objects)

        return ErrorType.OK
    }
}