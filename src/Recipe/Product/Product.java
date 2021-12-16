package Recipe.Product;

import java.io.Serializable;

public class Product implements Serializable
{
    public String name;
    public int count;

    public Product(String name, int count)
    {
        this.name = name;
        this.count = count;
    }
}
