package POST;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a single sale to one customer.
 */
public class Sale {
    private List<SalesLineItem> lineItems;
    private List<SaleObserver> observers;
    private int currentTotal;
    private int payedAmount;
    private int iterator;

    /**
     * Instantiates a new <code>Sale</code>.
     */
    public Sale() {
	lineItems = new ArrayList<SalesLineItem>();
        observers = new ArrayList<SaleObserver>();
    }
    
    /**
     * Adds an observer to this observable object. Observers
     * will be notified whenever an item is added to the sale
     * 
     * @param observer the <code>SaleObserver</code> to add
     */
    public void addObserver(SaleObserver observer) {
        this.observers.add(observer);
    }
    
    /**
     * Removes an observer to this observable object
     * @param observer the <code>SaleObserver</code> to remove
     */
    public void removeObserver(SaleObserver observer) {
        this.observers.remove(observer);
    }
    
    private void notifyObserversAddedItem(SalesLineItem addedLineItem) {
        // Create a copy of the lineItems list and pass to observers
        SalesLineItem[] saleItemsList = lineItems.toArray(
            new SalesLineItem[this.lineItems.size()]
        );
        
        for (SaleObserver observer : this.observers) {
            observer.itemAdded(addedLineItem);
            observer.saleListUpdated(saleItemsList);
        }
    }
    
    /**
     * Adds new items to the current <code>Sale</code>. 
     *
     * @param spec            The specification of the items that is added.
     * @param quantity        The number of items.
     */
    public void addItem(ProductSpecification spec, int quantity) {
	SalesLineItem lineItem = new SalesLineItem(spec, quantity);
	lineItems.add(lineItem);
	addToTotal(lineItem);
        this.notifyObserversAddedItem(lineItem);
    }

    private void addToTotal(SalesLineItem lineItem) {
	currentTotal = 
	    currentTotal + lineItem.getCost();
    }

    /**
     * Returns the total cost of all products registered so for.
     *
     * @return The total cost of all products registered so for
     * with sales tax.
     */
    public double getCurrentTotal() {
        double total = currentTotal + currentTotal * .06;
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * Calculates change and returns all information needed for the receipt.
     *
     * @return All information needed for the receipt.
     */
    public Receipt createReceipt(int payedAmount) {
	this.payedAmount = payedAmount;
	return new Receipt(this);
    }

    void resetLineItemIterator() {
	iterator = 0;
    }

    SalesLineItem nextLineItem() {
	return lineItems.get(iterator);
    }

    boolean hasMoreLineItems() {
	return iterator < lineItems.size();
    }

    int getPayedAmount() {
	return payedAmount;
    }
}
