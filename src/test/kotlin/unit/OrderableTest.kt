/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

package unit

import order.bestOrNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import probability.Chance
import quantity.RatioQuantity
import quantity.Unit.Companion.celsius
import quantity.Unit.Companion.cups
import quantity.Unit.Companion.fahrenheit
import quantity.Unit.Companion.gallons
import quantity.Unit.Companion.ounces
import quantity.Unit.Companion.quarts
import rectangle.Rectangle


// Ensures Orderable operates correctly
internal class OrderableTest {

    @Test internal fun `rectangle with largest area`() {
        assertEquals(36.0, listOf(Rectangle(2, 3), Rectangle.square(6.0), Rectangle(5, 7.0)).bestOrNull()?.area())
        assertNull(emptyList<Rectangle>().bestOrNull())
    }

    @Test internal fun `least likely chance`() {
        assertEquals(Chance(0), listOf(Chance(0.1), Chance(0.7), Chance(0), Chance(0.9)).bestOrNull())
        assertNull(emptyList<Chance>().bestOrNull())
    }

    @Test fun `largest Quantity`() {
        assertEquals(2.quarts, listOf(
                0.2.gallons, 24.ounces, 0.5.gallons, 7.cups).bestOrNull())
        assertNull(emptyList<RatioQuantity>().bestOrNull())
        assertEquals(100.celsius, listOf(
                212.fahrenheit, 0.celsius, 50.fahrenheit, (-40).celsius).bestOrNull())
    }
}
