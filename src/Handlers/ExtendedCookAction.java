package Handlers;

import Manager.SystemManager;
import Recipe.Product.Product;
import Recipe.ExtendedRecipe;
import java.util.HashMap;
import java.util.Scanner;

public class ExtendedCookAction extends CookAction
{
    private HashMap<Integer, IActionHandler> actions;//0 - menu 1 - cook
    private SystemManager manager;

    public ExtendedCookAction(SystemManager manager, HashMap<Integer, IActionHandler> actions) {
        super(manager, actions);
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
            this.manager = manager;
            this.actions = actions;
        }

        ExtendedRecipe recipe = (ExtendedRecipe) this.manager.current_recipe;

        while(recipe.count != 0)
        {
            System.out.println("Осталость приготовить " + recipe.count);
            this.cook(recipe);
        }

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

    private void cook(ExtendedRecipe recipe)
    {
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
        recipe.count -= 1;
        actions.get(0).execute();
    }
}
