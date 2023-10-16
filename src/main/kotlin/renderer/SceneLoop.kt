package renderer

import org.lwjgl.opengl.GL20
import renderer.SceneGlobals.mapSizeStatus
import renderer.SceneGlobals.positionStatus
import renderer.SceneGlobals.rotationStatus
import renderer.SceneGlobals.sceneColorB
import renderer.SceneGlobals.sceneColorG
import renderer.SceneGlobals.sceneColorR
import renderer.sceneComputations.MapMovement
import renderer.sceneComputations.MapResize
import renderer.sceneComputations.MapRotation

object SceneLoop {
    fun renderMap() {
        var currentPolygon: Array<FloatArray>?
        var textureCorners: Array<FloatArray>
        GL20.glClearColor(sceneColorR, sceneColorG, sceneColorB, 1f)

        //Проходимся по все комплексным моделям сцены
        for (complexModel in SceneMemory.complexModels!!) {
            //Запрашиваем следующий ПОЛИГОН в комплексной модели сцены

            //System.out.println(object.texture.getNumInMemory());

            do {
                currentPolygon = complexModel.nextFace()
                if (currentPolygon == null)
                    break

                //textureCorners = complexModel.texture.nextCorners
                GL20.glBegin(GL20.GL_TRIANGLES)
                GL20.glColor3d(0.5, 0.5, 0.5)

                //Проходимся по каждой ВЕРШИНЕ полигона
                for (index in 0..2) {
                    //GL20.glActiveTexture(GL20.GL_TEXTURE0);
                    //GL20.glBindTexture(GL20.GL_TEXTURE_2D, SceneGlobals.oglTextures[object.texture.getNumInMemory()]);

                    //Применяем матрицу вращения к вершине
                    currentPolygon[index] = MapRotation.rotate(currentPolygon[index], rotationStatus)

                    //Применяем смещение к вершине
                    currentPolygon[index] = MapMovement.move(currentPolygon[index], positionStatus)

                    //Применяем изменение в размерах к вершине
                    currentPolygon[index] = MapResize.resize(currentPolygon[index], mapSizeStatus)

                    //Закрепляю за следующей координатой полигона один из углов текстуры
                    //GL20.glTexCoord2d(textureCorners[index][0].toDouble(), textureCorners[index][1].toDouble())

                    //Добавляем в стек вершину
                    GL20.glVertex3d(
                        currentPolygon[index][0].toDouble(),
                        currentPolygon[index][1].toDouble(),
                        currentPolygon[index][2].toDouble()
                    )
                }
                GL20.glEnd()

                //GL20.glBindTexture(GL20.GL_TEXTURE_2D, 0);
            } while (currentPolygon != null)
        }
    }
}
