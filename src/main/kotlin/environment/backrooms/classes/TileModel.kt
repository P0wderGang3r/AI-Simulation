package environment.backrooms.classes

class TileModel (
    val coord_X: Int,
    val coord_Y: Int,
    val roomFloorNum: Int,
    val height: Double,
    val roomPreset: RoomPreset
) {
    val lowWallModel = ArrayList<Array<Float>>()

    val wallModel = ArrayList<Array<Float>>()

    val floorModel = ArrayList<Array<Float>>()

    private val wallColor = arrayOf(0.847f, 0.741f, 0.624f)

    private val floorColor = arrayOf(0.457f, 0.375f, 0.316f)

    fun genFloor() {
        //Нижний левый угол
        floorModel.add(arrayOf(coord_X.toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))
        //Верхний левый угол
        floorModel.add(arrayOf(coord_X.toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))
        //Нижний правый угол
        floorModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))

        //Верхний правый угол
        floorModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))
        //Верхний левый угол
        floorModel.add(arrayOf(coord_X.toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))
        //Нижний правый угол
        floorModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))
    }

    fun genLowWalls() {
        //Верхняя стена
        for (size_X in 0 until roomPreset.size_X) {
            //Нижний левый угол
            lowWallModel.add(arrayOf((size_X + coord_X).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf((size_X + coord_X).toFloat(), coord_Y.toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            lowWallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), coord_Y.toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf((size_X + coord_X).toFloat(), coord_Y.toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))
        }

        //Нижняя стена
        for (size_X in 0 until roomPreset.size_X) {
            //Нижний левый угол
            lowWallModel.add(arrayOf((size_X + coord_X).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf((size_X + coord_X).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            lowWallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf((size_X + coord_X).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))
        }

        for (size_Y in 0 until roomPreset.size_Y) {
            //Нижний левый угол
            lowWallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y).toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            lowWallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y + 1).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))
        }

        for (size_Y in 0 until roomPreset.size_Y) {
            //Нижний левый угол
            lowWallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y).toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            lowWallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y + 1).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            lowWallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y).toFloat(), (0.1f + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            lowWallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))
        }
    }

    fun genWalls() {
        //Верхняя стена
        for (size_X in 0 until roomPreset.size_X) {
            //Нижний левый угол
            wallModel.add(arrayOf((size_X + coord_X).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf((size_X + coord_X).toFloat(), coord_Y.toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            wallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), coord_Y.toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf((size_X + coord_X).toFloat(), coord_Y.toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), coord_Y.toFloat(), roomFloorNum * height.toFloat()))
        }

        //Нижняя стена
        for (size_X in 0 until roomPreset.size_X) {
            //Нижний левый угол
            wallModel.add(arrayOf((size_X + coord_X).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf((size_X + coord_X).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            wallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf((size_X + coord_X).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf((size_X + coord_X + 1).toFloat(), (coord_Y + roomPreset.size_Y).toFloat(), roomFloorNum * height.toFloat()))
        }

        for (size_Y in 0 until roomPreset.size_Y) {
            //Нижний левый угол
            wallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y).toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            wallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y + 1).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf(coord_X.toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))
        }

        for (size_Y in 0 until roomPreset.size_Y) {
            //Нижний левый угол
            wallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y).toFloat(), roomFloorNum * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))

            //Верхний правый угол
            wallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y + 1).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Верхний левый угол
            wallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y).toFloat(), (1 + roomFloorNum) * height.toFloat()))
            //Нижний правый угол
            wallModel.add(arrayOf((coord_X + roomPreset.size_X).toFloat(), (size_Y + coord_Y + 1).toFloat(), roomFloorNum * height.toFloat()))
        }
    }

    fun getWallColor() = wallColor

    fun getFloorColor(temperature: Double): Array<Float> {
        val deltaTemperature = 20 - temperature

        return arrayOf(
            floorColor[0] + (0 - deltaTemperature.toFloat() * 0.1f),
            floorColor[1],
            floorColor[2] + (deltaTemperature.toFloat() * 0.1f)
        )
    }
}