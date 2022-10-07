using Xunit;

namespace Demo.Generics;

public class Invariant
{
    private interface IDo<T>
    {
        T Foo(T input);
    }

    private class Do<T> : IDo<T>
    {
        public T Foo(T input) => input;
    }

    private class Base
    {
    }

    private class Derived : Base
    {
    }

    [Fact]
    public void Test()
    {
        IDo<Base>    i1 = new Do<Base>();
        IDo<Derived> i2 = new Do<Derived>();
        // IDo<Base>    i3 = new Do<Derived>();
        // IDo<Derived> i4 = new Do<Base>();
    }
}