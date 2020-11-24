package oows

class Volume(amount: Int, unit: Unit) {

    val volume: Int = amount * unit.conversion

    override fun equals(other: Any?): Boolean {
        other as Volume
        if (this.volume != other.volume) return false
        return true
    }

    override fun hashCode() = 31 * volume

}

class Unit(type: Type) {

    val conversion: Int = when (type) {
        Type.Teaspoon -> 1
        Type.Tablespoon -> 3
        Type.Ounce -> 6
        Type.Cup -> 32
        Type.Pint -> 64
        Type.Quart -> 128
        Type.Gallon -> 512
    }

    enum class Type {
        Tablespoon, Teaspoon, Ounce, Cup, Pint, Quart, Gallon
    }

}



