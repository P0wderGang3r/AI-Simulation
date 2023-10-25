package environment.backrooms.classes

import environment.backrooms.json.jDevice
import environment.backrooms.json.jFurniture
import environment.backrooms.json.jRoomType
import environment.network.default_NN.enums.ErrorType

class RoomType {
    var header: String = "Empty"

    var size_X: Int = 1

    var size_Y: Int = 1

    var furniture = ArrayList<Furniture>()

    fun genFurniture(furnitureJSON: Array<jFurniture>) {
        furniture.clear()
        for (furnitureObjectJSON in furnitureJSON) {
            furniture.add(Furniture(furnitureObjectJSON))
        }
    }

    fun genRoomType(roomTypeJSON: jRoomType): ErrorType {
        this.header = roomTypeJSON.header
        this.size_X = roomTypeJSON.size_X
        this.size_Y = roomTypeJSON.size_Y

        genFurniture(roomTypeJSON.furniture)

        return ErrorType.OK
    }
}