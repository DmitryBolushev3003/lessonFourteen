import org.example.NotFoundException;
import org.example.Product;
import org.example.ShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestShopRepository {

    private ShopRepository shopRepository;

    @BeforeEach
    public void setup() {
        shopRepository = new ShopRepository();
        shopRepository.add(new Product(555, "Кофта", 700));
        shopRepository.add(new Product(777, "Штаны", 1000));
        shopRepository.add(new Product(111, "Кроссовки", 3000));
    }

    @Test
    public void testRemoveExistingProduct() {
        int RemoveId = 777;

        shopRepository.removeById(RemoveId);
        Product[] products = shopRepository.findAll();

        Assertions.assertEquals(2, products.length);
        Product[] expected = {new Product(555, "Кофта", 700), new Product(111, "Кроссовки", 3000)};
        Assertions.assertArrayEquals(expected, products);
    }

    @Test
    public void testRemoveNonExistingProduct() {
        int nonExistingProductId = 666;

        Assertions.assertThrows(NotFoundException.class, () -> shopRepository.removeById(nonExistingProductId));
    }

    @Test
    public void shouldAddProductTest() {
        Product product = new Product(444, "Носки", 100);
        shopRepository.add(product);

        Product[] products = shopRepository.findAll();
        Assertions.assertEquals(4, products.length);
        Product[] expected = {
                new Product(555, "Кофта", 700),
                new Product(777, "Штаны", 1000),
                new Product(111, "Кроссовки", 3000),
                new Product(444, "Носки", 100)
        };
        Assertions.assertArrayEquals(expected, products);
    }

    @Test
    public void shouldNotAddAlreadyExistsProductTest() {
        ShopRepository repository = new ShopRepository();
        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(555));
    }

}