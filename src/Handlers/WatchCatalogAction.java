package Handlers;

import Manager.SystemManager;
import Recipe.Product.Product;
import Recipe.Recipe;
import Storage.Storage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class WatchCatalogAction implements IActionHandler
{
    private HashMap<Integer, IActionHandler> actions;
    private SystemManager manager;

    public WatchCatalogAction(SystemManager manager, HashMap<Integer, IActionHandler> actions)
    {
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        ArrayList<Recipe> recipes = manager.storage.getRecipes();

        showRecipes(recipes);

        chooseRecipe();
    }

    public void checkProductsInStorage(Recipe recipe)
    {
        for(Product product: recipe.products)
        {
            System.out.println("Требуется в рецепте: " + product.name + " " + product.count);

            Product storage_product = manager.storage.getProduct(product.name);

            if (storage_product != null)
                System.out.println("Есть на складе: " + storage_product.count);
            else
                System.out.println("На складе нет такого продукта!");
        }
    }

    public Recipe chooseRecipeByID()
    {
        Scanner console = new Scanner(System.in);

        System.out.println("Введите номер рецепта");

        int choose = console.nextInt();

        for(Recipe recipe: this.manager.storage.getRecipes())
            if (recipe.getId() == choose)
                return recipe;

        return null;
    }

    public void showRecipes(ArrayList<Recipe> recipes)
    {
        for(Recipe recipe: recipes)
        {
            System.out.println(recipe.getId() + " " + recipe.getRecipeName());
        }
    }

    public void chooseRecipe()
    {
        Scanner console = new Scanner(System.in);

        System.out.println("1. Посмотреть рецепт.\n0. Выйти в меню.");

        int choose = console.nextInt();

        if (choose == 1)
        {
            Recipe choose_recipe = chooseRecipeByID();

            System.out.println(choose_recipe.recipe_name);

            checkProductsInStorage(choose_recipe);

        }
        actions.get(0).execute();
    }
}
