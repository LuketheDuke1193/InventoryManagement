package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryProgram extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Inventory inventory = new Inventory(); //Creates new Inventory object.
        addTestData(inventory); //Calls the method to add sample data to the inventory object.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));//Starts loading MainScreen resources.
        View_Controller.MainScreenController controller = new View_Controller.MainScreenController(inventory); //Passes inventory to controller.
        //loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    void addTestData(Inventory inventory) {
        //Makes inhouse parts
        Part ih1 = new InhousePart(1, "Part Test", 4.55, 8, 4, 12, 100);
        Part ih2 = new InhousePart(2, "Part Ball", 3.50, 2, 1, 5, 101);
        //adds IH parts to inventory.
        Inventory.addPart(ih1);
        Inventory.addPart(ih2);
        //Makes outsourced parts
        Part os1 = new OutsourcedPart(3, "Part Wrench", 5.22, 9, 4, 13, "Cantara Industries");
        Part os2 = new OutsourcedPart(4, "Part Screw", 3.23, 4, 2, 12, "Relton Inc.");
        //Adds OS parts to inventory
        Inventory.addPart(os1);
        Inventory.addPart(os2);
        //Makes products
        Product pr1 = new Product(1, "Product F11", 15.99, 10, 4, 15);
        Product pr2 = new Product(2, "Product Diana", 17.99, 1, 1, 4);
        //Adds products to inventory
        Inventory.addProduct(pr1);
        Inventory.addProduct(pr2);
        //Adds Inhouse parts to associated parts lists for Pr1 and Pr2
        pr1.addAssociatedPart(ih1);
        pr2.addAssociatedPart(ih2);


    }
}


