package demo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Extensions {
    private fun Int.translate() = when (this) {
        1 -> "one"
        2 -> "two"
        3 -> "three"
        else -> "unknown number"
    }

    @Test
    fun test() {
        assertEquals("one", 1.translate())
        assertEquals("two", 2.translate())
        assertEquals("three", 3.translate())
    }

    private infix fun Int.subtract(item: Int) = this - item

    @Test
    fun infix() {
        4 subtract 1 shouldBe 3
    }
}