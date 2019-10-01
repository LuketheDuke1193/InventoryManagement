package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    public ObservableList<Product> searchProductsInv = FXCollections.observableArrayList();
    Inventory inventory;
    public ObservableList<Part> partInv = FXCollections.observableArrayList();
    public ObservableList<Product> prodInv = FXCollections.observableArrayList();
    public ObservableList<Part> searchPartsInv = FXCollections.observableArrayList();

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TextField partsSearchField;
    @FXML
    private TextField productsSearchField;

    public MainScreenController(Inventory inventory) {
        this.inventory = inventory;
    }

    public MainScreenController() { //Line 23 of InventoryProgram class throws error if default constructor isn't present.

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatePartsTable();
        generateProductsTable();
    }

    public void generatePartsTable() {
        //Takes inventory data and adds it to new Observable list "partInv"
        partInv.setAll(Inventory.getAllParts());
        //Sets items in partsTable tableview to the inventory list partInv
        partsTable.setItems(partInv);
        //Refreshes tableview.
        partsTable.refresh();
    }

    public void generateProductsTable() { //Same functionality as generatePartsTable() but for products.
        prodInv.setAll(Inventory.getAllProducts());
        productTable.setItems(prodInv);
        productTable.refresh();
    }

    @FXML
    void addHandler(MouseEvent event) throws IOException { //Opens Add Part screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/AddPart.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait(); //Tells main screen runtime to pause and wait for the new window to close before proceeding.
        generatePartsTable();
    }

    @FXML
    void addHandler1(MouseEvent event) throws IOException { //Opens Add Product Screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/AddProduct.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait(); //Tells main screen runtime to pause and wait for the new window to close before proceeding.
        generateProductsTable();
    }

    @FXML
    void deleteHandler(MouseEvent event) { //Deletes selected part
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + partsTable.getSelectionModel().getSelectedItem().getName() + "?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Part part = partsTable.getSelectionModel().getSelectedItem();
            Inventory.deletePart(part);
            generatePartsTable();
        }
    }

    @FXML
    void deleteHandler1(MouseEvent event) { //Deletes selected product
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + productTable.getSelectionModel().getSelectedItem().getName() + "?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Product product = productTable.getSelectionModel().getSelectedItem();
            Inventory.deleteProduct(product);
            generateProductsTable();
        }
    }

    @FXML
    void exitHandler(MouseEvent event) { //Exit Button Handler
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

    @FXML
    void modifyHandler(MouseEvent event) throws IOException {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        int partIndex = partsTable.getSelectionModel().getSelectedIndex();
        if (partIndex != -1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            ModifyPartController controller = loader.getController();
            controller.setPart(part);
            stage.showAndWait(); //Tells main screen runtime to pause and wait for the new window to close before proceeding.
            generatePartsTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No parts selected!");
            alert.setContentText("Please select a part to modify.");
            alert.showAndWait();
        }
    }

    @FXML
    void modifyHandler1(MouseEvent event) throws IOException {
        Product product = productTable.getSelectionModel().getSelectedItem();
        int productIndex = productTable.getSelectionModel().getSelectedIndex();
        if (productIndex != -1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            ModifyProductController controller = loader.getController();
            controller.setProduct(product);
            stage.showAndWait(); //Tells main screen runtime to pause and wait for the new window to close before proceeding.
            generatePartsTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No parts selected!");
            alert.setContentText("Please select a part to modify.");
            alert.showAndWait();
        }
    }

    @FXML
    void searchHandler(MouseEvent event) { //Search handler for searching parts.
        if (partsSearchField.getText().trim().toLowerCase().isEmpty()) {
            generatePartsTable();
        } else {
            try {
                searchPartsInv.clear();
                searchPartsInv.add(Inventory.lookupPart(Integer.parseInt(partsSearchField.getText().trim().toLowerCase())));
                partsTable.getItems().clear();
                partsTable.setItems(searchPartsInv);
                partsTable.refresh();
            } catch (NumberFormatException badInput) {
                searchPartsInv.clear();
                searchPartsInv.addAll(Inventory.lookupPart(partsSearchField.getText().trim().toLowerCase()));
                partsTable.getItems().clear();
                partsTable.setItems(searchPartsInv);
                partsTable.refresh();
            }
        }
    }

    @FXML
    void searchHandler1(MouseEvent event) { //Search handler for searching products
        if (productsSearchField.getText().trim().toLowerCase().isEmpty()) {
            generateProductsTable();
        } else {
            try {
                searchProductsInv.clear();
                searchProductsInv.add(Inventory.lookupProduct(Integer.parseInt(productsSearchField.getText().trim().toLowerCase())));
                productTable.getItems().clear();
                productTable.setItems(searchProductsInv);
                productTable.refresh();
            } catch (NumberFormatException badInput) {
                searchProductsInv.clear();
                searchProductsInv.addAll(Inventory.lookupProduct(productsSearchField.getText().trim().toLowerCase()));
                productTable.getItems().clear();
                productTable.setItems(searchProductsInv);
                productTable.refresh();
            }
        }
    }
}
