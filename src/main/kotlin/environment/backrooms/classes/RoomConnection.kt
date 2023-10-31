package environment.backrooms.classes

class RoomConnection (room1: Room, room2: Room) {
    //Данные координаты отражают, где именно в ЭТОЙ комнате должен быть дверной проём
    /**
     * Координата двери в комнате 1 по X
     */
    var coord_X_1: Int = 0

    /**
     * Координата двери в комнате 1 по Y
     */
    var coord_Y_1: Int = 0

    /**
     * Координата двери в комнате 2 по X
     */
    var coord_X_2: Int = 0

    /**
     * Координата двери в комнате 2 по Y
     */
    var coord_Y_2: Int = 0

    init {
        fun genRandomDoor(room1Start: Int, room1End: Int, room2Start: Int, room2End: Int): Int {
            val firstCoord = room1Start.coerceAtLeast(room2Start)
            val secondCoord = room2End.coerceAtLeast(room2End)
            return firstCoord + ((Math.random() * 10).toInt() % (firstCoord - secondCoord))
        }

        val room1_coord_X_end = room1.roomType.size_X + room1.coord_X
        val room1_coord_Y_end = room1.roomType.size_Y + room1.coord_Y


        val room2_coord_X_end = room2.roomType.size_X + room2.coord_X
        val room2_coord_Y_end = room2.roomType.size_Y + room2.coord_Y

        //Проверка соприкосновения по левой стене комнаты 1 с правой стеной комнаты 2
        if (room1.coord_X == room2_coord_X_end) {
            coord_X_1 = 0
            coord_X_2 = room2.roomType.size_X - 1
            coord_Y_1 = genRandomDoor(
                room1.coord_Y,
                room1_coord_Y_end,
                room2.coord_Y,
                room2_coord_Y_end
            )
            coord_Y_2 = coord_Y_1
        }

        //Проверка соприкосновения по правой стене комнаты 1 с левой стеной комнаты 2
        if (room1_coord_X_end == room2.coord_X) {
            coord_X_1 = room1.roomType.size_X - 1
            coord_X_2 = 0
            coord_Y_1 = genRandomDoor(
                room1.coord_Y,
                room1_coord_Y_end,
                room2.coord_Y,
                room2_coord_Y_end
            )
            coord_Y_2 = coord_Y_1
        }


        //Проверка соприкосновения по верхней стене комнаты 1 с нижней стеной комнаты 2
        if (room1.coord_Y == room2_coord_Y_end) {
            coord_Y_1 = 0
            coord_Y_2 = room2.roomType.size_Y - 1
            coord_X_1 = genRandomDoor(
                room1.coord_X,
                room1_coord_X_end,
                room2.coord_X,
                room2_coord_X_end
            )
            coord_X_2 = coord_X_1
        }

        //Проверка соприкосновения по нижней стене комнаты 1 с верхней стеной комнаты 2
        if (room1_coord_Y_end == room2.coord_Y) {
            coord_Y_1 = room1.roomType.size_Y - 1
            coord_Y_2 = 0
            coord_X_1 = genRandomDoor(
                room1.coord_X,
                room1_coord_X_end,
                room2.coord_X,
                room2_coord_X_end
            )
            coord_X_2 = coord_X_1
        }
    }
}