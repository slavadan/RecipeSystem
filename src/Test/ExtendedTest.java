package Test;

import Recipe.Product.Product;
import Recipe.Recipe;
import Test.Question.BaseQuestion;

import java.util.ArrayList;

public class ExtendedTest extends Test
{
    private BaseQuestion additional_question;

    public ExtendedTest(ArrayList<BaseQuestion> questions, ArrayList<Recipe> recipes, BaseQuestion additional_question)
    {
        super(new ArrayList<BaseQuestion>(questions), recipes);
        this.additional_question = additional_question;
    }

    @Override
    public ArrayList<Recipe> launchTest()
    {
        ArrayList<Recipe> super_result = super.launchTest();

        Object additional_answer = additional_question.ask();

        for(int i = 0; i < super_result.size(); ++i)
        {
            for(Product product: super_result.get(i).products)
                if (product.name.equals(additional_answer))
                    super_result.remove(i);
        }


        return super_result;
    }
}
