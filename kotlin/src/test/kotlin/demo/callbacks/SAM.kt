package demo.callbacks

import demo.callbacks.SAM.OnClickListener
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class SAM {

    fun interface OnClickListener {
        fun onClick()
    }

    class View {
        private lateinit var click: OnClickListener

        fun setOnClickListener(l: OnClickListener) {
            click = l
        }

        fun invoke() {
            click.onClick()
        }
    }

    @Test
    fun test() {
        var isClicked = false

        val view = View().apply {
            setOnClickListener {
                isClicked = true
            }
        }

        view.invoke()
        assertTrue(isClicked)
    }

    @Test
    fun test3() {
        var isClicked = false

        val view = View()
        view.setOnClickListener { isClicked = true }

        view.invoke()
        assertTrue(isClicked)
    }

    @Test
    fun test4() {
        var isClicked = false
        val view = View().apply {
            setOnClickListener { isClicked = true }
        }

        view.invoke()
        assertTrue(isClicked)
    }
}