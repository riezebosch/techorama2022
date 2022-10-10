using System;
using FluentAssertions;
using Xunit;

namespace Demo;

public class Patterns
{
    private interface IResult
    {
    }

    private record Ok<T>(T Data) : IResult;

    private record Failed : IResult;

    [Fact]
    public void Match()
    {
        IResult o = new Ok<int>(3);
        if (o is Ok<int> d)
        {
            d.Data.Should().Be(3);
        }
    }

    private static int Do(IResult result) => 
        result switch
        {
            Ok<int> o when o.Data % 2 == 0 => 1,
            Ok<bool> { Data: true } => 2,
            Failed => 3,
            _ => 4
        };

    [Fact]
    public void Switch()
    {
        var result = new Ok<int>(3);
        Do(result)
            .Should()
            .Be(4);
    }
}