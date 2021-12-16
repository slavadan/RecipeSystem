package Handlers;

import Manager.SystemManager;
import Recipe.Product.Product;

import java.util.HashMap;
import java.util.Scanner;

public class RegisterProductAction implements IActionHandler
{
    private HashMap<Integer, IActionHandler> actions;
    private SystemManager manager;

    public RegisterProductAction(SystemManager manager, HashMap<Integer, IActionHandler> actions)
    {
        this.manager = manager;
        this.actions = actions;
    }

    @Override
    public void execute()
    {
        Scanner console = new Scanner(System.in);

        String input_name;
        int input_count;

        System.out.println("Введите название продукта");
        input_name = console.nextLine();
        System.out.println("Введите количество");
        input_count = console.nextInt();

        this.manager.storage.addProduct(input_name, input_count);

        actions.get(1).execute();
    }
}
