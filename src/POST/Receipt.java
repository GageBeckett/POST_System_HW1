package POST;

import java.util.List;
import java.util.ArrayList;

/**
 * Holds all information needed by the receipt. 
 */
public class Receipt {
    private int payedAmount;
    private double totalCost;
    private List<SalesLineItem> receiptLines = new ArrayList<SalesLineItem>();
    
    /**
     * Creates a new receipt and saves all information it needs.
     *
     * @param sale      The <code>Sale</code> for wich the receipt shall be created.
     */
    Receipt(Sale sale) {
	totalCost = sale.getCurrentTotal();
	payedAmount = sale.getPayedAmount();
	createReceiptLines(sale);
    }

    private void createReceiptLines(Sale sale) {
	sale.resetLineItemIterator();
	while (sale.hasMoreLineItems()) {
	    receiptLines.add(sale.nextLineItem());
	}
    }

    /**
     * Returns the amount of change the customer should have.
     *
     * @return The amount of change the customer should have.
     */
    public double getChange() {
	return payedAmount - totalCost;
    }

    /**
     * Returns a well-formatted string with all receipt information.
     *
     * @return A well-formatted string with all receipt information.
     */
    public String toString() {
	return String.valueOf(this.getChange());
    }

}
