package com.koby5i.wh.controller;

import com.koby5i.wh.domain.Item;
import com.koby5i.wh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/items")
public class ItemController {

    // business logic in Service not in the controller.
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirToList(){
        return "redirect:/items/list";
    }

    @RequestMapping( value ="/list", method = RequestMethod.GET)
    public Iterable<Item> listItems(){
        return itemService.list();
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public Item getItem(@PathVariable(value="id") long id){
        return itemService.read(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Item createItem(@RequestBody Item item){
        return itemService.create(item);
    }


    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public Item updateItem(@PathVariable(value="id") long id, @RequestBody Item item){
        return itemService.update(id, item);
        //return "redirect:/product/show/" + savedProduct.getId();

    }


    @GetMapping("/hello")
    public String home(){
        return "Hello from RESTController";
    }

    @RequestMapping(value ="/delete/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttr){
        itemService.delete(id);
        redirectAttr.addFlashAttribute("message", "Item was deleted");
        return "redirect:/items/list";
    }

}
