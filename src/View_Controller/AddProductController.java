package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton inHouseButton;

    @FXML
    private RadioButton Outsourced;

    @FXML
    private TextField partIDBox;

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

    @FXML
    private TextField CompName;

    @FXML
    private Button Search;

    @FXML
    private Button Add1;

    @FXML
    private Button Add2;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button Delete;

    @FXML
    void addHandler1(ActionEvent event) {

    }

    @FXML
    void addHandler2(ActionEvent event) {

    }

    @FXML
    void cancelButtonHandler(ActionEvent event) {

    }

    @FXML
    void companyNameHandler(ActionEvent event) {

    }

    @FXML
    void deleteHandler(ActionEvent event) {

    }

    @FXML
    void idHandler(ActionEvent event) {

    }

    @FXML
    void inHouseTrue(ActionEvent event) {

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
    void outsourcedTrue(ActionEvent event) {

    }

    @FXML
    void priceCostHandler(ActionEvent event) {

    }

    @FXML
    void saveButtonHandler(ActionEvent event) {

    }

    @FXML
    void searchHandler(ActionEvent event) {

    }

    @FXML
    void searchHandler1(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert inHouseButton != null : "fx:id=\"inHouseButton\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Outsourced != null : "fx:id=\"Outsourced\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert partIDBox != null : "fx:id=\"partIDBox\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Inv != null : "fx:id=\"Inv\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert PriceCost != null : "fx:id=\"PriceCost\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Max != null : "fx:id=\"Max\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Min != null : "fx:id=\"Min\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert CompName != null : "fx:id=\"CompName\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Search != null : "fx:id=\"Search\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Add1 != null : "fx:id=\"Add1\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Add2 != null : "fx:id=\"Add2\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert CancelButton != null : "fx:id=\"CancelButton\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert SaveButton != null : "fx:id=\"SaveButton\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert Delete != null : "fx:id=\"Delete\" was not injected: check your FXML file 'AddProduct.fxml'.";

    }
}