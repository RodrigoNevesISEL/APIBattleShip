package domain

data class Fleet(val ships : MutableList<Ship>)

fun Fleet.countEqualsShips(ship : Ship) = ships.count { it == ship }

fun Fleet.removeFromFleet(ship : Ship) = ships.remove(ship)

fun Fleet.addToFleet(ship: Ship) = ships.add(ship)