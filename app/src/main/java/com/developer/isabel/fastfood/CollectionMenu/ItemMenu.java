package com.developer.isabel.fastfood.CollectionMenu;

public class ItemMenu {
    private String nameM;
    private String priceM;
    private String codigoM;
    private String url;
    private  String id;
    public ItemMenu(String nameM, String priceM, String codigoM, String url, String id){
        this.nameM=nameM;
        this.priceM=priceM;
        this.codigoM=codigoM;
        this.url=url;
        this.id=id;
    }

    public String getNameM() {
        return this.nameM;
    }

    public void setNameM(String nameM) {
        this.nameM = nameM;
    }

    public String getPriceM() {
        return this.priceM;
    }

    public void setPriceM(String priceM) {
        this.priceM = priceM;
    }

    public String getCodigoM() {
        return this.codigoM;
    }

    public void setCodigoM(String codigoM) {
        this.codigoM = codigoM;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
