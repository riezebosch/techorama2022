package demo

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

// https://kotlinlang.org/docs/generics.html
class Generics {
    interface Consumer<in T> {
        fun foo(input: T)
    }

    class ConsumerImpl<T> : Consumer<T> {
        override fun foo(input: T) = Unit
    }

    interface Producer<out T> {
        fun foo(): T
    }

    class ProducerImpl<T> : Producer<T> {
        override fun foo(): T = TODO()
    }

    open class Base
    class Derived : Base()

    @Test
    fun test() {
        val i1: Consumer<Derived> = ConsumerImpl<Base>()
//        i1.foo(Base())
        i1.foo(Derived())
    }

    @Test
    fun test2() {
        val i1: Producer<Base> = ProducerImpl<Derived>()
        assertFailsWith<NotImplementedError> {
            val result1: Base = i1.foo()
//          val result2: Derived = i1.foo()
        }
    }
}