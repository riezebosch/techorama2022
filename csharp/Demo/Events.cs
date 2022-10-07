using System;
using FluentAssertions;
using Xunit;

namespace Demo;

public class Events
{
    private bool _isRaised;

    private delegate void Raise(object sender, EventArgs e);

    private event Raise OnRaised;

    [Fact]
    public void TestIsRaised()
    {
        OnRaised += new Raise(DoRaise);
        OnRaised.Invoke(this, EventArgs.Empty);

        _isRaised.Should().BeTrue();
    }

    private void DoRaise(object sender, EventArgs e)
    {
        _isRaised = true;
    }
}