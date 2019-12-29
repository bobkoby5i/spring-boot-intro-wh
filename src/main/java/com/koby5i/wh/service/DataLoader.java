package com.koby5i.wh.service;

import com.koby5i.wh.domain.Item;
import com.koby5i.wh.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataLoader {
    private ItemRepository itemRepository;

    @Autowired
    public DataLoader(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    private void loadData(){
        // create an item
        Item item = new Item("ipad mini", "Apple tablet ipad mini");
        item.setPrice(13.99);
        item.setQty(22);
        item.setUpdatedAt(java.time.LocalDateTime.now());
        item.setCreatedAt(java.time.LocalDateTime.now());

        itemRepository.save(item);
    }
}
