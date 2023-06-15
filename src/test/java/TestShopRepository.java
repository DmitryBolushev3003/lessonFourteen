import org.example.NotFoundException;
import org.example.Product;
import org.example.ShopRepository;
import org.example.thereIsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestShopRepository {

    public class ShopRepositoryTest {
        private ShopRepository shopRepository;

        @BeforeEach
        public void setup() {
            shopRepository = new ShopRepository();
            shopRepository.add(new Product(1, "Кофта", 700));
            shopRepository.add(new Product(2, "Штаны", 1000));
            shopRepository.add(new Product(3, "Кроссовки", 3000));
        }

        @Test
        public void testRemoveExistingProduct() {
            int RemoveId = 2;

            shopRepository.removeById(RemoveId);
            Product[] products = shopRepository.findAll();

            Assertions.assertEquals(2, products.length);
            Product[] expected = {new Product(1, "Кофта", 700), new Product(3, "Кроссовки", 3000)};
            Assertions.assertArrayEquals(expected, products);
        }

        @Test
        public void testRemoveNonExistingProduct() {
            int nonExistingProductId = 10;

            Assertions.assertThrows(NotFoundException.class, () -> shopRepository.removeById(nonExistingProductId));
        }

        @Test
        public void shouldAddProductTest() {
            Product product = new Product(4, "Носки", 100);
            shopRepository.add(product);

            Product[] products = shopRepository.findAll();
            Assertions.assertEquals(4, products.length);
            Product[] expected = {
                    new Product(1, "Кофта", 700),
                    new Product(2, "Штаны", 1000),
                    new Product(3, "Кроссовки", 3000),
                    new Product(4, "Носки", 100)
            };
            Assertions.assertArrayEquals(expected, products);
        }

        @Test
        public void shouldNotAddAlreadyExistsProductTest() {
            Product duplicateProduct = new Product(1, "Кофта с ID", 100);
            Assertions.assertThrows(thereIsException.class, () -> shopRepository.add(duplicateProduct));
        }
    }
}
