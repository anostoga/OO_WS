package unit_aa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import oows.Volume.Companion.cups
import oows.Volume.Companion.ounces
import oows.Volume.Companion.tablespoons
import oows.Volume.Companion.teaspoons


class VolumeTest {
    @Test fun `Volume tests` () {
        assertEquals(3.teaspoons, 1.tablespoons )
        assertEquals(4.0.ounces , 0.5.cups)
        assertEquals(-4.0.ounces , -0.5.cups)
    }
}




