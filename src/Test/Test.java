package Test;


import Recipe.Product.Product;
import Recipe.Recipe;
import Test.Question.BaseQuestion;
import java.util.ArrayList;


public class Test
{
    protected ArrayList<BaseQuestion> questions;
    protected ArrayList<Recipe> recipes;

    public Test(ArrayList<BaseQuestion> questions, ArrayList<Recipe> recipes)
    {
        this.questions = questions;
        this.recipes = recipes;
    }

    public ArrayList<Recipe> launchTest()
    {
        ArrayList<Recipe> result = new ArrayList<Recipe>();

        for(BaseQuestion question: questions)
        {
            Object res = question.ask();

            for (Recipe recipe: recipes)
            {
                if (recipe.type.equals(res))
                    result.add(recipe);

                for (Product product: recipe.products)
                    if (product.name.equals(res))
                        result.add(recipe);
            }
        }

        return result;
    }

    public void addQuestion(BaseQuestion question) { questions.add(question); }

}
