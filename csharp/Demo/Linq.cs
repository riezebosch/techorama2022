using System.Linq;
using FluentAssertions;
using Xunit;

namespace Demo;

public class Linq
{
    [Fact]
    public void MethodSyntax()
    {
        var items = new[] { 1, 2, 3, 4, 5, 6 };
        items
            .Where(i => i % 2 == 0)
            .Should()
            .BeEquivalentTo(new[] { 2, 4, 6 });
    }
    
    [Fact]
    public void QuerySyntax()
    {
        var items = new[] { 1, 2, 3, 4, 5, 6 };
        var result = from i in items
            where i % 2 == 0
            select i;
        
        result.Should()
            .BeEquivalentTo(new[] { 2, 4, 6 });
    }
}