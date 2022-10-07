import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// https://developer.android.com/kotlin/coroutines/test
@OptIn(ExperimentalCoroutinesApi::class)
class Coroutines {
    private suspend fun fetchData(): String {
        delay(1000L)
        return "Hello world"
    }

    @Test
    fun test1() = runTest {
        val data = fetchData()
        assertEquals("Hello world", data)
    }

    @Test
    fun test2() = runTest {
        val repository = mutableListOf<Int>()

        launch { repository.add(3) }
        launch { repository.add(4) }

        assertEquals(emptyList(), repository)
    }

    @Test
    fun test3() = runTest {
        val repository = mutableListOf<Int>()

        launch { repository.add(3) }
        launch { repository.add(4) }
        advanceUntilIdle()

        assertEquals(listOf(3, 4), repository)
    }

    @Test
    fun test4() = runTest(UnconfinedTestDispatcher()) {
        val repository = mutableListOf<Int>()

        launch { repository.add(3) }
        launch { repository.add(4) }

        assertEquals(listOf(3, 4), repository)
    }

    @Test
    fun test5() = runTest(UnconfinedTestDispatcher()) {
        val repository = mutableListOf<Int>()

        launch {
            repository.add(3)
            delay(1)

            repository.add(4)
        }

        assertEquals(listOf(3), repository)
    }

    @Test
    fun test6() = runTest(UnconfinedTestDispatcher()) {
        val repository = mutableListOf<Int>()

        launch {
            repository.add(3)
            delay(1)

            repository.add(4)
        }

        advanceUntilIdle()
        assertEquals(listOf(3, 4), repository)
    }
}