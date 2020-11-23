package com.koby5i.wh.controller;

import com.koby5i.wh.converters.ItemToItemForm;
import com.koby5i.wh.domain.Item;
import com.koby5i.wh.domain.dto.ApiDtoProduct;
import com.koby5i.wh.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Slf4j
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
    @RequestMapping(value = "/api/items", method = RequestMethod.GET)
    public ResponseEntity<?> RestApiItems(@RequestHeader Map<String, String> headers){
        ArrayList<String> arr = new ArrayList<>();

        arr.add("https://bobkoby5i.github.io/intro-angular/products");
        arr.add("http://localhost:4200/products");

        headers.forEach((key, value) -> {
            System.out.println("Header "+ key+" = "+ value);
        });

        if(!headers.containsKey("referer"))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        else
            System.out.println("not found");

        System.out.println("Referer exists checking");
        String referer = headers.get("referer");
        if (!arr.contains(referer))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>( itemService.list(), HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value ="/api/items/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long  deleteItemById(@PathVariable long id){
        itemService.delete(id);
        return id;
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/api/items/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getItemById(@PathVariable long itemId) {
        return itemService.readItemById(itemId);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/api/items/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.CREATED )
    public Item postItem(@Valid @RequestBody ApiDtoProduct apiDtoProduct) {
        log.info(String.format("POST received from api product (name = %s description %s)", apiDtoProduct.getName(),apiDtoProduct.getDescription()));
        log.info(String.format("input %s",apiDtoProduct));

        Item item = itemService.postItem(apiDtoProduct);
        return item;
    }



}
