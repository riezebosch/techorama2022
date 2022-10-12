package demo

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// https://kotlinlang.org/docs/destructuring-declarations.html
class Destructuring {
    private fun create() = Pair(1, "b")

    @Test
    fun pair() {
        val (a, b) = create()

        assertEquals(1, a)
        assertEquals("b", b)
    }

    @Test
    fun triple() {
        val (a, b) = Triple(1, 2, 3)

        assertEquals(1, a)
        assertEquals(2, b)
    }

    data class Data(val x: Int, val y: String, val z: Boolean)

    @Test
    fun destructure() {
        val (a, _, c) = Data(1, "b", true)

        assertEquals(1, a)
        assertTrue(c)
    }
}