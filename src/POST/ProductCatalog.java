package POST;

import java.util.Map;
import java.util.HashMap;

/**
 * This singleton class is responsible for all access to the product database.
 */ 
public class ProductCatalog {
    private static ProductCatalog instance = new ProductCatalog();
    private Map<Integer, ProductSpecification> products = 
	new HashMap<Integer, ProductSpecification>();

    /**
     * Fills the catalog with some dummy items.
     */
    private ProductCatalog() {
        products.put(1, new ProductSpecification(1, "low fat milk",
           "Its milk but less good", 10));
        products.put(2, new ProductSpecification(2, "butter",
           "Its milk but thicker and stuff, good on bread", 10));
        products.put(3, new ProductSpecification(3, "bread",
           "Not milk to be sure but ground up plants of some kind", 10));
        products.put(3, new ProductSpecification(3,"Apple",
                "Also not a milk but some kind of tree leaf", 2));
        products.put(4, new ProductSpecification(4,"Grapes",
                "Weird colored orb that grows off of dead looking strings", 1));
        products.put(5, new ProductSpecification(5,"Orange",
                "Another Weird Leaf not sure what the obsession with leafes is", 2));
        products.put(6, new ProductSpecification(6, "Celery",
                "Green stick, not very good or nourishing", 1));
        products.put(7, new ProductSpecification(7, "Chicken",
                "A very Tasty bird. I strongly recommend you try it with bread", 7));
        products.put(8, new ProductSpecification(8, "Steak",
                "Basically the juicy parts of a cow. Very Very good, do not eat it with sauce", 10));
        products.put(9, new ProductSpecification(9, "Burger",
                "Kinda like a stead that has been ground up then smashed back together", 8));
        products.put(10, new ProductSpecification(10, "Pork",
                "Dont pay any attention to the animal it comes from, Pork is amazing", 8));
    }

    
    /**
     * Returns a singleton instance of <code>ProductCatalog</code>
     * @return singleton instance of <code>ProductCatalog</code>
     */
    public static ProductCatalog getInstance () {
        return ProductCatalog.instance;
    }

    /**
     * Search for an item in the product catalog.
     *
     * @param    itemId The item to look for
     * @return          The specification for the found item.
     * @throws ProductNotFoundException If the product with supplied itemId was not found
     */
    public ProductSpecification findSpecification(int itemId) throws ProductNotFoundException {
            ProductSpecification searchResult = products.get(itemId);
	    if (searchResult == null) {
                throw new ProductNotFoundException(itemId);
            } else {
                return searchResult;
            }
    }
}
