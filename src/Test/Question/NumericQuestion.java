package Test.Question;

import java.util.Scanner;


public class NumericQuestion extends BaseQuestion
{
    public NumericQuestion(String name, String description) {
        super(name, description);
    }

    @Override
    public Object ask()
    {
        Scanner console = new Scanner(System.in);

        super.ask();
        int value = console.nextInt();

        return value;
    }
}
