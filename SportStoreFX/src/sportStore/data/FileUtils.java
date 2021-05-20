package sportStore.data;

import javafx.scene.control.TableView;

import java.io.*;
import java.util.ArrayList;

/**
 * Class to load the list of products from the store and
 * save the purchase in a txt file
 * @author Bartolomé Cantó Mesas
 * @since 20/05/2021
 */
public class FileUtils
{
    /**
     *
     * @param file variable to collect the name of the text file
     * @return returns the list of products
     */
    public static ArrayList<Product> loadStats(String file)
    {
        ArrayList<Product> products = new ArrayList<>();

        //First we see if the file exists//Primero vemos si el fichero existe
        if(!(new File(file)).exists())
        {
            System.out.println("It has not been found " +file);
        }
        else
        {
            //in case it exists, we try to read//en caso de que exista, intentamos leer
            try
            {
                //read the file
                BufferedReader inputFile = new BufferedReader(new FileReader(file));

                //We create a String variable to read the file line by line//creamos una variable String para leer linea linea el archivo
                String line = inputFile.readLine();

                if(line == null)
                {
                    System.out.println("The file: " +file +"is empty");
                }
                else
                {
                    //if the String variable is not empty//si la variable String no está vacía
                    while (line != null)
                    {
                        String[] separatelines = line.split(";");

                        products.add(new Product(separatelines[0],separatelines[1],separatelines[2]
                                ,Float.parseFloat(separatelines[3])));

                        line = inputFile.readLine();
                    }
                }
                //close file
                inputFile.close();
            }
            catch (IOException e)
            {
                System.out.println("there have been problems with the file" + e.getMessage());
            }
        }
        return products;
    }

    /**
     *
     * @param pr collects an object of type TableView
     */
    //method that writes to the text file
    public static void saveStats(TableView<Product> pr)
    {
        String nameFile = "shoppingCart.txt";
        PrintWriter printWriter = null;

        try
        {
            printWriter = new PrintWriter(nameFile);

            for(int i = 0; i < pr.getItems().size(); i++)
            {
                printWriter.write(pr.getItems().get(i).getTradeMark() +" "
                                    + pr.getItems().get(i).getNameProduct() +" "
                                    + pr.getItems().get(i).getProductType() +" "
                                    + pr.getItems().get(i).getPrice() +"\n");
            }

            System.out.println("Successfully generated file");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                //close the file
                printWriter.close();
            }
        }
    }
}
