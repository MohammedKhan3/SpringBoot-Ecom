package project.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdouctResponse {
    List<ProcductDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
/*

    private  Integer pageNumber;
    private  Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;

 */