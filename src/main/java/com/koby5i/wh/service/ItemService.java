package com.koby5i.wh.service;

import com.koby5i.wh.commands.ItemForm;
import com.koby5i.wh.domain.Item;
import com.koby5i.wh.domain.dto.ApiDtoProduct;

import java.util.Optional;


public interface ItemService {
    Iterable<Item> list();
    Item readItemById(long itemId);
    Item read(long id);
    Item create(Item item);
    Item update(long id, Item item);
    void delete(long id);
    Item saveOrUpdateItemForm(ItemForm itemForm);
    Item postItem(ApiDtoProduct apiDtoProduct);
}
