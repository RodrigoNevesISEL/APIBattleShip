package domain

import domain.position.Position

abstract class Tile(val position: Position)

class MissShip(position: Position) : Tile(position)

abstract class StateShip(position : Position, val ship: Ship) : Tile(position)

class OwnShip(position: Position, ship : Ship) : StateShip(position, ship)

class HitShip(position: Position, ship: Ship) : StateShip(position, ship)

class DownShip(position: Position, ship: Ship) : StateShip(position, ship)

enum class Direction(val char : Char){
    H('H'), V('V');
}

fun String.toDirection() = Direction.valueOf(this.uppercase())

enum class State(val char: Char){
    WATER(' '),
    SHIP('#'),
    HIT('*'),
    DOWN('X'),
    MISS('O');
}

fun StateShip.change() =
    when(this){
        is OwnShip -> HitShip(position, ship)
        is HitShip -> DownShip(position, ship)
        else -> error("Cant Change State")
    }

fun toChar(tile: Tile?) =
    when (tile) {
        is OwnShip -> State.SHIP.char
        is HitShip -> State.HIT.char
        is DownShip -> State.DOWN.char
        is MissShip -> State.MISS.char
        else -> State.WATER.char
    }