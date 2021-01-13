package com.example.demo;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.message.Message;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import com.sun.el.parser.ParseException;


@RestController
//@CrossOrigin(origins="http://localhost:8082", allowedHeaders="*")
public class Controller {
		
ObjectMapper om = new ObjectMapper();
@Resource 
ItemService itemservice;

private final ItemRepository itemRepo;

public Controller(ItemRepository itemRepo) {
    this.itemRepo = itemRepo;
}

@GetMapping("/items")
public ResponseEntity<List<Item>> searchForItems(@SearchSpec Specification<Item> specs) {
    return new ResponseEntity<>(itemRepo.findAll(Specification.where(specs)), HttpStatus.OK);
}

	/*
	 * @GetMapping(value = "/list-items") Page<Item> itemPageable(@RequestParam int
	 * page) { return itemRepo.findAll(PageRequest.of(page, 3)); }
	 * 
	 * 
	 * @GetMapping(value = "/itemList") public List<Item> getEmployees() { return
	 * itemRepo.findAll(); }
	 */
	 


@PostMapping(value = "/uploadImage")
public ResponseEntity<Object> uploadImage(@RequestParam(required=true, value="imageFile") MultipartFile imageFile, @RequestParam(value="jsondata") String jsondata) throws JsonMappingException, JsonProcessingException {
	byte[] bytes;
	 LocalDate localDate = LocalDate.now(); 
	 Date date=Date.valueOf(localDate);
	if(!imageFile.isEmpty()) {
		bytes=itemservice.saveImage(imageFile);
		
		
		Item item= om.readValue(jsondata, Item.class);	
		String name=item.getName();
		String ingredient=item.getIngredient();
		String recipe=item.getRecipe();
		String type=item.getType();
		
		item.setName(name);
		item.setIngredient(ingredient);
		item.setRecipe(recipe);
		item.setDate(date);
		item.setPicByte(bytes);
		item.setType(type);
		
		itemservice.insertItem(item);
		return new ResponseEntity<Object>("File uploaded successfully", HttpStatus.OK);
		
	}
	return new ResponseEntity<Object>("No file found", HttpStatus.NOT_FOUND);
		
}

@PostMapping(value="/uploadVideo")
public ResponseEntity<Object> uploadVideo(@RequestParam(required=true, value="videoFile") MultipartFile videoFile, @RequestParam(value="jsondata") String jsondata) throws Exception{
	String path;
	if(!videoFile.isEmpty()) {
		Item item= om.readValue(jsondata, Item.class);	
		String name=item.getName();
		
		path=itemservice.saveVideo(videoFile);
		System.out.println(path+"bytessssssssssssssss");
		item.setName(name);
		item.setVideo(path);
	
		itemservice.insertVideo(item);
		return new ResponseEntity<Object>("Video uploaded successfully", HttpStatus.OK);
	}
	return new ResponseEntity<Object>("No Video found", HttpStatus.NOT_FOUND);
}

@PostMapping(value = "/deleteItem")
public void deleteItem(@RequestBody Item item) {
 itemservice.deleteItem(item);
}


@PutMapping(value = "/updateItem")
public void updateEmployee(@RequestBody Item item) {
 itemservice.updateItem(item);
}

}