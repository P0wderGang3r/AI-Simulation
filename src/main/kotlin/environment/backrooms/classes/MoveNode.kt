package environment.backrooms.classes

import environment.globals.MoveNodeType

class MoveNode (
    coord_X: Int,
    coord_Y: Int,
    room: Room
) {
    var moveNodeType = MoveNodeType.DEFAULT
    val connectedNodes = ArrayList<MoveNode>()
}