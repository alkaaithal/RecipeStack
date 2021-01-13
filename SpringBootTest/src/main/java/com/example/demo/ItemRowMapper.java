package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ietf.jgss.Oid;
import org.springframework.jdbc.core.RowMapper;

public class ItemRowMapper implements RowMapper<Item> {
@Override
public Item mapRow(ResultSet rs, int arg1) throws SQLException {
Item item = new Item();
item.setId(rs.getInt("id"));
item.setName(rs.getString("name"));
item.setIngredient(rs.getString("ingredient"));
item.setRecipe(rs.getString("recipe"));
item.setDate(rs.getDate("date"));
item.setPicByte(rs.getBytes("photo"));
item.setVideo(rs.getString("video"));
item.setType(rs.getString("type"));
        return item;
}
}
