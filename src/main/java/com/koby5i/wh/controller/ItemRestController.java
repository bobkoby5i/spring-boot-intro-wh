package com.koby5i.wh.controller;

import com.koby5i.wh.converters.ItemToItemForm;
import com.koby5i.wh.domain.Item;
import com.koby5i.wh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    //@CrossOrigin(origins = "http://localhost:4200")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/items", method = RequestMethod.GET) // dziala jak jest @Restcontroller ale nie zadziala jak jest @Controler & thymeleaf
    public Iterable<Item> RestApiItems(){
        return itemService.list();
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(path = "/api/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getItemById(@PathVariable long itemId) {
        return itemService.readItemById(itemId);
    }
}
