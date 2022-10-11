package domain

import java.util.*

enum class ShipType(val str :String){
    CARRIER("CARRIER"),
    BATTLESHIP("BATTLESHIP"),
    CRUISER("CRUISER"),
    DESTROYER("DESTROYER"),
    SUBMARINE("SUBMARINE");

    companion object{
        val values = values()
    }

}

data class Ship(val type: ShipType, val size : Int)

fun String.toShipType(size : Int) : Ship{
    val shipType = ShipType.values.find{ this.uppercase() == it.str} ?: throw ShipNotExists()
    return Ship(shipType,size)
}