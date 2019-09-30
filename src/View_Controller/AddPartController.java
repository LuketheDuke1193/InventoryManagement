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
    void companyNameHandler(ActionEvent event) {

    }

    @FXML
    void idHandler(ActionEvent event) {

    }

    @FXML
    void machineID1Handler(ActionEvent event) {

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
        assert inHouseButton != null : "fx:id=\"inHouseButton\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert Outsourced != null : "fx:id=\"Outsourced\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert partIDBox != null : "fx:id=\"partIDBox\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert Inv != null : "fx:id=\"Inv\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert PriceCost != null : "fx:id=\"PriceCost\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert Max != null : "fx:id=\"Max\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert Min != null : "fx:id=\"Min\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert CompName != null : "fx:id=\"CompName\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert SaveButton != null : "fx:id=\"SaveButton\" was not injected: check your FXML file 'AddPart.fxml'.";
        assert CancelButton != null : "fx:id=\"CancelButton\" was not injected: check your FXML file 'AddPart.fxml'.";
        inHouseButton.selectedProperty().setValue(true);
        CompName.disableProperty().bind(inHouseButton.selectedProperty());
        MachineID1.visibleProperty().bind(inHouseButton.selectedProperty());
        MachineIDLabel1.visibleProperty().bind(inHouseButton.selectedProperty());
        partIDBox.disableProperty().setValue(true);
    }
}
