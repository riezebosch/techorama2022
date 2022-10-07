package callbacks

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class Interface {
    interface OnClickListener {
        fun onClick()
    }

    class View {
        private lateinit var click: OnClickListener

        fun setOnClickListener(l: OnClickListener) {
            click = l
        }

        fun invoke() = click.onClick()
    }

    @Test
    fun test() {
        var isClicked = false

        val view = View()
        view.setOnClickListener(object : OnClickListener {
            override fun onClick() {
                isClicked = true
            }
        })

        view.invoke()
        assertTrue(isClicked)
    }
}