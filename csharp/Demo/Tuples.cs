using System;
using FluentAssertions;
using Xunit;

namespace Demo;

public class Tuples
{
    private static (int a, string b) Create() =>
        (1, "b");

    [Fact]
    public void Pair()
    {
        var (a, b) = Create();

        a.Should().Be(1);
        b.Should().Be("b");
    }

    [Fact]
    public void Triple()
    {
        var (a, b, _) = new Tuple<int, string, bool>(1, "b", false);

        a.Should().Be(1);
        b.Should().Be("b");
    }

    private record Data(int X, string Y, bool Z);
    
    [Fact]
    public void Record()
    {
        var (a, _, c) = new Data(1, "b", true);

        a.Should().Be(1);
        c.Should().BeTrue();
    }
}