package oows

class Unit private constructor(baseUnitRatio: Number) {
    internal fun ratio(other: Unit) = other.baseUnitRatio / this.baseUnitRatio

    private val baseUnitRatio = baseUnitRatio.toDouble()

    companion object{
        val teaspoon = Unit(1)
        val tablespoon = Unit(3)
        val ounce = Unit(6)
        val cup = Unit(48)
        val pint = Unit(64)
        val quart = Unit(128)
        val gallon = Unit(512)
    }
}
