package fullstack.springbootbackend.controller;

import fullstack.springbootbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // get all products
    @GetMapping("/products")
    public HashMap<String, Object> getAllProducts() {
        var products = new HashMap<String, Object>();
        List<String> categories = new ArrayList<String>(
                Arrays.asList("Hats", "Sneakers", "Womens", "Mens", "Watches", "Jewelry", "Jackets", "Kids", "Pregnancy"));
        for (String category : categories) {
            products.put(category.toLowerCase(Locale.ROOT), getCategory(category.toLowerCase(Locale.ROOT), category));
        }
        return products;
    }

    HashMap<String,Object> getCategory(String categoryName, String categoryTitle){
        var category = new HashMap<String, Object>();
        category.put("title", categoryTitle);
        category.put("routeName", categoryName);
        category.put("id", categoryName);
        category.put("items", productRepository.findAllByTitle(categoryName));
        return category;
    }
}