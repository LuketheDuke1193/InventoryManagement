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
            } else {
                updatePart(idPosition, newPart);
                //closes Modify Part Window
                Stage stage = (Stage) SaveButton.getScene().getWindow();
                stage.close();
            }

        } else if (Outsourced.isSelected()) {
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
            } else {
                updatePart(idPosition, newPart);
                //closes Modify Part Window
                Stage stage = (Stage) SaveButton.getScene().getWindow();
                stage.close();
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

