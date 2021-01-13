package com.example.demo;

import java.nio.file.Path;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
	List<Item> findAll();
	
	  void insertItem(String name, String ingredient, String recipe, MultipartFile
	  imageFile); void insertItem(Item item); 
	  void updateItem(Item item); 
	  void deleteItem(Item item); 
	  void executeUpdateItem(Item item); 
	  byte[] saveImage(MultipartFile imageFile); 
	  String saveVideo(MultipartFile videofile)
	  throws Exception; 
	  //void insertVideo(Item item);

	Page<Item> readAll(Pageable pageable);

	void insertVideo(Item item);
	 
}
