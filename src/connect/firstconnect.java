package connect;

import model.modelLaptop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class firstconnect {
    public static void main(String[] args) throws IOException {

        //reques
        connectURl koneksisaya = new connectURl();
        URL myAddress = koneksisaya.buildURl
                ("https://dummyjson.com/product/search?q=Laptop");
        //response
        ;
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);
        System.out.println(response);
        //decoding JSON
        assert response !=null;
        JSONArray responseJSON = new JSONArray(response);
        ArrayList<modelLaptop> lapi =
                new ArrayList<>();
        for(int i=0;i<responseJSON.length(); i++) {
            modelLaptop top=new modelLaptop();
            JSONObject jsonObject = responseJSON.getJSONObject(i);
            top.setId(jsonObject.getString("Id"));
            top.setTitle(jsonObject.getString("title"));
            top.setDeskripsi(jsonObject.getString("description"));
            top.setHarga(jsonObject.getInt("price"));
            top.setDiskon(jsonObject.getString("discountPercentage"));
            top.setRating(jsonObject.getInt("rating"));
            top.setStok(jsonObject.getInt("stock"));
            top.setBrand(jsonObject.getString("brand"));
            top.setKategori(jsonObject.getString("category"));
            top.setThumbnail(jsonObject.getString("thumbnail"));
        }
        System.out.println("Data JSON:");
        for (modelLaptop laptop :lapi) {
            System.out.println("ID: " + laptop.getId());
            System.out.println("Title : )" + laptop.getTitle());
            System.out.println("Description: " + laptop.getDeskripsi());
            System.out.println("Price : " + laptop.getHarga());
            System.out.println("Discount : " + laptop.getDiskon());
            System.out.println("Rating : " + laptop.getRating());
            System.out.println("Stock : " + laptop.getStok());
            System.out.println("Brand : " + laptop.getBrand());
            System.out.println("Thumbnail : " + laptop.getThumbnail());
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Masukan rating ");
        double searchRating = input.nextDouble();
        modelLaptop searchData = searchData (searchRating, searchRating);
        if (searchData != null) {
            System.out.println("Data dengan rating " + searchRating + ":");
            System.out.println("ID: " + searchData.getId());
            System.out.println("Title: " + searchData.getTitle());
            System.out.println("Description: " + searchData.getDeskripsi());
            System.out.println("Price : " + searchData.getHarga());
            System.out.println("Discount: " + searchData.getDiskon());
            System.out.println("Rating: " + searchData.getRating());
            System.out.println("Stock: " + searchData.getStok());
            System.out.println("Brand: " + searchData.getBrand());
            System.out.println("Thumbnail: " + searchData.getThumbnail());
        } else {
            System.out.println("Data dengan rating " + searchRating + " tidak ditemukan.");
        }
    }
    private static void displayData(modelLaptop[] laptops) {
        for (modelLaptop laptop : laptops) {
            System.out.println(laptop);
        }
        System.out.println();
    }

    private static void selectionSort(modelLaptop[] laptops) {
        int n = laptops.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (laptops[j].getRating() < laptops[minIndex].getRating()) {
                    minIndex = j;
                }
            }
            modelLaptop temp = laptops[minIndex];
            laptops[minIndex] = laptops[i];
            laptops[i] = temp;
        }
    }

    private static double getUserInput() {
        System.out.print("Masukkan rating yang ingin Anda cari: ");
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextDouble();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }
}