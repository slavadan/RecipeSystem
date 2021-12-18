package Storage;

import Recipe.Product.Product;
import Recipe.Recipe;
import java.util.ArrayList;


public class Storage
{
    private ArrayList<Product> products;
    private ArrayList<Recipe> recipes;

    public Storage(ArrayList<Product> products, ArrayList<Recipe> recipes)
    {
        this.products = products;
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() { return this.recipes; }
    public ArrayList<Product> getProducts() { return this.products; }

    public void setProducts(ArrayList<Product> products) { this.products = products; }
    public void setRecipes(ArrayList<Recipe> recipes) { this.recipes = recipes; }
    public void addProduct(String input_name, int input_count)
    {
        for (Product product: this.products)
        {
            if (product.name.equals(input_name))
            {
                product.count += input_count;
                return;
            }
        }

        Product product = new Product(input_name, input_count);

        this.products.add(product);
    }
    public void eraseProduct(Product input)
    {
        for (Product product: this.products)
        {
            if (product.name.equals(input.name))
            {
                product.count -= input.count;
                return;
            }
        }
    }

    public Recipe getRecipe(int id)
    {
        for(Recipe recipe: recipes)
            if (recipe.getId() == id)
                return new Recipe(recipe);

        return null;
    }

    public Product getProduct(String name)
    {
        for(Product product: products)
            if (product.name.equals(name))
                return product;

        return null;
    }
}
