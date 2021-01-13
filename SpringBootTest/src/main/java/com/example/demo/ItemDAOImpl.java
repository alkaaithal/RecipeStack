package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.sun.el.parser.ParseException;

@Repository
public class ItemDAOImpl implements ItemsDAO, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemDAOImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
}  
NamedParameterJdbcTemplate template;  
	
	  @Override 
	  public List<Item> findAll() { 
		  return template.query("select *  from item", new ItemRowMapper()); }
	 

	
	   @Override
	   
	   public void insertItem(Item item) {
	   
	   final String sql ="insert into item(name, ingredient,recipe,date,photo,video,type) values(:name,:ingredient,:recipe,:date,:photo, :video, :type)";
	   
	   KeyHolder holder = new GeneratedKeyHolder(); 
	   SqlParameterSource param = new MapSqlParameterSource()
	   
	   .addValue("name", item.getName()) 	
	   .addValue("ingredient",item.getIngredient()) 
	   .addValue("recipe", item.getRecipe()) 
	   .addValue("date",item.getDate()) 
	   .addValue("photo", item.getPicByte()) 
	   .addValue("video", item.getVideo())
	   .addValue("type", item.getType());

	   template.update(sql,param, holder); }
	 

	   @Override
	   public void updateItem(Item item) {
	   	// TODO Auto-generated method stub
	   	final String sql = "update item set name=:name, ingredient=:ingredient, recipe=:recipe where id=:id";
	       KeyHolder holder = new GeneratedKeyHolder();
	       SqlParameterSource param = new MapSqlParameterSource()
	   .addValue("id", item.getId())
	   .addValue("name", item.getName())
	   .addValue("ingredient", item.getIngredient())
	   .addValue("recipe", item.getRecipe());
	       template.update(sql,param, holder);

	   	
	   }

	
	/*
	 * @Override
	 * 
	 * public void insertItem(String name,String ingredient,String
	 * recipe,MultipartFile imageFile) {
	 * 
	 * final String sql =
	 * "insert into item(name, ingredient,recipe,date) values(:name,:ingredient,:recipe,:date)"
	 * ;
	 * 
	 * KeyHolder holder = new GeneratedKeyHolder(); SqlParameterSource param = new
	 * MapSqlParameterSource() //.addValue("id", id)
	 * 
	 * 
	 * .addValue("name", name) .addValue("ingredient", ingredient)
	 * .addValue("recipe", recipe) .addValue("date", localDate);
	 * 
	 * template.update(sql,param, holder); }
	 * 
	 */

	
	   @Override 
	   public void deleteItem(Item item) { 
	   
	   final String sql = "delete from item where id=:id"; 
	   Map<String,Object> map=new HashMap<String,Object>(); 
	   map.put("id", item.getId());
	   template.execute(sql,map,new PreparedStatementCallback<Object>() {
	   
	   @Override 
	   public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException 
	   { 
		   return ps.executeUpdate(); 
		   } 
	   }); 
	   }

	   
	   @Override 
	   public byte[] saveImage(MultipartFile imagefile) throws Exception {
	   // TODO Auto-generated method stub
	   //byte[] bytes = null;
	   String folder="C:/Users/aithaa/Alakananda/SpringBootTest/src/main/resources/static/img/";
	   //String folder="C:/Users/aithaa/Pictures/"; 
	   byte[] bytes = imagefile.getBytes(); 
	   Path path=Paths.get(folder +
	   imagefile.getOriginalFilename()); Files.write(path, bytes);
	   
	   return bytes; 
	   }
	   
	   @Override public String saveVideo(MultipartFile videofile) throws Exception{
		// TODO Auto-generated method stub
		   
	   String folder= "C:/Users/aithaa/Alakananda/SpringBootTest/src/main/resources/static/";
		
		/*
		 * String folder="/SpringBootTest/src/main/resources/static";
		 * 
		 * Path currentRelativePath = Paths.get(folder); String s =
		 * currentRelativePath.toAbsolutePath().toString();
		 * System.out.println("Current relative path is: " + s);
		 */
		  
		  byte[] bytes = videofile.getBytes();
		  Path path=Paths.get(folder + videofile.getOriginalFilename());
		  Files.write(path, bytes);
		  
		  return videofile.getOriginalFilename();
	   }
	   
	  
	
	  @Override 
	  public void insertVideo(Item item) {
	  
	  String name = null; List<Item> it=template.query("select * from item", new
	  ItemRowMapper());
	  
	  Iterator<Item> iterator = it.iterator(); 
	  while (iterator.hasNext()) { 
		  Item ite = iterator.next(); 
		  name=ite.getName(); 
		  } 
	  if (name.contains(item.getName())) 
	  { 
		  final String sql = "update item set video=:video where name=:name"; 
		  KeyHolder holder = new GeneratedKeyHolder(); 
		  SqlParameterSource param = new MapSqlParameterSource()
	  .addValue("name", item.getName()) 
	  .addValue("video", item.getVideo());
	  template.update(sql,param, holder); 
	  } else {
		  insertItem(item);
		  }
	  
	  }
	 
	  











@Override
public void insertItem(String name, String ingredient, String recipe, MultipartFile imageFile) {
	// TODO Auto-generated method stub
	
}



@Override
public void executeUpdateItem(Item item) {
	// TODO Auto-generated method stub
	
}

}
