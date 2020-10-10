package by.babanin.scc.controller;

import by.babanin.scc.domain.Product;
import by.babanin.scc.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/add")
    @ResponseBody
    public String add(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") Byte quantity,
            @RequestParam String cost,
            @RequestParam(defaultValue = "Common") String owner
    ) {
        Product product = Product.builder()
                .name(name)
                .quantity(quantity)
                .cost(cost)
                .owner(owner)
                .build();
        productRepository.save(product);
        return product + " saved";
    }

    @GetMapping("/products")
    public String getAll(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
}
