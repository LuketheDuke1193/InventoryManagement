package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {


    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public Inventory(ObservableList<Part> allParts, ObservableList<Product> allProducts) {
        Inventory.allParts = allParts;
        Inventory.allProducts = allProducts;
    }

    public Inventory() {
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partID) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partID) {
                return allParts.get(i);
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> results = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().toLowerCase().trim().contains(partName.toLowerCase().trim())) {
                results.add(allParts.get(i));
            }
        }
        return results;
    }

    public static Product lookupProduct(int productID) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == productID) {
                return allProducts.get(i);
            }
        }
        return null;
    }


    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> results = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().trim().contains(productName.toLowerCase().trim())) {
                results.add(allProducts.get(i));
            }
        }
        return results;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    public static void deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
    }

    public static void deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}
