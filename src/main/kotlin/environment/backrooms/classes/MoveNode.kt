package environment.backrooms.classes

import environment.globals.MoveNodeType

class MoveNode (
    val coord_X: Int,
    val coord_Y: Int,
    val room: Room
) {
    var moveNodeType = MoveNodeType.DEFAULT
    val connectedNodes = ArrayList<MoveNode>()
}