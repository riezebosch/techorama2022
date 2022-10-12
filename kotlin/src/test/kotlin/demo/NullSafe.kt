package demo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NullSafe {
    @Test
    fun safe() {
        val input: Int? = null
        val exception = assertThrows<NullPointerException> {
            input!!
        }

        exception.stackTrace[0].lineNumber shouldBe 10
    }

    fun calc(input: Int) = 2 * input

    @Test
    fun calling() {
        val input: Int? = null
        input shouldBe null

//        calc(input!!)
    }

    fun calc(input: Int?) = 2 * (input ?: 1)

    @Test
    fun elvis() = calc(null) shouldBe 2
}