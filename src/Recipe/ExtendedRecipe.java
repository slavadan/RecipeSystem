package Recipe;


public class ExtendedRecipe extends Recipe
{
    protected Recipe wrapper;
    public int count = 0;
    public ExtendedRecipe(Recipe recipe, int count)
    {
        super(recipe.getId(), recipe.recipe_name, recipe.type, recipe.stages, recipe.products);
        this.count = count;
    }
}
