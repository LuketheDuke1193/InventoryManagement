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

public class ModifyProductController {
    public ObservableList<Part> searchPartsInv = FXCollections.observableArrayList();
    public ObservableList<Part> partInv = FXCollections.observableArrayList();
    public ObservableList<Part> associatedPartInv = FXCollections.observableArrayList();
    private Product product;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
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
    private Button Search;
    @FXML
    private TextField ID;
    @FXML
    private TextField SearchField;
    @FXML
    private Button CancelButton;
    @FXML
    private Button SaveButton;
    @FXML
    private Button Delete;
    @FXML
    private TableView<Part> partsInvTable;
    @FXML
    private Button Add;
    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    void addHandler1(ActionEvent event) {
        Part selPart = partsInvTable.getSelectionModel().getSelectedItem();

        if (associatedPartsTable.getItems().contains(selPart)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Associated Part Error");
            alert.setContentText("Parts cannot be associated twice for the same product.");
            alert.show();
        } else {
            this.product.addAssociatedPart(selPart);
            generateAssociatedPartsTable();
        }
    }

    @FXML
    void cancelButtonHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the Modify Product window?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) CancelButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void deleteHandler(ActionEvent event) {
        Part delPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        this.product.deleteAssociatedPart(delPart);
        associatedPartInv.remove(delPart);
        generateAssociatedPartsTable();
    }

    @FXML
    void saveButtonHandler(ActionEvent event) {
        this.product.setId(Integer.parseInt(ID.getText()));
        this.product.setName(Name.getText());
        this.product.setPrice(Double.parseDouble(PriceCost.getText()));
        this.product.setStock(Integer.parseInt(Inv.getText()));
        this.product.setMin(Integer.parseInt(Min.getText()));
        this.product.setMax(Integer.parseInt(Max.getText()));

        int idPosition = Integer.parseInt(ID.getText());
        idPosition--;

        if (Integer.parseInt(Min.getText()) > Integer.parseInt(Max.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Min Field Error");
            alert.setContentText("Error: Please ensure that the value in the Min field is equal to or lower than the value in the Max field.");
            alert.show();
        } else if (Integer.parseInt(Inv.getText()) > Integer.parseInt(Max.getText()) || Integer.parseInt(Inv.getText()) < Integer.parseInt(Min.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inv Level Error");
            alert.setContentText("Error: Please ensure that the value in the Inv field is equal to or between the values in the Max and Min fields.");
            alert.show();
        } else if (Double.parseDouble(PriceCost.getText()) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Price Error");
            alert.setContentText("Error: Prices of parts and products cannot be negative.");
            alert.show();
        } else if (associatedPartInv.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Associated Parts Error");
            alert.setContentText("Error: Products must have at least one associated part.");
            alert.show();
        } else {
            Inventory.updateProduct(idPosition, this.product);
            //closes Modify Product Window
            Stage stage = (Stage) SaveButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void searchHandler(ActionEvent event) {
        if (SearchField.getText().trim().toLowerCase().isEmpty()) {
            generatePartsTable();
        } else {
            try {
                searchPartsInv.clear();
                searchPartsInv.add(Inventory.lookupPart(Integer.parseInt(SearchField.getText().trim().toLowerCase())));
                partsInvTable.getItems().clear();
                partsInvTable.setItems(searchPartsInv);
                partsInvTable.refresh();
            } catch (NumberFormatException badInput) {
                searchPartsInv.clear();
                searchPartsInv.addAll(Inventory.lookupPart(SearchField.getText().trim().toLowerCase()));
                partsInvTable.getItems().clear();
                partsInvTable.setItems(searchPartsInv);
                partsInvTable.refresh();
            }
        }
    }

    void setProduct(Product product) {
        this.product = product;


        ID.setText(String.valueOf(this.product.getId()));
        Name.setText(this.product.getName());
        Inv.setText(String.valueOf(this.product.getStock()));
        PriceCost.setText(String.valueOf(this.product.getPrice()));
        Min.setText(String.valueOf(this.product.getMin()));
        Max.setText(String.valueOf(this.product.getMax()));

        generateAssociatedPartsTable();
        generatePartsTable();

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
        associatedPartInv.setAll(this.product.getAllAssociatedParts());
        associatedPartsTable.setItems(associatedPartInv);
        associatedPartsTable.refresh();

    }

    @FXML
    void initialize() {
        ID.disableProperty().setValue(true);

    }
}
