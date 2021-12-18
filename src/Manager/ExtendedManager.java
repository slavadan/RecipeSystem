package Manager;


import Data.IDatabase;
import Recipe.Product.Product;
import Recipe.Recipe;
import Storage.Storage;
import Test.Test;
import java.util.ArrayList;
import java.util.HashMap;


public class ExtendedManager extends SystemManager
{

    public ExtendedManager(Storage storage, IDatabase database, Test test)
    {
        super(storage, database, test);
    }

    public ArrayList<Recipe> sort()
    {
        ArrayList<Recipe> full = new ArrayList<Recipe>();
        ArrayList<Recipe> not_full = new ArrayList<Recipe>();

        ArrayList<Recipe> recipes = storage.getRecipes();

        for(int i = 0; i < recipes.size(); ++i)
        {
            int aim = recipes.get(i).products.size();
            int count = 0;

            for(Product recipe_product: recipes.get(i).products)
            {
                Product storage_product = storage.getProduct(recipe_product.name);

                if (storage_product == null)
                    continue;

                if (storage_product.count >= recipe_product.count)
                    count += 1;
            }

            if (count >= aim)
                full.add(recipes.get(i));
            else
                not_full.add(recipes.get(i)) ;
        }

        ArrayList<Recipe> result = new ArrayList<Recipe>();
        result.addAll(full);
        result.addAll(not_full);

        return result;
    }


}