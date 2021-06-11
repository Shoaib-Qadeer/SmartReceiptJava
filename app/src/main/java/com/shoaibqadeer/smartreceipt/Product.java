package com.shoaibqadeer.smartreceipt;

public class Product {
    int product_id;
    String product_name;
    String product_category;
    String product_descripton;
    int product_stock;
    Double product_price;


    Product(){}

    Product(int p_id, String p_name, String p_category, String p_decp,int p_stk, double p_prc){
        this.product_id=p_id;
        this.product_name=p_name;
        this.product_category=p_category;
        this.product_descripton=p_decp;
        this.product_stock=p_stk;
        this.product_price=p_prc;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public String getProduct_name() {
        return product_name;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public void decbyvalue_stock(int value) {
        this.product_stock = product_stock-value;
    }

}
