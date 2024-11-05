package project.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
create model - Product
mark it as an entity
then we need
- Long productid
-string productName
-String description
-Integer quantity
-double price
-double special price
-relationsips Categoty many to one
and join column named with category_id
then auto generate id
use lombok for constructors

 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotBlank
    @Size(min=3,message = "product name must contain atleast 3 characters")
    private String productName;
    @NotBlank
    @Size(min=3,message = "product name must contain atleast 6 characters")
    private String description;
    private Integer quantity;
    private double price;
    private double discount;
    private String image;
    private double special_price;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
