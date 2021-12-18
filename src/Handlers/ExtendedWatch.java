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
        this.manager.storage.setRecipes(this.manager.sort());
        super.execute();
    }

    @Override
    public void checkProductsInStorage(Recipe recipe)
    {
        for(Product product: recipe.products)
        {
            System.out.println("Требуется в рецепте: " + product.name + " " + product.count);
            Product storage_product = manager.storage.getProduct(product.name);

            if (storage_product != null)
                System.out.println("Есть на складе: " + storage_product.count);

            if (storage_product.count < product.count || storage_product == null)
                offerChangeProductInRecipe(recipe, product.name);
        }
    }

    private void offerChangeProductInRecipe(Recipe recipe, String change_product_name)
    {
        ArrayList<Product> storage_products = this.manager.storage.getProducts();

        Scanner console = new Scanner(System.in);
        int choose;

        System.out.println("""
                Вы можете выбрать другой продукт!
                1.Да
                2.Нет
                0.Выйти в меню"""
        );

       choose = console.nextInt();

       switch (choose)
       {
           case 1:
               System.out.println("Доступные продукты на складе:");
               for(Product product: storage_products)
                   System.out.print(product.name + "количество -> " + product.count + "; ");

               System.out.println("\nВведите имя продукта, на который хотите поменять");
               String storage_product_name = console.nextLine();

               System.out.println("\nВведите количество");
               choose = console.nextInt();

               for(int i = 0; i < recipe.products.size(); ++i)
                   if (recipe.products.get(i).name.equals(change_product_name))
                       recipe.products.remove(i);

               recipe.products.add(new Product(storage_product_name, choose));
               break;

           case 2:
               return;

           case 0:
               actions.get(choose).execute();
       }
    }
}