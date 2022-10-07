import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence-scope/yield.html
class Iterators {
    fun iterator() = sequence {

        yieldAll(0..5 step 2)

        var i = 100
        while(true) {
            yield(++i)
        }
    }

    @Test
    fun yield() {
        assertEquals(listOf(0, 2, 4, 101, 102, 103, 104), iterator().take(7).toList())
    }
}