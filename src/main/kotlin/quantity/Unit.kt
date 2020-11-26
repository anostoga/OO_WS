package quantity

import kotlin.math.roundToLong

class Unit {
    private val baseUnit: Unit
    private val baseUnitRatio: Double
    private val offset: Double
    private val isTemperature: Boolean

    companion object {
        internal const val EPSILON = 0.000000001

        private val teaspoon = Unit()
        private val tablespoon = Unit(3, teaspoon)
        private val ounce = Unit(2, tablespoon)
        private val cup = Unit(8, ounce)
        private val pint = Unit(2, cup)
        private val quart = Unit(2, pint)
        private val gallon = Unit(4, quart)

        val Number.teaspoons get() = Quantity(this, teaspoon)
        val Number.tablespoons get() = Quantity(this, tablespoon)
        val Number.ounces get() = Quantity(this, ounce)
        val Number.cups get() = Quantity(this, cup)
        val Number.pints get() = Quantity(this, pint)
        val Number.quarts get() = Quantity(this, quart)
        val Number.gallons get() = Quantity(this, gallon)

        private val inch = Unit()
        private val foot = Unit(12, inch)
        private val yard = Unit(3, foot)
        private val chain = Unit(22, yard)
        private val furlong = Unit(10, chain)
        private val mile = Unit(8, furlong)

        val Number.inches get() = Quantity(this, inch)
        val Number.feet get() = Quantity(this, foot)
        val Number.yards get() = Quantity(this, yard)
        val Number.chains get() = Quantity(this, chain)
        val Number.furlongs get() = Quantity(this, furlong)
        val Number.miles get() = Quantity(this, mile)

        private val celsius = Unit(true)
        private val fahrenheit = Unit(5.0 / 9.0, 32.0, celsius, true)

        val Number.celsiuses get() = Quantity(this, celsius)
        val Number.fahrenheits get() = Quantity(this, fahrenheit)
    }

    private constructor(isTemperature: Boolean = false) {
        baseUnit = this
        baseUnitRatio = 1.0
        offset = 0.0
        this.isTemperature = isTemperature
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit, isTemperature: Boolean = false) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        offset = 0.0
        this.isTemperature = isTemperature
    }

    private constructor(relativeRatio: Number, offset: Double, relativeUnit: Unit, isTemperature: Boolean = false) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        this.offset = offset
        this.isTemperature = isTemperature
    }

    internal fun isCompatible(other: Unit) = this.baseUnit == other.baseUnit

    internal fun isPossibleToPlusMinus() = !isTemperature

    internal fun convertedAmount(otherAmount: Double, other: Unit) =
        (((otherAmount - other.offset) * (other.baseUnitRatio / this.baseUnitRatio)) + this.offset).also {
            require(this.isCompatible(other)) { "Incompatible units for arithmetic" }
        }



    internal fun hashCode(amount: Double) = (((amount - offset) * baseUnitRatio) / EPSILON).roundToLong().hashCode()

}
