package renderer

import org.lwjgl.opengl.GL20
import renderer.SceneGlobals.mapSizeStatus
import renderer.SceneGlobals.positionStatus
import renderer.SceneGlobals.rotationStatus
import renderer.computations.MapTransformations

object SceneLoop {
    private fun renderLowWalls() {
        for (floor in SceneMemory.outdoor!!.getHouse().getFloors()) {
            val currentPolygon = ArrayList<FloatArray>()

            for (room in floor.getRooms()) {

                val wallColor = arrayOf(
                    room.getWallColor()[0] + ((floor.number - 1) * 0.1f),
                    room.getWallColor()[1] + ((floor.number - 1) * 0.1f),
                    room.getWallColor()[2] + ((floor.number - 1) * 0.1f))

                GL20.glColor3f(wallColor[0], wallColor[1], wallColor[2])
                for (wallModel in room.getLowWallModel()) {
                    currentPolygon.add(wallModel.toFloatArray())
                    if (currentPolygon.size == 3) {
                        GL20.glBegin(GL20.GL_TRIANGLES)
                        for (vertex in currentPolygon) {
                            var currentVertex = vertex.clone()

                            //Применяем смещение к вершине
                            currentVertex = MapTransformations.move1D(currentVertex, SceneMemory.currentFloor)

                            //Применяем смещение к вершине
                            currentVertex = MapTransformations.move(currentVertex, positionStatus)

                            //Применяем изменение в размерах к вершине
                            currentVertex = MapTransformations.resize(currentVertex, mapSizeStatus)

                            //Применяем матрицу вращения к вершине
                            currentVertex = MapTransformations.rotate(currentVertex, rotationStatus)

                            //Применяем изменение в размерах к вершине
                            currentVertex = MapTransformations.resize3D(currentVertex, arrayOf(0.5625f, 1.0f, 0.5625f))

                            //Добавляем в стек вершину
                            GL20.glVertex3d(
                                currentVertex[0] * 0.5,
                                currentVertex[1] * 0.5,
                                currentVertex[2] * 0.5
                            )
                        }
                        currentPolygon.clear()
                        GL20.glEnd()
                    }
                }

            }

            if (floor.number == SceneMemory.currentFloor)
                break
        }
    }

    private fun renderWalls() {
        for (floor in SceneMemory.outdoor!!.getHouse().getFloors()) {
            if (floor.number == SceneMemory.currentFloor)
                break

            val currentPolygon = ArrayList<FloatArray>()

            for (room in floor.getRooms()) {

                val wallColor = arrayOf(
                    room.getWallColor()[0] + ((floor.number - 1) * 0.1f),
                    room.getWallColor()[1] + ((floor.number - 1) * 0.1f),
                    room.getWallColor()[2] + ((floor.number - 1) * 0.1f))

                GL20.glColor3f(wallColor[0], wallColor[1], wallColor[2])
                for (wallModel in room.getWallModel()) {
                    currentPolygon.add(wallModel.toFloatArray())
                    if (currentPolygon.size == 3) {
                        GL20.glBegin(GL20.GL_TRIANGLES)
                        for (vertex in currentPolygon) {
                            var currentVertex = vertex.clone()

                            //Применяем смещение к вершине
                            currentVertex = MapTransformations.move1D(currentVertex, SceneMemory.currentFloor)

                            //Применяем смещение к вершине
                            currentVertex = MapTransformations.move(currentVertex, positionStatus)

                            //Применяем изменение в размерах к вершине
                            currentVertex = MapTransformations.resize(currentVertex, mapSizeStatus)

                            //Применяем матрицу вращения к вершине
                            currentVertex = MapTransformations.rotate(currentVertex, rotationStatus)

                            //Применяем изменение в размерах к вершине
                            currentVertex = MapTransformations.resize3D(currentVertex, arrayOf(0.5625f, 1.0f, 0.5625f))

                            //Добавляем в стек вершину
                            GL20.glVertex3d(
                                currentVertex[0] * 0.5,
                                currentVertex[1] * 0.5,
                                currentVertex[2] * 0.5
                            )
                        }
                        currentPolygon.clear()
                        GL20.glEnd()
                    }
                }

            }
        }
    }

