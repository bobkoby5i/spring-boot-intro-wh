package com.koby5i.wh.controller;

import com.koby5i.wh.converters.ItemToItemForm;
import com.koby5i.wh.domain.Item;
import com.koby5i.wh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRestController {

    // business logic in Service not in the controller.
    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }


    @RequestMapping(value = "/api/hello", method = RequestMethod.GET) // dziala jak jest @Restcontroller ale nie zadziala jak jest @Controler & thymeleaf
    public String RestApiHello(){
        return "Hello from RESTController";
    }

    @RequestMapping(value = "/api/items", method = RequestMethod.GET) // dziala jak jest @Restcontroller ale nie zadziala jak jest @Controler & thymeleaf
    public Iterable<Item> RestApiItems(){
        return itemService.list();
    }
}
