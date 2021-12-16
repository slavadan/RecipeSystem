package Handlers;

import Manager.ExtendedManager;
import Manager.SystemManager;
import Recipe.Product.Product;
import Recipe.Recipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class ExtendedWatch extends WatchCatalogAction
{
    private HashMap<Integer, IActionHandler> actions;
    private ExtendedManager manager;

    public ExtendedWatch(ExtendedManager manager, HashMap<Integer, IActionHandler> actions)
    {
        super(manager, actions);
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        HashMap<String, ArrayList<Recipe>> recipes = this.manager.findFullRecipes();
        Scanner console = new Scanner(System.in);
        int choose = 0;

        System.out.println("Продуктов хватает на " + recipes.get("Full").size() + " блюд");
        System.out.println("Нужно купить продукты для " + recipes.get("NotFull").size() + " блюд");

        System.out.println(
                "1.Показать рецепты, которые можно приготовить\n" +
                "2.Показать рецепты, которые нельзя приготовить\n"+
                "0.Выйти в меню"
        );

        choose = console.nextInt();

        switch (choose)
        {
            case 1:
                for(Recipe recipe: recipes.get("Full"))
                    System.out.println(recipe.recipe_name);
                    actions.get(0).execute();
                break;

            case 2:
                for(Recipe recipe: recipes.get("NotFull"))
                    System.out.println("id:" + recipe.getId() + " -> " + recipe.recipe_name);

                System.out.println("1.Поменять продукт\n0.Выйти в меню");
                choose = console.nextInt();

                if(choose == 1) { changeProduct(recipes.get("NotFull")); }
                else
                    actions.get(0).execute();

                break;

            case 0:
                actions.get(0).execute();
        }

    }

    private void changeProduct(ArrayList<Recipe> recipes)
    {
        ArrayList<Product> storage_products = this.manager.storage.getProducts();
        Scanner console = new Scanner(System.in);
        int choose = 0;

        for(Recipe recipe: recipes)
            System.out.println("id:" + recipe.getId() + " -> " + recipe.recipe_name);

        System.out.println("Введите номер рецепта");
        choose = console.nextInt();

        Recipe current_recipe = null;
        for(Recipe recipe: recipes)
            if (recipe.getId() == choose)
                current_recipe = recipe;

        if(current_recipe == null)
            actions.get(0).execute();

        System.out.println("Вы выбрали " + current_recipe.recipe_name);

        System.out.println("Доступные продукты рецепта:");
        for(Product product: current_recipe.products)
            System.out.println(product.name + "; ");

        System.out.println("Введите имя продукта, который хотите поменять");
        String base_name = console.nextLine();
        base_name = console.nextLine();

        System.out.println("Доступные продукты на складе:");
        for(Product product: storage_products)
            System.out.print(product.name + "количество -> " + product.count + "; ");

        System.out.println("\nВведите имя продукта, на который хотите поменять");
        String change_name = console.nextLine();

        System.out.println("\nВведите количество");
        choose = console.nextInt();

        for(int i = 0; i < current_recipe.products.size(); ++i)
            if (current_recipe.products.get(i).name.equals(base_name))
                current_recipe.products.remove(i);

        current_recipe.products.add(new Product(change_name, choose));

        actions.get(0).execute();
    }
}
