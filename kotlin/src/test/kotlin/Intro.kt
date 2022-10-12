import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Intro {
    @Test
    fun test() = 4 minus 2 shouldBe 2

    private infix fun Int.minus(rhs: Int) = this - rhs

    @Test
    fun map() {
        val items = mapOf(1 to "one", 2 to "two", 3 to "three", 4.to("four"), Pair(5, "five"))
        items[1] shouldBe "one"
    }

    @Test
    fun nullSafe() {
        something(2)

        val input: Int? = null
        something(input!!)
    }

    private fun something(input: Int?) {
        if (input != null) {
            input
        }
    }

    @Test
    fun properties() {
        val data = Data(4)
        data.myproperty shouldBe 4
        data.other shouldBe ""

        data.myproperty = 5
    }

    @Test
    fun data() {
        val data = DataClass(3, "something")
        val updated = data.copy(myproperty = 5)

        updated shouldBe DataClass(5, "something")
    }

    data class DataClass(val myproperty: Int = 0, val other: String = "")

//    @Test
//    suspend fun async() = runTest {
//        delay(400)
//    }
}

class Data(var myproperty: Int, val other: String = "") {
    var full: String = ""
        get() = field
        set(value) {
            field = value
        }
}
