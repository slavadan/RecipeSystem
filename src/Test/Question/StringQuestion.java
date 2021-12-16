package Test.Question;

import java.util.Scanner;

public class StringQuestion extends BaseQuestion
{
    public StringQuestion(String name, String description) {
        super(name, description);
    }
    @Override

    public Object ask()
    {
        Scanner console = new Scanner(System.in);

        super.ask();

        String value = console.nextLine();

        return value;
    }
}
