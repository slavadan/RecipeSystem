package Handlers;

import Manager.SystemManager;

import java.util.HashMap;
import java.util.Scanner;

public class MenuAction implements IActionHandler
{
    private HashMap<Integer, IActionHandler> actions;
    private SystemManager manager;

    public MenuAction(SystemManager manager, HashMap<Integer, IActionHandler> actions)
    {
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        Scanner console = new Scanner(System.in);

        int choose;

        System.out.println(
                "1. Продолжить готовку.\n" +
                "2. Выбрать новый рецепт.\n" +
                "3. Посмотреть каталог рецептов\n" +
                "4. Зарегистрировать продукты\n" +
                        "0. Выйти из системы.\n");

        choose = console.nextInt();

        actions.get(choose).execute();
    }

    public void addAction(int key, IActionHandler action) { actions.put(key, action); }
}
