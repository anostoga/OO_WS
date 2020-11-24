package unit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import probability.Unit.*
import probability.Volume


class MeasumentTest {
    @Test fun `Volume tests` () {
        assertEquals(Volume(1, Tablespoon), Volume(1, Tablespoon))
        assertNotEquals(Volume(1, Tablespoon), Volume(2, Tablespoon))

        assertNotEquals(Volume(1, Tablespoon), Volume(1, Teaspoon))
        assertEquals(Volume(1, Tablespoon), Volume(3, Teaspoon))

        assertEquals(Volume(2, Gallon ), Volume(8, Quart))
        assertNotEquals(Volume(2, Gallon ), Volume(4, Quart))

       // assertTrue(Volume(1, Gallon) > Volume(1, Teaspoon))

    }
}




