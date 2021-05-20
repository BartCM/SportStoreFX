package sportStore.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import sportStore.data.Client;
import sportStore.data.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static sportStore.data.FileUtils.loadStats;
import static sportStore.data.FileUtils.saveStats;

/**
 * Class that controls the operation of the program
 * @version 1.0
 * @author Bartolomé Cantó Mesas
 * @since 20/05/2021
 *
 */

public class Controller implements Initializable {
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelName;
    @FXML
    private Label lbQuantityProducts;
    @FXML
    private Label lbTotalPrice;
    @FXML
    private TableColumn<Product, String> columShoppingTradeMark;
    @FXML
    private TableColumn<Product, String> columShoppingNameProduct;
    @FXML
    private TableColumn<Product, String> columShoppingProductType;
    @FXML
    private TableColumn<Product, Float> columShoppingPrice;
    @FXML
    private TableView<Product> lViewShoppingCart;
    @FXML
    private TableView<Product> tblProducts;
    @FXML
    private TableColumn<Product, String> columTradeMark;
    @FXML
    private TableColumn<Product, String> columNameProduct;
    @FXML
    private TableColumn<Product, String> columProducTipe;
    @FXML
    private TableColumn<Product, Float> columPrice;

    final static String FILE = "articles.txt";

    private ObservableList<Product> products;

    int QuantityProducts;
    float totalPrice = 0;

