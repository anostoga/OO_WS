package quantity

import kotlin.math.roundToLong

class Unit {
    private val baseUnit: Unit
    private val baseUnitRatio: Double
    private val offset: Double

    companion object {
        internal const val EPSILON = 0.000000001

        private val teaspoon = Unit()
        private val tablespoon = Unit(3, teaspoon)
        private val ounce = Unit(2, tablespoon)
        private val cup = Unit(8, ounce)
        private val pint = Unit(2, cup)
        private val quart = Unit(2, pint)
        private val gallon = Unit(4, quart)

        val Number.teaspoons get() = RatioQuantity(this, teaspoon)
        val Number.tablespoons get() = RatioQuantity(this, tablespoon)
        val Number.ounces get() = RatioQuantity(this, ounce)
        val Number.cups get() = RatioQuantity(this, cup)
        val Number.pints get() = RatioQuantity(this, pint)
        val Number.quarts get() = RatioQuantity(this, quart)
        val Number.gallons get() = RatioQuantity(this, gallon)

        private val inch = Unit()
        private val foot = Unit(12, inch)
        private val yard = Unit(3, foot)
        private val chain = Unit(22, yard)
        private val furlong = Unit(10, chain)
        private val mile = Unit(8, furlong)

        val Number.inches get() = RatioQuantity(this, inch)
        val Number.feet get() = RatioQuantity(this, foot)
        val Number.yards get() = RatioQuantity(this, yard)
        val Number.chains get() = RatioQuantity(this, chain)
        val Number.furlongs get() = RatioQuantity(this, furlong)
        val Number.miles get() = RatioQuantity(this, mile)

        private val celsius = Unit()
        private val fahrenheit = Unit(5.0 / 9.0, 32.0, celsius)

        val Number.celsiuses get() = IntervalQuantity(this, celsius)
        val Number.fahrenheits get() = IntervalQuantity(this, fahrenheit)
    }

    private constructor() {
        baseUnit = this
        baseUnitRatio = 1.0
        offset = 0.0
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        offset = 0.0
    }

    private constructor(relativeRatio: Number, offset: Double, relativeUnit: Unit) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        this.offset = offset
    }

    internal fun isCompatible(other: Unit) = this.baseUnit == other.baseUnit

    internal fun convertedAmount(otherAmount: Double, other: Unit) =
        (((otherAmount - other.offset) * (other.baseUnitRatio / this.baseUnitRatio)) + this.offset).also {
            require(this.isCompatible(other)) { "Incompatible units for arithmetic" }
        }



    internal fun hashCode(amount: Double) = (((amount - offset) * baseUnitRatio) / EPSILON).roundToLong().hashCode()

}
