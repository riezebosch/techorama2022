namespace Demo;

public class Properties
{
    private int _full;
    public int Full
    {
        get { return _full; }
        set { _full = value; }
    }
    
    // C# 3.0
    public int AutoImplemented { get; set; }

    // C# 3.0
    public int ReadOnly { get; private set; }

    // C# 6
    public int ReadOnlyInitializer { get; } = 5;

    // C# 6
    public int Computed => 5;
}