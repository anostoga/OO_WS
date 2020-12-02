
import graph.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

// Ensures graph operations work
internal class GraphTest {
    companion object {
        private val a = Node()
        private val b = Node()
        private val c = Node()
        private val d = Node()
        private val e = Node()
        private val f = Node()
        private val g = Node()

        init {
            b cost 5 to a
            b cost 6 to c cost 7 to d cost 2 to e cost 3 to b cost 4 to f
            c cost 1 to d
            c cost 8 to e
        }
    }

    @Test
    internal fun `can reach`() {
        Assertions.assertTrue(b canReach b)
        Assertions.assertTrue(b canReach a)
        Assertions.assertTrue(b canReach f)
        Assertions.assertTrue(b canReach d)
        Assertions.assertTrue(c canReach f)
        Assertions.assertFalse(g canReach b)
        Assertions.assertFalse(a canReach b)
        Assertions.assertFalse(b canReach g)
    }

    @Test
    internal fun `hop count`() {
        assertEquals(0, b hopCount b)
        assertEquals(1, b hopCount a)
        assertEquals(1, b hopCount f)
        assertEquals(2, b hopCount d)
        assertEquals(3, c hopCount f)
        assertThrows<IllegalArgumentException> { g hopCount b}
        assertThrows<IllegalArgumentException> { a hopCount b}
        assertThrows<IllegalArgumentException> { b hopCount g}
    }

    @Test
    fun cost() {
        assertEquals(0.0, b cost b)
        assertEquals(5.0, b cost a)
        assertEquals(4.0, b cost f)
        assertEquals(7.0, b cost d)
        assertEquals(10.0, c cost f)
        assertThrows<IllegalArgumentException> { g cost b }
        assertThrows<IllegalArgumentException> { a cost b }
        assertThrows<IllegalArgumentException> { b cost g }
    }

    @Test
    fun path() {
        assertPath(a, a, 0, 0)
        assertPath(b, a, 1, 5)
        assertPath(b, f, 1, 4)
        assertPath(b, d, 2, 7)
        assertPath(c, f, 4, 10)
        assertPath(b, e, 3, 9)
        assertThrows<IllegalArgumentException> { g path b }
        assertThrows<IllegalArgumentException> { a path b }
        assertThrows<IllegalArgumentException> { b path g }
    }

    private fun assertPath(source: Node, destination: Node, expectedHopCount: Int, expectedCost: Number) {
        val p = source path destination
        assertEquals(expectedHopCount, p.hopCount())
        assertEquals(expectedCost.toDouble(), p.cost())
    }

    @Test
    fun paths() {
        assertEquals(1, (b paths a).size)
        assertEquals(2, (c paths d).size)
        assertEquals(3, (c paths f).size)
        assertEquals(0, (b paths g).size)
    }
}
