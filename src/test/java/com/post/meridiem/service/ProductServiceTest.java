import com.post.meridiem.model.Products;
import com.post.meridiem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void testGetProducts_WhenValueIsTrue_ReturnsListOfProducts() {
        // Given
        boolean value = true;
        int expectedSize = 2;

        // When
        List<Products> products = productService.getProducts(value);

        // Then
        assertNotNull(products);
        assertEquals(expectedSize, products.size());
    }

    @Test
    void testGetProducts_WhenValueIsFalse_ReturnsEmptyList() {
        // Given
        boolean value = false;
        int expectedSize = 0;

        // When
        List<Products> products = productService.getProducts(value);

        // Then
        assertNotNull(products);
        assertEquals(expectedSize, products.size());
    }

    @Test
    void testGetProducts_WhenValueIsTrue_ProductsHaveCorrectProperties() {
        // Given
        boolean value = true;

        // When
        List<Products> products = productService.getProducts(value);

        // Then
        assertNotNull(products);
        assertEquals(2, products.size());
        Products firstProduct = products.get(0);
        assertEquals("100", firstProduct.getProductId());
        assertEquals("Sample Product", firstProduct.getProductName());
        assertEquals(100D, firstProduct.getProductPrice());

        Products secondProduct = products.get(1);
        assertEquals("101", secondProduct.getProductId());
        assertEquals("Trial Product", secondProduct.getProductName());
        assertEquals(100D, secondProduct.getProductPrice());
    }
}