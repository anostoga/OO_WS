package oows

class Volume(amount: Number, private val unit: Unit) {

    private val amount: Double = amount.toDouble()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Volume) return false
        return this.amount == convertedAmount(other)
    }

    private fun convertedAmount(other: Volume) = other.amount * this.unit.ratio(other.unit)

}

class Unit private constructor(baseUnitRatio: Number) {
    internal fun ratio(other: Unit) = other.baseUnitRatio / this.baseUnitRatio

    private val baseUnitRatio = baseUnitRatio.toDouble()

    companion object{
        val cup = Unit(48)
        val ounce = Unit(6)
        val teaspoon = Unit(1)
        val tablespoon = Unit(3)
    }

}



