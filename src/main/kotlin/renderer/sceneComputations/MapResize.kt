package renderer.sceneComputations

object MapResize {
    fun resize(coordinates: FloatArray, size: Float): FloatArray {
        return floatArrayOf(
            coordinates[0] * size,
            coordinates[1] * size,
            coordinates[2] * size
        )
    }
}
