package unit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import probability.Probability

class ProbabilityTest {
    @Test
    fun `100 % is certain`(){
        assertTrue(Probability(100.0).isCertain())
    }

    @Test fun `99,9% is not certain`(){
        assertFalse(Probability(99.9).isCertain())
    }

    @Test fun `50 and 50 equals 25`() {
        assertEquals(Probability(50.0) and Probability(50), Probability(25))
    }

    @Test fun `The probability of 70 not happening should be 30`() {
        assertEquals(Probability(30), !Probability(70))
    }

    @Test fun `The probability of 70,5 not happening should be 29,5`() {
        assertEquals(Probability(29.5), !Probability(70.5))
    }

    @Test fun `The probability of 70,5 not happening should not be 30`() {
        assertNotEquals(Probability(30), !Probability(70.5))
    }

    @Test fun `Check that hashcode works` () {
        assertNotSame(Probability(50).hashCode(), Probability(50).hashCode())
    }

    @Test fun `Percentage over 100 is disallowed`() {
        assertThrows<IllegalArgumentException>{Probability(101)}
    }


    @Test fun `Probability for x or y = (x + y) - x * y` () {
        assertEquals(Probability(75), Probability(50).or(Probability(50)))
    }


}
