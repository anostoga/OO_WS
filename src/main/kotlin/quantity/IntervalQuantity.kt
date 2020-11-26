/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package quantity

import kotlin.math.absoluteValue

open class IntervalQuantity internal constructor(amount: Number, protected val unit: Unit) {
    protected val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || (other is IntervalQuantity && this.equals(other))

    private fun equals(other: IntervalQuantity) = this.isCompatible(other) &&
            (this.amount - convertedAmount(other)).absoluteValue < Unit.EPSILON

    override fun hashCode() = unit.hashCode(amount)

    private fun isCompatible(other: IntervalQuantity) = this.unit.isCompatible(other.unit)

    protected fun convertedAmount(other: IntervalQuantity) = this.unit.convertedAmount(other.amount, other.unit)

}
