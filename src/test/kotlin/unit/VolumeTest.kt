package unit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import oows.Volume
import oows.Unit


class VolumeTest {
    @Test fun `Volume tests` () {
        assertEquals(Volume(3, Unit.teaspoon), Volume(1, Unit.tablespoon))
        assertEquals(Volume(4, Unit.ounce), Volume(0.5, Unit.cup))

    }
}




