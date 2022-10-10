import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Patterns {
    interface Result
    data class Ok<T>(val data: T) : Result
    data class Failed(val message: String) : Result

    private fun match(result: Result) = when (result) {
        is Ok<*> -> { // type erasure
            when (result.data) { // Any?
                is Int ->  if (result.data % 2 == 0) 1 else 4 // smart cast
                is Boolean -> when {
                    result.data -> 2
                    else -> 4
                }
                else -> 4
            }
        }
        is Failed -> 3
        else -> 4
    }

    @Test
    fun odd() =
        assertEquals(4, match(Ok(3)))

    @Test
    fun even() =
        assertEquals(1, match(Ok(2)))

    @Test
    fun isTrue() =
        assertEquals(2, match(Ok(true)))

    @Test
    fun isFalse() =
        assertEquals(4, match(Ok(false)))

    @Test
    fun failed() =
        assertEquals(3, match(Failed("because")))

    @Test
    fun other() =
        assertEquals(4, match(object: Result {}))
}