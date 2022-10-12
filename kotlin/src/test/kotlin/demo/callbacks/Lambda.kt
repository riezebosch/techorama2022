package demo.callbacks

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

typealias onClick = () -> Unit

class Lambda {
    class View {
        private lateinit var click: onClick

        fun setOnClickListener(l: onClick) {
            click = l
        }

        fun invoke() = click()
    }

    @Test
    fun test3() {
        var isClicked = false
        val view = View().apply {
            setOnClickListener { isClicked = true }
        }

        view.invoke()
        assertTrue(isClicked)
    }
}