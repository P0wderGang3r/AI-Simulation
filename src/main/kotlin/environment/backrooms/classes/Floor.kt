package environment.backrooms.classes

class Floor (
    val number: Int
){
    private val rooms = ArrayList<Room>()

    fun getRooms() = rooms

}