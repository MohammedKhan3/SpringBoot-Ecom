package project.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * Entity: Makes the class as an entity in database.
 * NoArgsConstructor and AllArgsConstructor are part of lombok dependency.
 * Data: Generates boilerplate code.
 */
@Entity(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    /*
       Id: Denotes the primary key to the entity.
       GeneratedValue: Genrates id automatically and we can do that by declaring strategy.

     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //NotBlank and Size are part of validation.
    //we are ensuring some restrictions.
    @NotBlank
    @Size(min = 5, message = "Category name must contain 5 characters.")
    private String categoryName;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;
}
