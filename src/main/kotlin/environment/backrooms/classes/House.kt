package environment.backrooms.classes

class House {
    private val floors = ArrayList<Floor>()

    fun getFloors() = floors

    //Devices---------------------------
    fun addDevice(floor: Int, room: String, device: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        var savedRoom: Room? = null
        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedRoom = iterRoom
                break
            }
        }

        if (savedRoom == null)
            return

        for (iterDevice in savedRoom.getDevices()) {
            if (iterDevice.name == device) {
                savedRoom.getDevices().remove(iterDevice)
                return
            }
        }
    }

    fun removeDevice(floor: Int, room: String, device: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        var savedRoom: Room? = null
        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedRoom = iterRoom
                break
            }
        }

        if (savedRoom == null)
            return

        for (iterDevice in savedRoom.getDevices()) {
            if (iterDevice.name == device) {
                savedRoom.getDevices().remove(iterDevice)
                return
            }
        }
    }

    //Rooms-----------------------------

    fun addRoom(floor: Int, room: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedFloor.getRooms().add(iterRoom)
                return
            }
        }
    }

    fun removeRoom(floor: Int, room: String) {
        var savedFloor: Floor? = null
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                savedFloor = iterFloor
                break
            }
        }

        if (savedFloor == null)
            return

        for (iterRoom in savedFloor.getRooms()) {
            if (iterRoom.name == room) {
                savedFloor.getRooms().add(iterRoom)
                return
            }
        }

    }

    //Floors----------------------------

    fun addFloor(floor: Int) {
        floors.add(Floor(floor))
    }

    fun removeFloor(floor: Int) {
        var savedFloor: Floor
        for (iterFloor in floors) {
            if (iterFloor.number == floor) {
                floors.remove(iterFloor)
                return
            }
        }
    }
}