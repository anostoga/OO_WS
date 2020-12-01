/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package probability

import order.Orderable
import kotlin.math.absoluteValue
import kotlin.math.roundToLong

// Understands the likelihood of something specific occurring
class Chance(likelihoodAsFraction: Number): Orderable<Chance> {
    private val fraction = likelihoodAsFraction.toDouble()

    init {
        require(fraction in (0.0..1.0)) { "Fraction must be between 0 and 1, inclusive" }
    }

    companion object {
        private const val CERTAIN_FRACTION = 1.0
        private const val EPSILON = 0.000000001
    }

    override fun equals(other: Any?) = other is Chance && this.equals(other)

    private fun equals(other: Chance) = (this.fraction - other.fraction).absoluteValue < EPSILON

    override fun hashCode() = (fraction / EPSILON).roundToLong().hashCode()

    operator fun not() = Chance(CERTAIN_FRACTION - fraction)

    infix fun and(other: Chance) = Chance(this.fraction * other.fraction)

    // Implemented with DeMorgan's Law https://en.wikipedia.org/wiki/De_Morgan%27s_laws
    infix fun or(other: Chance) = !((!this).and(!other))

    override fun isBetterThan(other: Chance) = this.fraction < other.fraction
}