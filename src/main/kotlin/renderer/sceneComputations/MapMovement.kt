package renderer.sceneComputations

object MapMovement {
    fun move(coords: FloatArray, positionStatus: FloatArray): FloatArray {
        return floatArrayOf(
            coords[0] + positionStatus[0],
            coords[1] + positionStatus[1],
            coords[2] + positionStatus[2]
        )
    }
}
