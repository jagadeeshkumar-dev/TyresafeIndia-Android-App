package com.app.tyresafeindia;

public class cars {

    private  int carid;
    private int qtyml;
    private int totalml;
    private int price;
    private String vechilename;
    private String tyresize;
    private String imageurl;

    public cars(int carid, String vechilename, String tyresize, int qtyml, int totalml, int price, String imageurl) {
        this.carid = carid;
        this.qtyml = qtyml;
        this.totalml = totalml;
        this.price = price;
        this.vechilename = vechilename;
        this.tyresize = tyresize;
        this.imageurl = imageurl;
    }




    public int getcarId() {
        return carid;
    }

    public int getQtyml() {
        return qtyml;
    }

    public int getTotalml() {
        return totalml;
    }

    public int getPrice() {
        return price;
    }

    public String getVechilename() {
        return vechilename;
    }

    public String getTyresize() {
        return tyresize;
    }
    public String getImageurl() {
        return imageurl;
    }
}
