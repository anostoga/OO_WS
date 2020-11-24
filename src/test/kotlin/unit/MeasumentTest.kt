package unit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import oows.Volume
import oows.Unit
import oows.Unit.Type


class MeasumentTest {
    @Test fun `Volume tests` () {
        assertEquals(Volume(1, Unit(Type.Tablespoon)), Volume(1, Unit(Type.Tablespoon)))
        assertNotEquals(Volume(1, Unit(Type.Tablespoon)), Volume(2, Unit(Type.Tablespoon)))

        assertNotEquals(Volume(1,Unit(Type.Tablespoon)), Volume(1, Unit(Type.Teaspoon)))
        assertEquals(Volume(1, Unit(Type.Tablespoon)), Volume(3, Unit(Type.Teaspoon)))

        assertEquals(Volume(2, Unit(Type.Gallon)), Volume(8, Unit(Type.Quart)))
        assertNotEquals(Volume(2, Unit(Type.Gallon)), Volume(4, Unit(Type.Quart)))

    }
}




