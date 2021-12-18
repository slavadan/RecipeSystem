import Data.IDatabase;
import Data.LocalDataBase;
import Handlers.*;
import Manager.SystemManager;
import Recipe.Product.Product;
import Recipe.Recipe;
import Recipe.Stage;
import Storage.Storage;
import Test.Question.BaseQuestion;
import Test.Question.NumericQuestion;
import Test.Question.StringQuestion;
import Test.ExtendedTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Initializer
{
    public void init()
    {
        ArrayList<BaseQuestion> questions = new ArrayList<>();
        questions.add(new NumericQuestion(
                "Type", "1. Завтрак\n2. Обед\n3.Ужин"
        ));
        questions.add(new StringQuestion(
                "Product", "Введите имя продукта, который обязательно должен быть"
        ));

        BaseQuestion additional_question = new NumericQuestion("Count", "Ввидите количество порций");

        IDatabase database = new LocalDataBase(
                "recipe.bin", "current.bin", "products.bin"
        );
        Storage storage = new Storage(database.loadProducts(), database.loadRecipes());

        ExtendedTest test = new ExtendedTest(questions, database.loadRecipes(), additional_question);

        SystemManager manager = new SystemManager(storage, database, test);

        ExitAction exit = new ExitAction(manager);

        MenuAction open_menu = new MenuAction(
                manager, new HashMap<Integer, IActionHandler>()
        {{
            put(0, exit);
        }}
        );

        CookAction cook_action = new ExtendedCookAction(
                manager, new HashMap<Integer, IActionHandler>()
        {{
            put(0, open_menu);
        }}
        );

        ContinueRecipeAction continue_recipe = new ContinueRecipeAction(
                manager, new HashMap<Integer, IActionHandler>()
        {{
            put(1, cook_action);
            put(2, open_menu);
        }}
        );

        RegisterProductAction register = new RegisterProductAction(
                manager, new HashMap<Integer, IActionHandler>()
        {{
            put(1, open_menu);
        }}
        );

        FindNewRecipeAction find_recipe = new FindNewRecipeAction(
                manager, new HashMap<Integer, IActionHandler>()
        {{
            put(0, open_menu);
            put(1, cook_action);
        }}
        );

        WatchCatalogAction watch_catalog = new WatchCatalogAction(
                manager, new HashMap<Integer, IActionHandler>()
        {{
            put(0, open_menu);
        }}
        );

        open_menu.addAction(1, continue_recipe);
        open_menu.addAction(2, find_recipe);
        open_menu.addAction(3, watch_catalog);
        open_menu.addAction(4, register);

        open_menu.execute();
    }

    public void testInitDataBase()
    {
        IDatabase database = new LocalDataBase(
                "recipe.bin", "current.bin", "products.bin"
        );

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        LinkedList<Stage> stages = new LinkedList<Stage>();
        stages.add(new Stage("Разогреть сковородку"));
        stages.add(new Stage("Разбить яйцо"));
        stages.add(new Stage("Подождать"));

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Яйцо", 1));


        recipes.add(new Recipe(
                1, "Жаренное яйцо", "Завтрак",
                stages, products
        ));

        stages = new LinkedList<Stage>();
        stages.add(new Stage("Нарезать помидоры"));
        stages.add(new Stage("Нарезать капусту"));
        stages.add(new Stage("Положить в салатницу нарезанные продукты"));
        stages.add(new Stage("Залить масло"));

        products = new ArrayList<Product>();
        products.add(new Product("Масло", 2));
        products.add(new Product("Помидор", 2));
        products.add(new Product("Капуста", 2));

        recipes.add(new Recipe(
                2, "Салат", "Завтрак", stages, products
        ));

        ArrayList<Product> storage_products = new ArrayList<>();
        storage_products.add(new Product("Яйцо", 15));
        storage_products.add(new Product("Салат", 15));
        storage_products.add(new Product("Помидор", 15));

        database.saveProducts(storage_products);
        database.saveRecipes(recipes);
    }
}