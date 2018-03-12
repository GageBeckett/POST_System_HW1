package POST;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(int itemId) {
        super("Could not find product with ID " + itemId);
    }
    
}
