package probability

class Volume(amount: Int, unit: Unit) {

    private val noTeaspoons: Int = when (unit) {
        Unit.Teaspoon -> 1
        Unit.Tablespoon -> 3
        Unit.Ounce -> 6
        Unit.Cup -> 32
        Unit.Pint -> 64
        Unit.Quart -> 128
        Unit.Gallon -> 512
    } * amount


    override fun equals(other: Any?): Boolean {
        other as Volume
        if (this.noTeaspoons != other.noTeaspoons) return false
        return true
    }

    override fun hashCode() = 31 * noTeaspoons

    enum class Unit {
        Tablespoon, Teaspoon, Ounce, Cup, Pint, Quart, Gallon
    }

}



