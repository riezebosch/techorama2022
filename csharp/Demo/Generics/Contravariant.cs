using Xunit;

namespace Demo.Generics;

public class Contravariant
{
    private interface IDo<in T>
    {
        void Foo(T input);
    }

    private class Do<T> : IDo<T>
    {
        public void Foo(T input)
        {
        }
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
        IDo<Derived> i4 = new Do<Base>();
        
        // i4.Foo(new Base());
        i4.Foo(new Derived());
    }
}