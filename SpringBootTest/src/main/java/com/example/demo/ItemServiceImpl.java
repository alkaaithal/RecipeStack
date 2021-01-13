package com.example.demo;

import java.nio.file.Path;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepo;
	
	@Autowired
    public ItemServiceImpl(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }
	
	@Resource
	ItemsDAO itemdao;

	@Override
	public List<Item> findAll() {
		return itemdao.findAll();
	}

	
	  @Override public void insertItem(String name,String ingredient,String
	  recipe,MultipartFile imageFile) { itemdao.insertItem(name, ingredient,
	  recipe, imageFile); }
	  
	  @Override public void insertItem(Item item) { itemdao.insertItem(item); }
	  
	  @Override 
	  public void updateItem(Item item) { 
		  itemdao.updateItem(item);
	  
	  }
	  
	  @Override public void executeUpdateItem(Item item) {
	  itemdao.executeUpdateItem(item);
	  
	  }
	  
	  
	  @Override public void deleteItem(Item item) { 
		  itemdao.deleteItem(item);
	  
	  }
	  
	  @Override public byte[] saveImage(MultipartFile imageFile) 
	  { 
		  byte[] bytes =null; 
		  try { 
			  bytes=itemdao.saveImage(imageFile); } 
		  catch (Exception e) { e.printStackTrace(); } 
		  return bytes;
	  
	  }
	  

	  
	
	  @Override public void insertVideo(Item item) { itemdao.insertVideo(item); }
	 

	@Override
	public String saveVideo(MultipartFile videofile) throws Exception {
		String bytes; 
		bytes=itemdao.saveVideo(videofile);
		 return bytes;
		
	}


	@Override
	public Page<Item> readAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return itemRepo.findAll(pageable);
	}
	 

}