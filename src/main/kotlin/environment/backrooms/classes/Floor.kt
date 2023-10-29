package environment.backrooms.classes

import environment.backrooms.json.jRoom
import environment.backrooms.json.jRoomType
import environment.network.enums.ErrorType

class Floor (
    val number: Int
){
    private val rooms = ArrayList<Room>()

    fun getRooms() = rooms

    fun genRoomsByType(roomTypesJSON: Array<jRoomType>): ErrorType {
        for (room in rooms) {
            room.genRoomByType(roomTypesJSON)
        }

        return ErrorType.OK
    }

    fun genFloor(floorJSON: Array<jRoom>): ErrorType {
        for (roomJSON in floorJSON) {
            val room = Room()
            room.genRoom(roomJSON)
            rooms.add(room)
        }

        return ErrorType.OK
    }
}