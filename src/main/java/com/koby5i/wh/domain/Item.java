package com.koby5i.wh.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
//public  class Item implements Serializable {
public class Item{

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "qty")
    private int qty;

    @Column(name = "price")
    private double  price;

    // private no args constructor is need by JPA by Dan Vega
    private Item() {

    }

    private Item(String name, String description){
        this.name        = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty){
        this.qty = qty;
    }

    public double  getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%d, name='%s', description='%s', qty=%d]", id, name, description, qty);
    }
}