    private fun renderFloor() {
        for (floor in SceneMemory.outdoor!!.getHouse().getFloors()) {
            val currentPolygon = ArrayList<FloatArray>()

            for (room in floor.getRooms()) {

                val floorColor = room.getFloorColor()
                GL20.glColor3f(floorColor[0], floorColor[1], floorColor[2])
                for (floorModel in room.getFloorModel()) {
                    currentPolygon.add(floorModel.toFloatArray())
                    if (currentPolygon.size == 3) {
                        GL20.glBegin(GL20.GL_TRIANGLES)
                        for (vertex in currentPolygon) {
                            var currentVertex = vertex.clone()

                            //Применяем смещение к вершине
                            currentVertex = MapTransformations.move1D(currentVertex, SceneMemory.currentFloor)

                            //Применяем смещение к вершине
                            currentVertex = MapTransformations.move(currentVertex, positionStatus)

                            //Применяем изменение в размерах к вершине
                            currentVertex = MapTransformations.resize(currentVertex, mapSizeStatus)

                            //Применяем матрицу вращения к вершине
                            currentVertex = MapTransformations.rotate(currentVertex, rotationStatus)

                            //Применяем изменение в размерах к вершине
                            currentVertex = MapTransformations.resize3D(currentVertex, arrayOf(0.5625f, 1.0f, 0.5625f))

                            //Добавляем в стек вершину
                            GL20.glVertex3d(
                                currentVertex[0] * 0.5,
                                currentVertex[1] * 0.5,
                                currentVertex[2] * 0.5
                            )
                        }
                        currentPolygon.clear()
                        GL20.glEnd()
                    }
                }

            }
            if (floor.number == SceneMemory.currentFloor)
                break
        }
    }

    private fun renderOutdoor() {
        //Проходимся по все комплексным моделям сцены
        for (complexModel in SceneMemory.complexModels!!) {
            GL20.glColor3d(0.8, 0.8, 0.8)

            var currentPolygon: Array<FloatArray>?
            var textureCorners: Array<FloatArray>

            //System.out.println(object.texture.getNumInMemory());

            while (true) {
                //Запрашиваем следующий ПОЛИГОН в комплексной модели сцены
                currentPolygon = complexModel.nextFace()
                if (currentPolygon == null)
                    break

                GL20.glBindTexture(GL20.GL_TEXTURE_2D, SceneGlobals.oglTextures[complexModel.texture.numInMemory])
                GL20.glEnable(GL20.GL_TEXTURE_2D)
                textureCorners = complexModel.texture.getNextCorners()

                GL20.glBegin(GL20.GL_TRIANGLES)
                //GL20.glColor3d(1.0, 1.0, 1.0)

                //Проходимся по каждой ВЕРШИНЕ полигона
                for (index in 0..2) {
                    GL20.glActiveTexture(GL20.GL_TEXTURE0)
                    GL20.glBindTexture(GL20.GL_TEXTURE_2D, SceneGlobals.oglTextures[complexModel.texture.numInMemory])

                    //Применяем смещение к вершине
                    currentPolygon[index] = MapTransformations.move(currentPolygon[index], arrayOf(0.0f, 0.0f, -1.01f).toFloatArray())

                    //Применяем матрицу вращения к вершине
                    currentPolygon[index] = MapTransformations.rotate(currentPolygon[index], rotationStatus)

                    //Применяем изменение в размерах к вершине
                    currentPolygon[index] = MapTransformations.resize3D(currentPolygon[index], arrayOf(0.5625f, 1.0f, 0.5625f))

                    //Применяем изменение в размерах к вершине
                    currentPolygon[index] = MapTransformations.resize(currentPolygon[index], mapSizeStatus)

                    //Закрепляю за следующей координатой полигона один из углов текстуры
                    GL20.glTexCoord2d(textureCorners[index][0].toDouble(), textureCorners[index][1].toDouble())

                    //Добавляем в стек вершину
                    GL20.glVertex3d(
                        currentPolygon[index][0].toDouble(),
                        currentPolygon[index][1].toDouble(),
                        currentPolygon[index][2].toDouble()
                    )
                }
                GL20.glEnd()

                GL20.glDisable(GL20.GL_TEXTURE_2D)
                GL20.glBindTexture(GL20.GL_TEXTURE_2D, 0)
            }
        }

    }

    fun renderer() {
        //GL20.glClearColor(sceneColorR, sceneColorG, sceneColorB, 1f)

        renderFloor()
        renderWalls()
        renderLowWalls()
        renderOutdoor()
    }
}
