package project.categoryModel;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
