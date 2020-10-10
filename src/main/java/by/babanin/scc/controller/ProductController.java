package by.babanin.scc.controller;

import by.babanin.scc.domain.Product;
import by.babanin.scc.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/products/add")
    public String add(
            @ModelAttribute Product product,
            Model model
    ) {
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/products")
    public String getAll(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
}
