package com.koby5i.wh.service;

import com.koby5i.wh.domain.Item;
import com.koby5i.wh.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
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
        System.out.println("New item Id: " + item.getId());
        return itemRepository.save(item);
    }

    @Override
    public Item update(long id,Item newItem) {
        Item item = itemRepository.findById(id).orElse(null);
        if( item != null ) {
            item.setDescription(newItem.getDescription());
            item.setName(newItem.getName());
            item.setPrice(newItem.getPrice());
            item.setQty(newItem.getQty());
            System.out.println("Updated item Id: " + item.getId());
        }
        return itemRepository.save(item);
    }

    @Override
    public void delete(long id){
        itemRepository.deleteById(id);
    }
}
