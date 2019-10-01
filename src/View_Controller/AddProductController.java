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
    public ObservableList<Part> searchPartsInv = FXCollections.observableArrayList();
    public ObservableList<Part> associatedPartInv = FXCollections.observableArrayList();
    public ObservableList<Part> partInv = FXCollections.observableArrayList();

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
    private TextField ID;
    @FXML
    private Button Add1;
    @FXML
    private Button Search;
    @FXML
    private TextField SearchField;
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
    void cancelButtonHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the Add Product window?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) CancelButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void deleteHandler(ActionEvent event) {
        Part delPart = partsInvTable.getSelectionModel().getSelectedItem();
        associatedPartInv.remove(delPart);
        generateAssociatedPartsTable();
    }

    @FXML
    void saveButtonHandler(ActionEvent event) {
        if (Name.getText().isEmpty() || Inv.getText().isEmpty() || PriceCost.getText().isEmpty() || Min.getText().isEmpty() || Max.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Value Missing");
            alert.setContentText("Error: One or more fields are empty. Ensure that all fields are filled before saving.");
            alert.show();
        } else {
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
                Inventory.addProduct(newProduct);
                Stage stage = (Stage) SaveButton.getScene().getWindow();
                stage.close();
            }
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
        generatePartsTable();
        ID.disableProperty().setValue(true);
    }
}
