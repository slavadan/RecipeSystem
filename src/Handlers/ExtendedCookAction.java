package Handlers;

import Manager.SystemManager;
import Recipe.ExtendedRecipe;

import java.util.HashMap;

public class ExtendedCookAction extends CookAction
{
    private HashMap<Integer, IActionHandler> actions;
    private SystemManager manager;

    public ExtendedCookAction(SystemManager manager, HashMap<Integer, IActionHandler> actions)
    {
        super(manager, actions);
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        ExtendedRecipe recipe = (ExtendedRecipe) this.manager.current_recipe;

        while(recipe.count != 0)
        {
            super.execute();
            recipe.count -= 1;
            manager.current_recipe = manager.storage.getRecipe(recipe.getId());
        }

        System.out.println("Все порции приготовлены!");
        actions.get(0).execute();
    }
}