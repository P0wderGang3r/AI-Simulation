package environment.backrooms.classes

import environment.backrooms.json.jFurniture

class Furniture (
    furnitureObjectJSON: jFurniture
) {
    var furnitureType: String

    var coordX: Int

    var coordY: Int

    var rotation: Int

    init {
        this.furnitureType = furnitureObjectJSON.furniture_type
        this.coordX = furnitureObjectJSON.coord_X
        this.coordY = furnitureObjectJSON.coord_Y
        this.rotation = furnitureObjectJSON.rotation
    }
}
