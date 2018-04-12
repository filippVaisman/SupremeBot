package sample;

public class DataProductLoader {

    private String color;
    private String size;
    private String productName;
    private DataProductLoader dataProductLoader;

    DataProductLoader(String color, String size, String productName) {
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
