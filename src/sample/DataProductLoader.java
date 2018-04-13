package sample;

import javax.xml.crypto.Data;

public class DataProductLoader {

    private String color;
    private String size;
    private String productName;
    private static DataProductLoader dataProductLoader;



    private DataProductLoader(){

    }

//    private DataProductLoader(String color, String size, String productName) {
//        this.color = color;
//        this.size = size;
//        this.productName = productName;
//    }

    public static DataProductLoader getDataProductLoader(){
        if(dataProductLoader == null){
            dataProductLoader = new DataProductLoader();
        }
        return dataProductLoader;
    }


    public void setValues(String color, String size, String productName){
        this.color = color;
        this.size = size;
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getProductName() {
        return productName;
    }
}
