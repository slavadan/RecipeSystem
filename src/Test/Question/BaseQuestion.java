package Test.Question;


public abstract class BaseQuestion
{
    public final String name;
    private final String description;

    public BaseQuestion(String name, String description)
    {
        this.description = description;
        this.name = name;
    }

    public Object ask()
    {
        System.out.println(description);
        return null;
    }
}
