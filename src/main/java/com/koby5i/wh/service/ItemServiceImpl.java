package com.koby5i.wh.service;

import com.koby5i.wh.commands.ItemForm;
import com.koby5i.wh.converters.ItemFormToItem;
import com.koby5i.wh.domain.Item;
import com.koby5i.wh.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Null;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;
    private ItemFormToItem itemFormToItem;


    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemFormToItem itemFormToItem) {
        this.itemRepository = itemRepository;
        this.itemFormToItem = itemFormToItem;
    }

    @Override
    public Iterable<Item> list() {
        return itemRepository.findAll();
    }

    @Override
    public Item read(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item create(Item item) {
        item.setCreatedAt(java.time.LocalDateTime.now()); // did not come from the form
        System.out.println("Creating new item(" + item.getId()+ "," + item.getName() + ") CreatedAt:" + item.getCreatedAt() + " UpdatedAt:" + item.getUpdatedAt() + " ...");
        return itemRepository.save(item);
    }

    @Override
    public Item update(long id,Item newItem) {
        // find item id in repository
        // update existing item in DB with newItem data
        Item item = itemRepository.findById(id).orElse(null);
        if( item != null ) {
            item.setDescription(newItem.getDescription());
            item.setName(newItem.getName());
            item.setPrice(newItem.getPrice());
            item.setQty(newItem.getQty());
            item.setUpdatedAt(newItem.getUpdatedAt());
            System.out.println("Updating item (" + item.getId()+ "," + item.getName() + ") CreatedAt:" + item.getCreatedAt() + " UpdatedAt:" + item.getUpdatedAt() + " ...");
        }

        itemRepository.save(item);
        return item;
    }

    @Override
    public Item saveOrUpdateItemForm(ItemForm itemForm) {
        // convert form to item object
        // update existing item in DB with new data
        Item newItem = itemFormToItem.convert(itemForm);
        newItem.setUpdatedAt(java.time.LocalDateTime.now());

        Item savedItem;
        if (newItem.getId() != null ) {
            // here we update existing item
            long id = newItem.getId();
            savedItem = update(id,newItem);
        } else {
            savedItem = create(newItem);
        }

        System.out.println("Saved Item Id: " + savedItem.getId());
        return savedItem;
    }

    @Override
    public void delete(long id){
        itemRepository.deleteById(id);
    }
}
