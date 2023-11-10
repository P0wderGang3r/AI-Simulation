package renderer.classes

class Joint (
    val parent: TexturedModel,
    val jointPos: FloatArray
) {
    val childs = ArrayList<TexturedModel>()
}