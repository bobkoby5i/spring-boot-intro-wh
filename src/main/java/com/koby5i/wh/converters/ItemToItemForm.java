package com.koby5i.wh.converters;

import com.koby5i.wh.commands.ItemForm;
import com.koby5i.wh.domain.Item;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemForm implements Converter<Item, ItemForm> {
    // tutaj przypisujemy do Form wartosci z obiektu item.
    // czyli wypelniamy formularz
    @Override
    public ItemForm convert(Item item) {
        ItemForm itemForm = new ItemForm();
        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setDescription(item.getDescription());
        itemForm.setPrice(item.getPrice());
        itemForm.setQty(item.getQty());
        return itemForm;
    }
}
