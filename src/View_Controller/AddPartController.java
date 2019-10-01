package View_Controller;

import Model.InhousePart;
import Model.OutsourcedPart;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.addPart;
import static Model.Inventory.getAllParts;

public class AddPartController {

    public Label MachineIDLabel1;
    public TextField MachineID1;

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
    private Button SaveButton;

    @FXML
    private Button CancelButton;

    @FXML
    void cancelButtonHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the Add Part window?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) CancelButton.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void saveButtonHandler(ActionEvent event) {
        if (inHouseButton.isSelected()) {
            if (Name.getText().isEmpty() || Inv.getText().isEmpty() || PriceCost.getText().isEmpty() || Min.getText().isEmpty() || Max.getText().isEmpty() || MachineID1.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Value Missing");
                alert.setContentText("Error: One or more fields are empty. Ensure that all fields are filled before saving.");
                alert.show();
            }
            Part newPart = new InhousePart(
                    getAllParts().size() + 1,
                    Name.getText(),
                    Double.parseDouble(PriceCost.getText()),
                    Integer.parseInt(Inv.getText()),
                    Integer.parseInt(Min.getText()),
                    Integer.parseInt(Max.getText()),
                    Integer.parseInt(MachineID1.getText())
            );
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
                addPart(newPart);

                //closes Add Part Window
                Stage stage = (Stage) SaveButton.getScene().getWindow();
                stage.close();
            }
        } else if (Outsourced.isSelected()) {
            if (Name.getText().isEmpty() || Inv.getText().isEmpty() || PriceCost.getText().isEmpty() || Min.getText().isEmpty() || Max.getText().isEmpty() || CompName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Value Missing");
                alert.setContentText("Error: One or more fields are empty. Ensure that all fields are filled before saving.");
                alert.show();
            }
            Part newPart = new OutsourcedPart(
                    getAllParts().size() + 1,
                    Name.getText(),
                    Double.parseDouble(PriceCost.getText()),
                    Integer.parseInt(Inv.getText()),
                    Integer.parseInt(Min.getText()),
                    Integer.parseInt(Max.getText()),
                    CompName.getText()
            );
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
                addPart(newPart);

                //closes Add Part Window
                Stage stage = (Stage) SaveButton.getScene().getWindow();
                stage.close();
            }
        }
    }


    @FXML
    void initialize() {
        inHouseButton.selectedProperty().setValue(true);
        CompName.disableProperty().bind(inHouseButton.selectedProperty());
        MachineID1.visibleProperty().bind(inHouseButton.selectedProperty());
        MachineIDLabel1.visibleProperty().bind(inHouseButton.selectedProperty());
        partIDBox.disableProperty().setValue(true);
    }
}
