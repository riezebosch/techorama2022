using System.Collections.Generic;
using System.Threading.Tasks;
using FluentAssertions;
using Xunit;

namespace Demo;

public class Streams
{
    [Fact]
    public async Task Test()
    {
        var items = new List<int>();
        await foreach (var i in Fetch())
        {
            items.Add(i);
        }

        items.Should().BeEquivalentTo(new[] { 20, 40, 60, 80 });
    }

    private static async IAsyncEnumerable<int> Fetch()
    {
        var i = 0;
        yield return await Fetch(++i);
        yield return await Fetch(++i);
        yield return await Fetch(++i);
        yield return await Fetch(++i);
    }

    private static async Task<int> Fetch(int i)
    {
        await Task.Delay(500);
        return i * 20;
    }
}