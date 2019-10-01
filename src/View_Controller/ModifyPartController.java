package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.updatePart;

public class ModifyPartController {

    Part part;

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
    ObservableList<Part> partInv = FXCollections.observableArrayList();
    @FXML
    private TextField CompName;
    @FXML
    private Button SaveButton;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField MachineID;
    @FXML
    private Label machineIDLabel;

    @FXML
    void cancelButtonHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the Modify Part window?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) CancelButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void saveButtonHandler(ActionEvent event) throws IOException {
        if (inHouseButton.isSelected()) {
            if (Name.getText().isEmpty() || Inv.getText().isEmpty() || PriceCost.getText().isEmpty() || Min.getText().isEmpty() || Max.getText().isEmpty() || MachineID.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Value Missing");
                alert.setContentText("Error: One or more fields are empty. Ensure that all fields are filled before saving.");
                alert.show();
            } else {
                Part newPart = new InhousePart(
                        Integer.parseInt(partIDBox.getText()),
                        Name.getText(),
                        Double.parseDouble(PriceCost.getText()),
                        Integer.parseInt(Inv.getText()),
                        Integer.parseInt(Min.getText()),
                        Integer.parseInt(Max.getText()),
                        Integer.parseInt(MachineID.getText())
                );
                int idPosition = Integer.parseInt(partIDBox.getText());
                idPosition--;
                partInv = Inventory.getAllParts();

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
                } else {
                    for (int i = 0; i < Inventory.getAllProducts().size(); i++) { //Iterates through products inv
                        for (int j = 0; j < Inventory.getAllProducts().get(i).getAllAssociatedParts().size(); j++) {//Iterates through
                            if (Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getId() == newPart.getId()) {
                                Inventory.getAllProducts().get(i).getAllAssociatedParts().set(j, newPart);
                            }
                        }
                    }
                    updatePart(idPosition, newPart);
                    //closes Modify Part Window
                    Stage stage = (Stage) SaveButton.getScene().getWindow();
                    stage.close();
                }
            }
        } else if (Outsourced.isSelected()) {
            if (Name.getText().isEmpty() || Inv.getText().isEmpty() || PriceCost.getText().isEmpty() || Min.getText().isEmpty() || Max.getText().isEmpty() || CompName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Value Missing");
                alert.setContentText("Error: One or more fields are empty. Ensure that all fields are filled before saving.");
                alert.show();
            } else {
                Part newPart = new OutsourcedPart(
                        Integer.parseInt(partIDBox.getText()),
                        Name.getText(),
                        Double.parseDouble(PriceCost.getText()),
                        Integer.parseInt(Inv.getText()),
                        Integer.parseInt(Min.getText()),
                        Integer.parseInt(Max.getText()),
                        CompName.getText()
                );
                int idPosition = Integer.parseInt(partIDBox.getText());
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
                } else {
                    for (int i = 0; i < Inventory.getAllProducts().size(); i++) { //Iterates through products inv
                        for (int j = 0; j < Inventory.getAllProducts().get(i).getAllAssociatedParts().size(); j++) {//Iterates through
                            if (Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getId() == newPart.getId()) {
                                Inventory.getAllProducts().get(i).getAllAssociatedParts().set(j, newPart);
                            }
                        }
                    }
                    updatePart(idPosition, newPart);
                    //closes Modify Part Window
                    Stage stage = (Stage) SaveButton.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    void setPart(Part part) {
        this.part = part;

        if (this.part instanceof InhousePart) {
            inHouseButton.selectedProperty().setValue(true);
            partIDBox.setText(String.valueOf(part.getId()));
            Name.setText(part.getName());
            PriceCost.setText(String.valueOf(part.getPrice()));
            Inv.setText(String.valueOf(part.getStock()));
            Min.setText(String.valueOf(part.getMin()));
            Max.setText(String.valueOf(part.getMax()));
            MachineID.setText(String.valueOf(((InhousePart) part).getMachineID()));

        } else if (this.part instanceof OutsourcedPart) {
            Outsourced.selectedProperty().setValue(true);
            partIDBox.setText(String.valueOf(part.getId()));
            Name.setText(part.getName());
            PriceCost.setText(String.valueOf(part.getPrice()));
            Inv.setText(String.valueOf(part.getStock()));
            Min.setText(String.valueOf(part.getMin()));
            Max.setText(String.valueOf(part.getMax()));
            CompName.setText(String.valueOf(((OutsourcedPart) part).getCompanyName()));
        }
    }

    @FXML
    void initialize() {
        CompName.disableProperty().bind(inHouseButton.selectedProperty());
        MachineID.visibleProperty().bind(inHouseButton.selectedProperty());
        machineIDLabel.visibleProperty().bind(inHouseButton.selectedProperty());
        partIDBox.disableProperty().setValue(true);

    }
}

