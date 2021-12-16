package Data;

import Recipe.Product.Product;
import Recipe.Recipe;

import java.util.ArrayList;

public interface IDatabase
{
    ArrayList<Recipe> loadRecipes();
    ArrayList<Product> loadProducts();
    Recipe loadCurrentRecipe();
    void saveProducts(ArrayList<Product> products);
    void saveRecipes(ArrayList<Recipe> recipes);
    void saveCurrentRecipe(Recipe recipe);
}
