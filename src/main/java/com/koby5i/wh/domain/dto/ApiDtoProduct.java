package com.koby5i.wh.domain.dto;

//import lombok.Data;

import lombok.Data;

@Data
public class ApiDtoProduct {
    private String name;
    private String description;
    private int qty;
    private double price;
}
