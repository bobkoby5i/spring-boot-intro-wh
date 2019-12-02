package com.koby5i.wh;


import com.koby5i.wh.domain.Item;
import com.koby5i.wh.repository.ItemRepository;

import com.koby5i.wh.service.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
//import java.util.logging.Logger;

@SpringBootApplication
public class WhApplication {

	//co to jest
	private static final Logger logger = LoggerFactory.getLogger(WhApplication.class);
	//private static final Logger logger = LoggerFactory

	// ItemRepository
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	DataLoader dataLoader;

	public static void main(String[] args) {
		SpringApplication.run(WhApplication.class, args);
		//System.out.println("Hello World");
	}

	// ItemsConstructor
	@PostConstruct
	void seeItems() {
		logger.info("seeItems methid called ...");
		for(Item item : itemRepository.findAll()){
			logger.info(item.toString());
		}
	}

}
