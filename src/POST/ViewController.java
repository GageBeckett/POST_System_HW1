package POST;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.io.IOException;


/**
 * A Controller for the view.
 */
public class ViewController implements SaleObserver {
    private DBController cont;
    private @FXML ListView TextAreaItemList;
    private @FXML TextField TextItemId;
    private @FXML TextField TextItemQuant;
    private @FXML Label LabelTotal;
    private @FXML ListView TextAreaReceipt;
    private @FXML ListView TextAreaHistory;
    /**
     * Creates a new <code>View</code>.
     * @param cont           The controller of the application.
     */
    /**
     */
    public ViewController(){
        DBController cont = new DBController();
        this.cont = cont;
        this.cont.makeNewSale();
        this.cont.addSaleObserver(this);
    }





    @FXML
    private void itemAddedButton(ActionEvent e){
        int item = Integer.parseInt(TextItemId.getText());
        int quant = Integer.parseInt(TextItemQuant.getText());
        enterItem(item,quant);
    }
    @FXML
    private void GetTotalButton(ActionEvent e){
        double cost = cont.getTotalCost();
        System.out.println(cost);
        LabelTotal.setText("$" + String.valueOf(cost));
        LabelTotal.setVisible(true);
    }
    @FXML
    private void handleReceiptPrint(ActionEvent e){
        try{


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Receipt.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Receipt");
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
            TextAreaReceipt = (ListView) scene.lookup("#TextAreaReceipt");
            TextAreaHistory = (ListView) scene.lookup("#TextAreaHistory");
            TextAreaReceipt.getItems().addAll(TextAreaItemList.getItems());
            TextAreaHistory.getItems().addAll(TextAreaReceipt.getItems());
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {

                    TextAreaReceipt.getItems().clear();
                    TextAreaItemList.getItems().clear();
                    new ViewController();
                }
            });


        }catch(Exception el) {
            el.printStackTrace();
        }


    }

    @Override
    public void itemAdded(SalesLineItem lineItem) {
        // This event is not used but could be in the future, do nothing
    }

    @Override
    public void saleListUpdated(SalesLineItem[] allItems) {
        this.updateList(allItems);
    }

    private void updateList(SalesLineItem[] lineItems) {
        TextAreaItemList.getItems().clear();
        for (SalesLineItem lineItem : lineItems) {
            TextAreaItemList.getItems().add(lineItem.getName() + " $" + lineItem.getCost());
        }
        double cost = cont.getTotalCost();
        System.out.println(cost);
        LabelTotal.setText("$" + String.valueOf(cost));
    }

    private void displayError(Exception err) {
        System.out.println("Oops, try again! " + err.getMessage());
    }

    private void enterItem(int itemId, int quantity) {

        try {
            ProductSpecification itemSpec = cont.enterItem(itemId, quantity);
        } catch (ProductNotFoundException productException) {
            this.displayError(productException);
        }
    }
    @FXML
    private void handlePrint(){

    }
}
