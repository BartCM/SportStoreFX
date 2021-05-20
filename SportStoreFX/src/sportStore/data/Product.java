package sportStore.data;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Product class to store the products of the store
 * @version 1.0
 * @author Bartolome
 * @since 20/05/2021
 */
public class Product
{
    private String tradeMark;
    private String nameProduct;
    private String productType;
    private float price;
    User users;

    /**
     * create the products
     * @param tradeMark variable to enter the product brand
     * @param nameProduct variable to enter the product name
     * @param productType variable to enter the type of product
     * @param price variable to enter the price
     */
    public Product(String tradeMark, String nameProduct, String productType, float price)
    {
        this.tradeMark = tradeMark;
        this.nameProduct = nameProduct;
        this.productType = productType;
        this.price = price;
    }

    /**
     * @return the trademark
     */
    public String getTradeMark() {
        return tradeMark;
    }

    /**
     * @param tradeMark tradeMark
     */
    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }

    /**
     *
     * @return nameProduct
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     *
     * @param nameProduct nameProduct
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    /**
     *
     * @return ProductType
     */
    public String getProductType() {
        return productType;
    }

    /**
     *
     * @param productType productType
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     *
     * @return Price
     */
    public float getPrice() {
        return price;
    }

    /**
     *
     * @param price price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     *
     * @return Users
     */
    public User getUsers() {
        return users;
    }

    /**
     *
     * @param users users
     */
    public void setUsers(User users) {
        this.users = users;
    }

    /**
     * Method that returns a String in the desired format
     */
    public void String()
    {
        System.out.println("TradeMark: " +tradeMark +"\n"
                +"Name Product: " +nameProduct +"\n"
                +"Product Type: " +productType +"\n"
                +"Price: " +price);
    }

    /**
     *
     * @return override toString method
     */
    @Override
    public String toString() {
        return tradeMark +";" +nameProduct +";" +productType +";" +price;
    }

    /**
     *
     * @param o variable to enter the object
     * @return returns the object compared to another object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product products = (Product) o;
        return Float.compare(products.price, price) == 0;
    }

    /**
     *
     * @return returns the hashCode of the price
     */
    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