    /**
     *
     * @param url class represents a Uniform Resource Locator
     * @param resourceBundle containt locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dialogName();

        //columnas Store
        columTradeMark.setCellValueFactory(new PropertyValueFactory("tradeMark"));
        columNameProduct.setCellValueFactory(new PropertyValueFactory("nameProduct"));
        columProducTipe.setCellValueFactory(new PropertyValueFactory("productType"));
        columPrice.setCellValueFactory(new PropertyValueFactory("price"));
        columPrice.setStyle("-fx-alignment: CENTER-RIGHT");

        //columnas Shopping
        columShoppingTradeMark.setCellValueFactory(new PropertyValueFactory("tradeMark"));
        columShoppingNameProduct.setCellValueFactory(new PropertyValueFactory("nameProduct"));
        columShoppingProductType.setCellValueFactory(new PropertyValueFactory("productType"));
        columShoppingPrice.setCellValueFactory(new PropertyValueFactory("price"));
        columShoppingPrice.setStyle("-fx-alignment: CENTER-RIGHT");

        //guardamos los datos de los productos
        products = FXCollections.observableArrayList(loadStats(FILE));

        //mostramos en la tabla los productos
        tblProducts.setItems(products);
    }

    /**
     * button event to add products to cart
     * If no product is chosen, an Alert dialog skips
     * @param event event to press the button
     */
    public void buttonAddToCart(ActionEvent event) {

        if(tblProducts.getSelectionModel().getSelectedItem() != null)
        {
            products = FXCollections.observableArrayList(tblProducts.getSelectionModel().getSelectedItem());

            //we add the selected products in the table
            lViewShoppingCart.getItems().addAll(products);

            //I save the number of products in the table
            QuantityProducts = lViewShoppingCart.getItems().size();

            totalPrice = 0;

            for(int i = 0; i < QuantityProducts; i++)
            {
                totalPrice += lViewShoppingCart.getItems().get(i).getPrice();
            }

            //shows the total amount
            lbTotalPrice.setText(Float.toString(totalPrice));

            //I show on the screen with a label the amount of products that are in the table
            lbQuantityProducts.setText(String.valueOf(QuantityProducts));
        }
        else
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error loading data");
            dialog.setContentText("You have not selected any article");
            dialog.showAndWait();
        }
    }

    /**
     * button to delete a product from the shopping list
     * if the delete button is pressed without having any product, an alert dialog will appear
     * @param event event to press the button
     */
    //delete an item from the shopping cart
    public void btnDeleteProductShopping(ActionEvent event) {

        if(lViewShoppingCart.getSelectionModel().getSelectedItem() != null)
        {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("INFORMATION");
            dialog.setHeaderText(lViewShoppingCart.getSelectionModel().getSelectedItem().toString());
            dialog.setContentText("Are you sure you want to delete this product?");

            Optional<ButtonType> result = dialog.showAndWait();

            //if the ok button is pressed, the selected object is deleted
            if(result.get() == ButtonType.OK)
            {
                lViewShoppingCart.getItems().remove(lViewShoppingCart.getSelectionModel().getSelectedIndex());
            }

            //quantity of products
            QuantityProducts = lViewShoppingCart.getItems().size();

            totalPrice = 0;

            //does the subtraction of the products
            for(int i = 0; i < QuantityProducts; i++)
            {
                totalPrice = lViewShoppingCart.getItems().get(i).getPrice() - totalPrice;
            }

            //shows the quantity of products
            lbQuantityProducts.setText(String.valueOf(QuantityProducts));
            //shows the total amount
            lbTotalPrice.setText(Float.toString(totalPrice));
        }
        else
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error loading data");
            dialog.setContentText("You have not selected any article");
            dialog.showAndWait();
        }
    }

    /**
     * button to confirm shopping list
     * If the purchase is accepted, the products are uploaded a file called "shoppingCart.txt"
     * @param event event to press the button
     */
    public void btnConfirmOrder(ActionEvent event) {

        //If there are more than 0 items
        if(lViewShoppingCart.getItems().size() > 0)
        {
            //shows confirmation alert with selected products
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Confirmed");
            dialog.setHeaderText(listaConfirmacion(lViewShoppingCart));
            dialog.setContentText("confirmed products");
            dialog.showAndWait();

            //the products are saved
            saveStats(lViewShoppingCart);

            //shows alert, products have been successfully saved to file
            Alert dialog1 = new Alert(Alert.AlertType.INFORMATION);
            dialog1.setTitle("Confirmed");
            dialog1.setHeaderText("the products have been saved correctly");
            dialog1.setContentText("Thank you for your purchase");
            dialog1.showAndWait();
        }
        else
        {
            //if there are no products in the cart
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error loading data");
            dialog.setContentText("No items in the cart");
            dialog.showAndWait();
        }

        //all products in the cart are deleted
        lViewShoppingCart.getItems().removeAll(lViewShoppingCart.getItems());

        //shows the quantity of products
        lbQuantityProducts.setText(String.valueOf(lViewShoppingCart.getItems().size()));

        //shows the total amount
        lbTotalPrice.setText(String.valueOf(lViewShoppingCart.getItems().size()));
    }

    /**
     *
     * @param tableViewShopping variable to enter the object
     * @return a list in the specified format
     */
    public String listaConfirmacion(TableView<Product> tableViewShopping)
    {
        String result = "";

        for(int i = 0; i < tableViewShopping.getItems().size(); i++)
        {
            result += tableViewShopping.getItems().get(i).getTradeMark() +" "
                    +tableViewShopping.getItems().get(i).getNameProduct() +" "
                    +tableViewShopping.getItems().get(i).getProductType() +" "
                    +tableViewShopping.getItems().get(i).getPrice() + "\n";
        }

      return result;
    }

    /**
     * Method that returns an Alert dialog for the client to enter the name and email,
     * is reflected in the Store sale
     * This data is entered in a Client ArrayList
     */
    public void dialogName()
    {

        ArrayList<Client> client = new ArrayList<>();
                
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        Dialog<Pair<String,String>> dialog = new Dialog<>();
        dialog.setTitle("HELLO");
        dialog.setHeaderText("INTRODUCE TU NOMBRE Y TU EMAIL");

        ButtonType loginButtonType = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        TextField userName = new TextField();
        userName.setPromptText("Username");
        TextField userEmail = new TextField();
        userEmail.setPromptText("Email");

        grid.add(new Label("Username:"),0,0);
        grid.add(userName,1,0);
        grid.add(new Label("Email:"),0,1);
        grid.add(userEmail,1,1);
        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait();

        labelName.setText(userName.getText());
        labelEmail.setText(userEmail.getText());

        client.add(new Client("01",userName.getText(),userEmail.getText()));
    }
}
