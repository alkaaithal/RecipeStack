package com.example.demo;


import java.nio.file.Path;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.multipart.MultipartFile;
public interface ItemsDAO{
List<Item> findAll();
	
	  void insertItem(Item item);
	  
	  void updateItem(Item item); 
	  byte[] saveImage(MultipartFile imagefile) throws Exception; 
	  String saveVideo(MultipartFile videofile) throws Exception;
	  /////public void deleteItem(Item item); void executeUpdateItem(Item item);
	  
	  void insertItem(String name, String ingredient, String recipe, MultipartFile
	  imageFile); //void insertVideo(Item item);


	void deleteItem(Item item);

	void executeUpdateItem(Item item);

	void insertVideo(Item item);
	 }
