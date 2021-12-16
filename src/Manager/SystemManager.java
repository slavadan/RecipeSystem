package Manager;

import Data.IDatabase;
import Recipe.Recipe;
import Storage.Storage;
import Test.Test;


public class SystemManager
{
    public Storage storage;
    public IDatabase database;
    public Recipe current_recipe = null;

    public Test test = null;

    public SystemManager(Storage storage, IDatabase database, Test test)
    {
        this.storage = storage;
        this.database = database;
        this.test = test;
        this.current_recipe = this.database.loadCurrentRecipe();
    }

    public void setCurrentRecipe(int recipeID)
    {
        this.current_recipe = this.storage.getRecipe(recipeID);
    }
}
