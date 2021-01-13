package com.example.demo;

import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ietf.jgss.Oid;


@Entity
public class Item {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	private String name, ingredient, recipe;
	private String video, type;
	Date date;
	byte[] photo;

	
	
	public int getId() {
		return id;			
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(String ingredient) {
		this.ingredient=ingredient;
	}
	
	public String getRecipe() {
		return recipe;
	}
	
	public void setRecipe(String recipe) {
		this.recipe=recipe;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date=date;
	}
	
	 public byte[] getPicByte() {   
		return photo;
	}
		
	public void setPicByte(byte[] photo) {
	
		this.photo = photo;
		 
 }

	public String getVideo() {
		return video;
	}
	
	public void setVideo(String video) {
		this.video=video;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type=type;
	}
	

}
