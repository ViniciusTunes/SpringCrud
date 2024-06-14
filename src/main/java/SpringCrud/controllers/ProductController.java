package SpringCrud.controllers;

import SpringCrud.domain.product.Product;
import SpringCrud.domain.product.ProductRepository;
import SpringCrud.domain.product.RequestProduct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        System.out.println(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        Product product = repository.getReferenceById(String.valueOf(data.id()));
        product.setName(data.name());
        product.setPrice_in_cents(data.price_in_cents());
        return ResponseEntity.ok(product);
    }
}
