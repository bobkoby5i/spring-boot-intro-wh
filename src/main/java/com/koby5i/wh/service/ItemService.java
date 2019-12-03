package com.koby5i.wh.service;

import com.koby5i.wh.domain.Item;


public interface ItemService {
    Iterable<Item> list();
    //Item read(long id);
}
