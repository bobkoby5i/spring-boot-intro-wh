package com.koby5i.wh.service;

import com.koby5i.wh.domain.Item;
import com.koby5i.wh.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    //@Override
    public Iterable<Item> list() {
        return itemRepository.findAll();
    }

    //@Override
    //public Item read(long id) {
    //    return itemRepository.findOne(id);
    //}
    public void delete(Long id){
        itemRepository.deleteById(id);
    }
}
