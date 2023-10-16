package renderer.primitives

class ComplexModel(
    var model: Model,
    var texture: Texture,
    private val size: Float,
    private val posOfMdl: FloatArray
) {
    fun nextFace(): Array<FloatArray>? {
            val res = Array(3) { FloatArray(3) }
            var vLine: FloatArray
            if (model.lastF < model.fModel.size) {

                /*
                println("--------------------------")
                println("f: " + model.lastF)
                print("f[n]: ")
                for (mdl in model.fModel[model.lastF])
                    print("${mdl} ")
                println()

                 */

                for (i in 0..2) {
                    vLine = model.vModel[model.fModel[model.lastF][i]]

                    /*
                    println("f_i: " + model.fModel[model.lastF][i])
                    for (mdl in vLine)
                        print("$mdl ")
                    println()

                     */

                    res[i][0] = vLine[0] * size * model.normalizedSize + posOfMdl[0]
                    res[i][1] = vLine[1] * size * model.normalizedSize + posOfMdl[1]
                    res[i][2] = vLine[2] * size * model.normalizedSize + posOfMdl[2]
                }
                model.lastF += 1
            } else {
                model.lastF = 0
                return null
            }

         return res
        }
}
