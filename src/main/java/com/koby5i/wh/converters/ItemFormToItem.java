package com.koby5i.wh.converters;

import com.koby5i.wh.commands.ItemForm;
import com.koby5i.wh.domain.Item;
//import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class ItemFormToItem implements Converter<ItemForm, Item> {

    @Override
    public Item convert(ItemForm itemForm) {
        Item item = new Item();
        if (itemForm.getId() != null  && !StringUtils.isEmpty(itemForm.getId())) {
            // here I am replacing with simple set id. this object id is something bson mongo
            // item.setId(new ObjectId(itemForm.getId()));
            item.setId(itemForm.getId());
        }
        item.setDescription(itemForm.getDescription());
        item.setPrice(itemForm.getPrice());
        item.setQty(itemForm.getQty());
        item.setName(itemForm.getName());
        return item;
    }
}
