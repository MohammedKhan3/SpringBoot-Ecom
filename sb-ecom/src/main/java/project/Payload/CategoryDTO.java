package project.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//request object
/*

 add - long categoryId
    - String categoryName
    - use lombok and create constructor and getter and setter

 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO {
    private Long id;
    private String categoryName;
}
