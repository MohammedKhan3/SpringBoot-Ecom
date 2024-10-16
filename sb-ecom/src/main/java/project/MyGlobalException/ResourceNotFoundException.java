package project.MyGlobalException;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;

    public ResourceNotFoundException() {
    }

    String field;
    String filedName;
    Long fieldName;
    public ResourceNotFoundException(Long fieldName, String resourceName, String field) {
        super(String.format("%s not found  with %s: %d",resourceName,field));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.field = field;
    }

    public ResourceNotFoundException(String resourceName, String field, String filedName) {
       super(String.format("%s not found  with %s: %s",resourceName,field,filedName));
        this.resourceName = resourceName;
        this.field = field;
        this.filedName = filedName;
    }



}
