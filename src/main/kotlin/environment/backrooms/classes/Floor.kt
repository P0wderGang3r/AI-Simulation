package environment.backrooms.classes

import environment.backrooms.json.jRoom
import environment.backrooms.json.jRoomPresets
import environment.network.enums.ErrorType

class Floor (
    val number: Int
){
    private val rooms = ArrayList<Room>()

    fun getRooms() = rooms

    fun genFloor(housePath: String, floorJSON: Array<jRoom>, roomPresetsJSON: jRoomPresets): ErrorType {
        for (roomJSON in floorJSON) {
            val room = Room()
            room.genRoom(housePath, roomJSON, roomPresetsJSON, number)
            rooms.add(room)
        }

        return ErrorType.OK
    }
}