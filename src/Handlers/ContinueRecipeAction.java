package Handlers;

import Manager.SystemManager;

import java.util.HashMap;

public class ContinueRecipeAction implements IActionHandler
{
    private HashMap<Integer, IActionHandler> actions;
    private SystemManager manager;

    public ContinueRecipeAction(SystemManager manager, HashMap<Integer, IActionHandler> actions)
    {
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        if (this.manager.current_recipe != null)
        {
            System.out.println("Обнаружен незаконченный рецепт");
            actions.get(1).execute();
        }
        else
        {
            System.out.println("Рецепт не найден");
            actions.get(2).execute();
        }

    }
}
