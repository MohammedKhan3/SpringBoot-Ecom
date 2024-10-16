package project.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*

- list of categoryDTO call it content
use lombok to create constructor, getter and setter



 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    List<CategoryDTO> content;

    }

