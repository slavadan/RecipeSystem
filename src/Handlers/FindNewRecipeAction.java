package Handlers;

import Manager.SystemManager;
import Recipe.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FindNewRecipeAction implements IActionHandler
{
    private HashMap<Integer, IActionHandler> actions;//0 - menu 1 - cook
    private SystemManager manager;

    public FindNewRecipeAction(SystemManager manager, HashMap<Integer, IActionHandler> actions)
    {
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        ArrayList<Recipe> results = this.manager.test.launchTest();

        Scanner console = new Scanner(System.in);

        int choose;

        if (results.size() == 0)
        {
            System.out.println("Рецепты не найдены");
            actions.get(0).execute();
        }

        for(Recipe recipe: results)
            System.out.println(recipe.getId() + " " + recipe.recipe_name);

        System.out.println("Введите номер рецепта");

        choose = console.nextInt();

        for(Recipe recipe: results)
            if(recipe.getId() == choose)
                this.manager.current_recipe = recipe;

        actions.get(1).execute();
    }
}
