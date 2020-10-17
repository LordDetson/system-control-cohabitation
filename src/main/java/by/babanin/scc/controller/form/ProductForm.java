package by.babanin.scc.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {
    private String name;
    private Byte quantity;
    private Double cost;
    private String owner;

    public ProductForm() {
        quantity = 1;
        owner = "Common";
    }
}
