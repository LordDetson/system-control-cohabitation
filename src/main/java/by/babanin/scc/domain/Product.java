package by.babanin.scc.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Byte quantity;
    private String cost;
    private String owner;

    public Product() {
        quantity = 1;
        owner = "Common";
    }
}
