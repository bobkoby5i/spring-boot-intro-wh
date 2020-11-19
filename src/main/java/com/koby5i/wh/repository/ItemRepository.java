package com.koby5i.wh.repository;

import java.util.List;
import com.koby5i.wh.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    //List<Item> findByFirstName(String FirstName);
    @Override
    List<Item> findAll();



    List<Item> findAllByOrderByIdDesc();

    //@Override
    //Item findOne(long id);

    //@Override
    //ItemEntity save(ItemEntity itemEntity);
    //Item save(Item item);

    //@Override
    //void deleteById(Long id);
}

