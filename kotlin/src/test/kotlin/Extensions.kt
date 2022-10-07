import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Extensions {
    @Test
    fun test() {
        assertEquals("one", 1.something())
        assertEquals("two", 2.something())
        assertEquals("three", 3.something())
    }
}

private fun Int.something() = when(this) {
    1 -> "one"
    2 -> "two"
    3 -> "three"
    else -> "unknown number"
}
