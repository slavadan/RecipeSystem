package Test;


import Recipe.Recipe;
import Recipe.ExtendedRecipe;
import Test.Question.BaseQuestion;
import java.util.ArrayList;


public class ExtendedTest extends Test
{
    private BaseQuestion additional_question;

    public ExtendedTest(ArrayList<BaseQuestion> questions, ArrayList<Recipe> recipes, BaseQuestion additional_question)
    {
        super(questions, recipes);
        this.additional_question = additional_question;
    }

    @Override
    public ArrayList<Recipe> launchTest()
    {
        ArrayList<Recipe> super_result = super.launchTest();

        Object additional_answer = additional_question.ask();

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        for(Recipe recipe: super_result)
            recipes.add(new ExtendedRecipe(recipe, (int)additional_answer));

        return recipes;
    }

}
