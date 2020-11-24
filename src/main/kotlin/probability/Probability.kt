package probability


class Probability(percentage: Number) {

    private val percentage = percentage.toDouble()

    fun isCertain(): Boolean {
        return percentage == 100.0
    }

    init {
        require(this.percentage >= 0 && this.percentage <= 100) {"Percentage must be between 0 and 100"}
    }

    infix fun and(other: Probability): Probability {
        return Probability(other.percentage * percentage / 100)
    }

    infix fun or(other: Probability): Probability {
        return Probability(other.percentage + this.percentage) - other.and(this)
    }

    operator fun not(): Probability{
        return Probability(100 - percentage)
    }

    operator fun minus(other: Probability): Probability {
        return Probability(this.percentage - other.percentage)
    }

    override fun equals(other: Any?): Boolean {
        other as Probability
        return other.percentage == percentage
    }

    override fun hashCode(): Int =  percentage.hashCode()

}
