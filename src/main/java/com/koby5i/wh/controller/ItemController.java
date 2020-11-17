package com.koby5i.wh.controller;

import com.koby5i.wh.commands.ItemForm;
import com.koby5i.wh.converters.ItemToItemForm;
import com.koby5i.wh.domain.Item;
import com.koby5i.wh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
//@RequestMapping("/items")
public class ItemController {

    // business logic in Service not in the controller.
    private ItemService itemService;
    private ItemToItemForm itemToItemForm;



    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setItemToItemForm(ItemToItemForm itemToItemForm) {
        this.itemToItemForm = itemToItemForm;
    }

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value="/index2", method = RequestMethod.GET)
    public String index2() {
        return "index2";
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String listItemsPub(Model model){
        model.addAttribute("pageTitle","Warehouse Items");
        model.addAttribute("items",itemService.list());
        return "itemslist_pub.html";
    }

    @RequestMapping(value="/user/index", method = RequestMethod.GET)
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String redirToUserList() {
        return "redirect:/user/items/list";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String redirToAdminList() {
        return "redirect:/admin/items/list";
    }



    @RequestMapping(value = "/user/items/list", method = RequestMethod.GET)
    public String listItemsUser(Model model){
        model.addAttribute("pageTitle","Warehouse Items");
        model.addAttribute("items",itemService.list());
        return "itemslist_user.html";
    }

    @RequestMapping(value = "/admin/items/list", method = RequestMethod.GET)
    public String listItemsAdmin(Model model){
        model.addAttribute("pageTitle","Warehouse Items");
        model.addAttribute("items",itemService.list());
        return "itemslist_admin.html";
    }

    @RequestMapping(value = "/admin/items/show/{id}", method = RequestMethod.GET)
    public String showItemDetails(@PathVariable(value="id") long id, Model model){
        model.addAttribute("item",itemService.read(id));
        return "itemshow.html";
    }

    @RequestMapping( value = "/admin/items/edit/{id}", method = RequestMethod.GET)
    public String editItem(@PathVariable(value="id") long id, Model model){
        Item item = itemService.read(id);
        ItemForm itemForm = itemToItemForm.convert(item);
        model.addAttribute("itemForm", itemForm);
        return "itemform.html";
    }

    @RequestMapping(value = "/admin/items/new", method = RequestMethod.GET)
    public String newItem(Model model){
        //Item item = itemService.read(id);
        Item item = new Item("default", "default");
        ItemForm itemForm = itemToItemForm.convert(item);
        model.addAttribute("itemForm", itemForm);
        return "itemform.html";
    }

    // here we receive updated item from form.
    // need to find in db and update.
    @RequestMapping(value = "/admin/items/saveorupdate", method = RequestMethod.POST)
    public String saveOrUpdateItem(@Valid ItemForm itemForm, BindingResult bindingResult,RedirectAttributes redirectAttr){
        if(bindingResult.hasErrors()){
            return "itemform";
        }
        Item savedItem = itemService.saveOrUpdateItemForm(itemForm);
        //return "redirect:/admin/items/show/" + savedItem.getId();
        redirectAttr.addFlashAttribute("message", "Item (ID:"+  savedItem.getId() + " Name:"+  savedItem.getName() +" ) was updated.");
        return "redirect:/admin/items/list";
    }

    // PROBLEM: method = RequestMethod.DELETE
    // @RequestMapping(value ="/items/delete/{id}", method = RequestMethod.DELETE)
    // <td><a th:href="${'/items/delete/' + item.id}">Delete</a> </td>
    // dostaje GET method  not supported jak tu mam DELETE

    @RequestMapping(value ="/admin/items/delete/{id}")
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttr){
        itemService.delete(id);
        redirectAttr.addFlashAttribute("message", "Item (ID:"+  id + " ) was deleted.");
        return "redirect:/admin/items/list";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("pageTitle","Warehouse NN Login");
        return "login";
    }

    @RequestMapping(value="/login-error", method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


}
