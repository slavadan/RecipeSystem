package Handlers;

import Manager.SystemManager;
import Recipe.Product.Product;
import Recipe.Recipe;

import java.awt.print.PageFormat;
import java.util.HashMap;
import java.util.Scanner;


public class CookAction implements IActionHandler
{
    private HashMap<Integer, IActionHandler> actions;
    private SystemManager manager;

    public CookAction(SystemManager manager, HashMap<Integer, IActionHandler> actions)
    {
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        if(!this.isEnoughProducts())
        {
            System.out.println("Недостаточно продуктов");
            this.actions.get(0).execute();
        }

        Recipe recipe = this.manager.current_recipe;
        Scanner console = new Scanner(System.in);

        int choose;

        while(!recipe.isCompleted())
        {
            System.out.println(recipe.getCurrentStage());
            System.out.println("1. Выполнить этап.\n2. Выйти в меню.");

            choose = console.nextInt();

            switch (choose)
            {
                case 1 -> recipe.completeStage();
                case 2 -> actions.get(0).execute();
            }
        }

        for(int i = 0; i < recipe.products.size(); ++i)
        {
            this.manager.storage.eraseProduct(
                    recipe.products.get(i)
            );
        }
        System.out.println("Блюдо приготовлено");
        this.manager.current_recipe = null;
        this.actions.get(0).execute();
    }

    private boolean isEnoughProducts()
    {
        int counter = 0;

        for(Product recipe_product: this.manager.current_recipe.products)
            for(Product storage_product: this.manager.storage.getProducts())
                if (recipe_product.count <= storage_product.count & recipe_product.name.equals(storage_product.name))
                    counter += 1;

        return counter == this.manager.current_recipe.products.size();
    }
}
