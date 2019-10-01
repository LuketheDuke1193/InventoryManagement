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
            } else {
                addPart(newPart);

                //closes Add Part Window
                Stage stage = (Stage) SaveButton.getScene().getWindow();
                stage.close();
            }

        } else if (Outsourced.isSelected()) {
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
