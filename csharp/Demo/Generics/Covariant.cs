using Xunit;

namespace Demo.Generics;

public class Covariant
{
    private interface IDo<out T>
    {
        T Foo();
    }

    private class Do<T> : IDo<T>
    {
        public T Foo() => default;
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
        IDo<Base>    i3 = new Do<Derived>();
        // IDo<Derived> i4 = new Do<Base>();
        
        Base b = i3.Foo();
        // Derived d = i3.Foo();
    }
}