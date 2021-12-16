package Handlers;

import Manager.SystemManager;

public class ExitAction implements IActionHandler
{
    private SystemManager manager;

    public ExitAction(SystemManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void execute()
    {
        this.manager.database.saveCurrentRecipe(this.manager.current_recipe);
        this.manager.database.saveProducts(this.manager.storage.getProducts());
        this.manager.database.saveRecipes(this.manager.storage.getRecipes());

        System.exit(0);
    }
}
