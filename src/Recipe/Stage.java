package Recipe;


import java.io.Serializable;


public class Stage implements Serializable
{
    private final String description;

    public Stage(String description)
    {
        this.description = description;
    }

    public String getStageDescription() { return description; }
}
