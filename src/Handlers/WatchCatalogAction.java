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

        Scanner console = new Scanner(System.in);

        int choose;

        for(Recipe recipe: recipes)
        {

            System.out.println(recipe.getId() + " " + recipe.getRecipeName());

        }

        System.out.println("1. Посмотреть рецепт.\n0. Выйти в меню.");
        choose = console.nextInt();

        if (choose == 1)
        {
            System.out.println("Введите номер рецепта");
            choose = console.nextInt();

            Recipe choose_recipe = null;
            for(Recipe recipe: recipes)
                if (recipe.getId() == choose)
                    choose_recipe = recipe;

            System.out.println(choose_recipe.recipe_name);

            for(Product product: choose_recipe.products)
            {
                System.out.println("Требуется в рецепте: " + product.name + " " + product.count);

                Product storage_product = manager.storage.getProduct(product.name);

                if (storage_product != null)
                    System.out.println("Есть на складе: " + storage_product.count);
                else
                    System.out.println("На складе нет такого продукта!");
            }

        }
        actions.get(0).execute();

    }
}
