package domain.position

data class Row(val number : Int)

fun Int.toRowOrNull(): Row? {
  return if(this in  1..26 ) Row(this) else null
}

fun Int.toRow(): Row {
    return Row(this)
}

fun String.toRowOrNull() : Row?{
    val number = this.toIntOrNull() ?: return null
    if(number !in 1..26) return null
    return Row(number)

}
