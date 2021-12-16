package Data;

import Recipe.Product.Product;
import Recipe.Recipe;

import java.io.*;
import java.util.ArrayList;

public class LocalDataBase implements IDatabase
{
    private final String recipe_file;
    private final String current_recipe_file;
    private final String products_file;

    public LocalDataBase(String recipe_file, String current_recipe_file, String products_file)
    {
        this.recipe_file = recipe_file;
        this.current_recipe_file = current_recipe_file;
        this.products_file = products_file;
    }

    @Override
    public ArrayList<Recipe> loadRecipes()
    {
        ArrayList<Recipe> recipes;

        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.recipe_file)))
        {
            recipes = ((ArrayList<Recipe>)stream.readObject());
            stream.close();
            return recipes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Product> loadProducts()
    {
        ArrayList<Product> products;

        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.products_file)))
        {
            products = ((ArrayList<Product>)stream.readObject());
            stream.close();
            return products;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Recipe loadCurrentRecipe()
    {
        Recipe recipe;

        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.current_recipe_file)))
        {
            recipe = (Recipe)stream.readObject();

            if (recipe == null)//поменять
                return null;
            else
                return recipe;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void saveProducts(ArrayList<Product> products)
    {
        try(ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(this.products_file, false)))
        {
            stream.writeObject(products);
            stream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRecipes(ArrayList<Recipe> recipes)
    {
        try(ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(this.recipe_file, false)))
        {
            stream.writeObject(recipes);
            stream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCurrentRecipe(Recipe recipe)
    {
        try(ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(this.current_recipe_file, false)))
        {
            stream.writeObject(recipe);
            stream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
