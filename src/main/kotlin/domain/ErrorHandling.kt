package domain

abstract class ErrorHandling : Exception()

class PositionNotExists : ErrorHandling()

class ShipNotExists : ErrorHandling()

class NoTile : ErrorHandling()

class ExceedLimits : ErrorHandling()