package Recipe;

import Recipe.Product.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Recipe implements Serializable
{
    public final String recipe_name;
    public String type;
    private int id;
    public Queue<Stage> stages;
    public final ArrayList<Product> products;

    public Recipe(int id, String name, String type, Queue<Stage> stages, ArrayList<Product> products)
    {
        this.id = id;
        this.recipe_name = name;
        this.stages = stages;
        this.products = products;
        this.type = type;
    }

    public Recipe(Recipe recipe)
    {
        this.id = recipe.id;
        this.recipe_name = new String(recipe.recipe_name);
        this.stages = new LinkedList<Stage>(recipe.stages);
        this.products = new ArrayList<>(recipe.products);
        this.type = new String(recipe.type);
    }

    public String getCurrentStage() { return this.stages.element().getStageDescription(); }
    public void completeStage() { this.stages.poll(); }
    public boolean isCompleted() { return this.stages.isEmpty(); }
    public String getRecipeName() { return this.recipe_name; }
    public int getId() { return this.id; }
}
