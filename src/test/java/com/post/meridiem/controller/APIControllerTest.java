import com.post.meridiem.controller.APIController;
import com.post.meridiem.model.Products;
import com.post.meridiem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(APIController.class)
public class APIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProducts() throws Exception {
        // Given
        List<Products> products = Arrays.asList(new Products());
        when(productService.getProducts(true)).thenReturn(products);

        // When and Then
        mockMvc.perform(get("/api/products?value=true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetProductsInvalidParam() throws Exception {
        // Given
        when(productService.getProducts(true)).thenThrow(new RuntimeException("Invalid parameter"));

        // When and Then
        mockMvc.perform(get("/api/products?value=invalid"))
                .andExpect(status().isBadRequest());
    }
}