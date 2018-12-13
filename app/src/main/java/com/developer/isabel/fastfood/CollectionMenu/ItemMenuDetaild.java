package com.developer.isabel.fastfood.CollectionMenu;

public class ItemMenuDetaild {
    private String namemenu;
    private String pricemenu;
    private String codigomenu;
    private String urlmenu;
    private String idmenu;
    private String descriptionmenu;
    private String restaurantemenu;

    public ItemMenuDetaild(String namemenu, String pricemenu, String codigomenu, String urlmenu, String idmenu,String descriptionmenu, String restaurantemenu){
        this.namemenu=namemenu;
        this.pricemenu=pricemenu;
        this.codigomenu=codigomenu;
        this.urlmenu=urlmenu;
        this.idmenu=idmenu;
        this.descriptionmenu=descriptionmenu;
        this.restaurantemenu=restaurantemenu;
    }

    public String getNamemenu() {
        return this.namemenu;
    }

    public void setNamemenu(String namemenu) {
        this.namemenu = namemenu;
    }

    public String getPricemenu() {
        return this.pricemenu;
    }

    public void setPricemenu(String pricemenu) {
        this.pricemenu = pricemenu;
    }

    public String getCodigomenu() {
        return this.codigomenu;
    }

    public void setCodigomenu(String codigomenu) {
        this.codigomenu = codigomenu;
    }

    public String getUrlmenu() {
        return this.urlmenu;
    }

    public void setUrlmenu(String urlmenu) {
        this.urlmenu = urlmenu;
    }

    public String getIdmenu() {
        return this.idmenu;
    }

    public void setIdmenu(String idmenu) {
        this.idmenu = idmenu;
    }

    public String getDescriptionmenu() {
        return this.descriptionmenu;
    }

    public void setDescriptionmenu(String descriptionmenu) {
        this.descriptionmenu = descriptionmenu;
    }

    public String getRestaurantemenu() {
        return this.restaurantemenu;
    }

    public void setRestaurantemenu(String restaurantemenu) {
        this.restaurantemenu = restaurantemenu;
    }
}
