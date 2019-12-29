package com.koby5i.wh.controller;

import com.koby5i.wh.commands.ItemForm;
import com.koby5i.wh.converters.ItemToItemForm;
import com.koby5i.wh.domain.Item;
import com.koby5i.wh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    // business logic in Service not in the controller.
    private ItemService itemService;
    private ItemToItemForm itemToItemForm;

    //@Autowired
    //public ItemController(ItemService itemService){
//        this.itemService = itemService;
//    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setItemToItemForm(ItemToItemForm itemToItemForm) {
        this.itemToItemForm = itemToItemForm;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String redirToList() {
        //return "index.html";
        return "redirect:/items/list";

    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET) // dziala jak jest @Restcontroller ale nie zadziala jak jest @Controler & thymeleaf
    public String home(){
        return "Hello from RESTController";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listItems(Model model){
        model.addAttribute("pageTitle","Warehouse Items");
        model.addAttribute("items",itemService.list());
        return "itemslist.html";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showItemDetails(@PathVariable(value="id") long id, Model model){
        model.addAttribute("item",itemService.read(id));
        return "itemshow.html";
    }

    @RequestMapping( value = "/edit/{id}", method = RequestMethod.GET)
    public String editItem(@PathVariable(value="id") long id, Model model){
        Item item = itemService.read(id);
        ItemForm itemForm = itemToItemForm.convert(item);
        model.addAttribute("itemForm", itemForm);
        return "itemform.html";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newItem(Model model){
        //Item item = itemService.read(id);
        Item item = new Item("default", "default");
        ItemForm itemForm = itemToItemForm.convert(item);
        model.addAttribute("itemForm", itemForm);
        return "itemform.html";
    }

    // here we receive updated item from form.
    // need to find in db and update.
    @RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
    public String saveOrUpdateItem(@Valid ItemForm itemForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "itemform";
        }
        Item savedItem = itemService.saveOrUpdateItemForm(itemForm);
        return "redirect:/items/show/" + savedItem.getId();
    }


    @RequestMapping(value ="/delete/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttr){
        itemService.delete(id);
        redirectAttr.addFlashAttribute("message", "Item was deleted.");
        return "redirect:/items/list";
    }

}
