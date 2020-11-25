package oows

class Volume private constructor(amount: Number, private val unit: Unit) {


    companion object {
        val Number.tablespoons get() = Volume(this, Unit.tablespoon)
        val Number.teaspoons get() = Volume(this, Unit.teaspoon)
        val Number.ounces get() = Volume(this, Unit.ounce)
        val Number.cups get() = Volume(this, Unit.cup)
    }

    private val amount: Double = amount.toDouble()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Volume) return false
        return this.amount == convertedAmount(other)
    }

    private fun convertedAmount(other: Volume) = other.amount * this.unit.ratio(other.unit)

    operator fun unaryMinus() = Volume(-amount, unit)

    override fun hashCode(): Int {
        var result = unit.hashCode()
        result = 31 * result + amount.hashCode()
        return result
    }
}



