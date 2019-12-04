package com.koby5i.wh.controller;

import com.koby5i.wh.domain.Item;
import com.koby5i.wh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping( value ="/list", method = RequestMethod.GET)
    public Iterable<Item> list(){
        return itemService.list();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Item read(@PathVariable(value="id") long id){
//        return itemService.read(id);
//    }

    @RequestMapping("/hello")
    public String home(){
        return "Hello from RESTController";
    }

    @RequestMapping(value ="/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttr){
        itemService.delete(id);
        redirectAttr.addFlashAttribute("message", "Iteam was deleted");
        return "redirect:/items";
    }

}
