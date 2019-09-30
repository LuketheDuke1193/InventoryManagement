package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public ObservableList<Part> partInv = FXCollections.observableArrayList();

    @FXML
    private TextField Name;

    @FXML
    private TextField Inv;

    @FXML
    private TextField PriceCost;

    @FXML
    private TextField Max;

    @FXML
    private TextField Min;
    public ObservableList<Part> associatedPartInv = FXCollections.observableArrayList();
    Product product;
    @FXML
    private TextField ID;

    @FXML
    private Button Add1;
    @FXML
    private Button Search;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Part> partsInvTable;

    @FXML
    private Button Add2;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button Delete;
    @FXML
    private Button Search1;
    @FXML
    private TextField searchField1;
    @FXML
    private TableView<Part> associatedPartsInvTable;

    @FXML
    void addHandler1(ActionEvent event) {
        Part selPart = partsInvTable.getSelectionModel().getSelectedItem();
        associatedPartInv.add(selPart);
        generateAssociatedPartsTable();
    }

    @FXML
    void idHandler(ActionEvent event) {

    }

    @FXML
    void invHandler(ActionEvent event) {

    }

    @FXML
    void maxHandler(ActionEvent event) {

    }

    @FXML
    void minHandler(ActionEvent event) {

    }

    @FXML
    void nameHandler(ActionEvent event) {

    }

    @FXML
    void priceCostHandler(ActionEvent event) {

    }

    @FXML
    void cancelButtonHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the Add Product window?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) CancelButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void searchHandler(ActionEvent event) {

    }

    @FXML
    void searchHandler1(ActionEvent event) {

    }

    @FXML
    void deleteHandler(ActionEvent event) {
        Part delPart = partsInvTable.getSelectionModel().getSelectedItem();
        associatedPartInv.remove(delPart);
        generateAssociatedPartsTable();
    }

    @FXML
    void saveButtonHandler(ActionEvent event) {
        Product newProduct = new Product(
                Inventory.getAllProducts().size() + 1,
                Name.getText(),
                Double.parseDouble(PriceCost.getText()),
                Integer.parseInt(Inv.getText()),
                Integer.parseInt(Min.getText()),
                Integer.parseInt(Max.getText())
        );
        for (int i = 0; i < associatedPartInv.size(); i++) {
            newProduct.addAssociatedPart(associatedPartInv.get(i));
        }

        if (Integer.parseInt(Min.getText()) > Integer.parseInt(Max.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Min Field Error");
            alert.setContentText("Error: Please ensure that the value in the Min field is equal to or lower than the value in the Max field.");
            alert.show();
        } else {
            Inventory.addProduct(newProduct);
            Stage stage = (Stage) SaveButton.getScene().getWindow();
            stage.close();
        }

    }

    public void generatePartsTable() {
        //Takes inventory data and adds it to new Observable list "partInv"
        partInv.setAll(Inventory.getAllParts());
        //Sets items in partsTable tableview to the inventory list partInv
        partsInvTable.setItems(partInv);
        //Refreshes tableview.
        partsInvTable.refresh();
    }

    public void generateAssociatedPartsTable() {
        associatedPartsInvTable.setItems(associatedPartInv);
        associatedPartsInvTable.refresh();

    }

    @FXML
    void initialize() {
        assert ID != null : "fx:id=\"ID\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Inv != null : "fx:id=\"Inv\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert PriceCost != null : "fx:id=\"PriceCost\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Max != null : "fx:id=\"Max\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Min != null : "fx:id=\"Min\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Search != null : "fx:id=\"Search\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert partsInvTable != null : "fx:id=\"partsInvTable\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Add1 != null : "fx:id=\"Add1\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Search1 != null : "fx:id=\"Search1\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert searchField1 != null : "fx:id=\"searchField1\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert associatedPartsInvTable != null : "fx:id=\"associatedPartsInvTable\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Add2 != null : "fx:id=\"Add2\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert CancelButton != null : "fx:id=\"CancelButton\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert SaveButton != null : "fx:id=\"SaveButton\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Delete != null : "fx:id=\"Delete\" was not injected: check your FXML file 'AddProduct.fxml'.";
        generatePartsTable();
        ID.disableProperty().setValue(true);
    }
}
