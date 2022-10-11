package domain

import domain.position.*

data class Grid(val tiles : List<Tile>, val size : Int)

fun Grid.isMissShot(position: Position) = getState(position) == State.MISS.char

fun Grid.getState(position: Position): Char = toChar(getTile(position))

fun Grid.getTile(position: Position) =
    tiles.find { it.position.column == position.column && it.position.row == position.row } ?: throw NoTile()

fun Grid.getTileOrNull(position: Position) =
    tiles.find { it.position.column == position.column && it.position.row == position.row }

fun Grid.placeOnGrid(ship: Ship, position: Position, direction: Direction): Grid {
    val list = position.shipPositions(direction, ship.size).map {
        OwnShip(it, ship)
    }
    return Grid(tiles + list,size)
}

fun Grid.positionsAvailable(position: Position, direction: Direction, squares: Int): Boolean {
    if((position.column.ordinal + squares >= size && direction == Direction.H)
        || (position.row.number + squares >= size && direction == Direction.V)) return false
    val ship = position.shipPositions(direction, squares)
    tiles.forEach {
        if (it is OwnShip) {
            ship.forEach { square ->
                if (square.isAdjacent(it.position)) return false
            }
        }
    }
    return true
}

fun Grid.removeFromGrid(tile: OwnShip) =
    Grid(tiles.filterIsInstance<OwnShip>().filter { it.ship != tile.ship }, size)

fun Grid.shotOnGrid(position: Position): Grid? {
    val tile = getTileOrNull(position)
    return when (toChar(tile)) {
        State.WATER.char -> Grid(tiles + MissShip(position),size)
        State.SHIP.char -> Grid(if (tile is OwnShip) (tiles - tile) + tile.change() else tiles,size)
        else -> null
    }
}

fun Grid.sinkShip(position: Position): Grid {
    val tile = getTile(position)
    if (tile is StateShip)
        if (isShipSunk(tile.ship))
            return Grid(tiles.map {
                if (it is StateShip && it.ship == tile.ship)
                    DownShip(it.position, it.ship)
                else
                    it
            }, size)
    return Grid(tiles,size)
}

fun Grid.isShipSunk(ship: Ship): Boolean {
    return tiles.filterIsInstance<HitShip>().filter { it.ship == ship }.size == ship.size
}



