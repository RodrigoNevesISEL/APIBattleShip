package domain.position

import domain.ExceedLimits

const val FirstColumnChar = 'A'

 class Column(val letter : Char){

     val ordinal : Int = letter - FirstColumnChar

}


fun Char.toColumnOrNull() : Column?{
    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVYWXZ"

    return if(alphabet.contains(this.uppercaseChar())) Column(this) else  null

}

fun Char.toColumn() : Column = toColumnOrNull() ?: throw ExceedLimits()

fun Int.toColumn() : Column{
        return Column(FirstColumnChar + this - 1)
}






