package by.babanin.scc.controller;

import by.babanin.scc.controller.form.ProductForm;
import by.babanin.scc.domain.Product;
import by.babanin.scc.domain.User;
import by.babanin.scc.repository.ProductRepository;
import org.javamoney.moneta.Money;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(types = User.class)
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/products/add")
    public String add(
            @ModelAttribute ProductForm productForm,
            @AuthenticationPrincipal User user
    ) {
        Product product = Product.builder()
                .name(productForm.getName())
                .quantity(productForm.getQuantity())
                .cost(Money.of(productForm.getCost(), user.getCurrency()))
                .owner(productForm.getOwner())
                .build();
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getAll(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
}
