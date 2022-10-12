package demo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// https://kotlinlang.org/docs/flow.html
@OptIn(ExperimentalCoroutinesApi::class)
class Streams {
    @Test
    fun test() = runTest {
        val items = mutableListOf<Int>()
        fetch().collect {
            items.add(it)
        }

        assertEquals(listOf(20, 40, 60, 80), items)
    }

    private fun fetch(): Flow<Int> = flow {
        var i = 0
        emit(fetch(++i))
        emit(fetch(++i))
        emit(fetch(++i))
        emit(fetch(++i))
    }

    private suspend fun fetch(i: Int): Int {
        delay(200)
        return i * 20
    }

    @Test
    fun released() = runTest {
        val items = mutableListOf<Int>()
        launch {
            for (i in 1..8) {
                items.add(99)
                delay(200)
            }
        }

        fetch().collect {
            items.add(it)
        }

        advanceUntilIdle()
        assertEquals(listOf(99, 20, 99, 40, 99, 60, 99, 80, 99, 99, 99, 99), items)
    }

}