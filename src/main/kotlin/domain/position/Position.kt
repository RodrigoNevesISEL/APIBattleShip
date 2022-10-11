package domain.position

import domain.Direction
import domain.PositionNotExists

data class Position(val column : Column, val row : Row){
    override fun toString(): String = "${column.letter}${row.number}"

    fun isAdjacent(position: Position) : Boolean {
        return (this.column.ordinal) - (position.column.ordinal) in -1..1 &&
                (this.row.number) - (position.row.number) in -1..1
    }

}

/**
 * throw PositionNotExists
 */

fun String.toPosition() : Position{
    val column = this[0].toColumnOrNull() ?: throw PositionNotExists()

    val rowString : String = if(this.length == 2)this[1].toString() else this[1].toString() + this[2]
    val row =  rowString.toRowOrNull() ?: throw PositionNotExists()

    return Position(column,row)

}

fun Position.shipPositions(direction: Direction, squares: Int) =
    List(squares) {
        if (direction === Direction.H)
            Position((column.letter + it).toColumn(), row)
        else
            Position(column, (row.number + it).toRow())
    }

