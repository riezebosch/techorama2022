package demo

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotContain
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty
import kotlin.test.assertEquals

// https://kotlinlang.org/docs/properties.html
class Properties {
    class Simple {
        var property: Int = 0 // note: avoid var, choose immutable
    }

    @Test
    fun simple() {
        val data = Simple()
        data.property = 1

        assertEquals(1, data.property)
    }

    class Full {
        var myproperty: Int = 0
            get() = field + 10
            set(value) {
                field = value + 5
            }
    }

    @Test
    fun full() {
        val data = Full()
        data.myproperty = 1

        assertEquals(16, data.myproperty)
    }

    class Computed {
        var myproperty: Int = 0
            get() = field++
    }

    @Test
    fun computed() {
        val data = Computed()
        assertEquals(0, data.myproperty)
        assertEquals(1, data.myproperty)
        assertEquals(2, data.myproperty)
    }

    class ReadOnly(val myproperty: Int = 0)

    @Test
    fun readonly() {
        val data = ReadOnly(4)
        data.myproperty shouldBe 4
    }

    data class DataClass(val myproperty: Int)

    @Test
    fun data() {
        DataClass(4).toString() shouldBe "DataClass(myproperty=4)"
        ReadOnly(4).toString() shouldNotContain "myproperty"
    }

    @Test
    fun copy() {
        val origin = DataClass(4)
        val copy = origin.copy(myproperty = 3)

        copy shouldBe DataClass(3)
    }

    class Lazy {
        var count = 0
        val myproperty: Int by lazy {
            ++count
        }
    }

    @Test
    fun lazy() {
        val data = Lazy()
        assertEquals(0, data.count)

        assertEquals(1, data.myproperty)
        assertEquals(1, data.myproperty)
        assertEquals(1, data.myproperty)
        assertEquals(1, data.myproperty)
        assertEquals(1, data.count)
    }

    class Observable {
        var count = 0
        var myproperty: Int by observable(0, ) {
            prop, old, new ->
            ++count
        }
    }

    @Test
    fun observable() {
        val data = Observable()
        assertEquals(0, data.myproperty)
        assertEquals(0, data.count)

        data.myproperty = 1
        data.myproperty = 1
        data.myproperty = 1
        data.myproperty = 1
        data.myproperty = 1
        data.myproperty = 1

        assertEquals(1, data.myproperty)
        assertEquals(6, data.count)
    }

    class Delegate {
        val myproperty: Int by MyDelegate()
    }

    class MyDelegate
    {
        operator fun getValue(delegate: Delegate, property: KProperty<*>) = 4
    }

    @Test
    fun delegate() {
        val data = Delegate()
        assertEquals(4, data.myproperty)
    }

    @Test
    fun initializer() {
        val simple = Simple().apply {
            property = 3
        }

        simple.property shouldBe 3
    }

    @Test
    fun scoping() {
        val item = Computed()
        item.run {
            myproperty
            myproperty
            myproperty
            myproperty
        }

        with(item) {
            myproperty shouldBe 4
        }
    }
}
