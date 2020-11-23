package probability

import java.lang.RuntimeException

class Probability(percentage: Number) {

    private val percentage = percentage.toDouble()

    fun isCertain(): Boolean {
        return percentage >= 100.0
    }

    init {
        if (this.percentage < 0 || this.percentage > 100){
            throw RuntimeException("percentage must be between 100 and 0")
        }
    }

    fun and(other: Probability): Probability {
        return Probability(other.percentage * percentage / 100)
    }

    operator fun not(): Probability{
        return Probability(100 - percentage)
    }

    override fun equals(other: Any?): Boolean {
        other as Probability
        return other.percentage == percentage
    }

    override fun hashCode(): Int =  percentage.hashCode()

}
