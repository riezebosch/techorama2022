package demo

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class Collections {
    @Test
    fun immutable() {
        val items = listOf(1, 2, 3, 4, 5)
//        items[1] = 10

        items shouldNotBe empty()
    }

    @Test
    fun mutable1() {
        val items = mutableListOf(1, 2, 3, 4, 5)
        items[1] = 10

        items shouldBe listOf(1, 10, 3, 4, 5)
    }

    @Test
    fun mutable2() {
        val items = listOf(1, 2, 3, 4, 5).toMutableList()
        items[1] = 10

        items shouldBe listOf(1, 10, 3, 4, 5)
    }

    @Test
    fun empty() {
        val items = emptyList<Int>()
        items shouldBe emptyList()
    }

    @Test
    fun map() {
        val items = mapOf(1 to "one", 2 to "two", 3 to "three")
        items[1] shouldBe "one"
    }

    @Test
    fun except() {
        val items = listOf(1, 2, 3, 4, 5)
        items.filter { it != 3 } shouldBe listOf(1, 2, 4, 5)
    }

    @Test
    fun project() {
        val items = listOf(1, 2, 3, 4, 5)
        items.map { it * 3 } shouldBe listOf(3, 6, 9, 12, 15)
    }
}