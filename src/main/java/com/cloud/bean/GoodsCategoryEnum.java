package com.cloud.bean;

public enum GoodsCategoryEnum {

 VEGETABLES(1, "蔬菜"),
 FRUIT(2, "水果"),
 MEAT(3, "肉类");

 private final int id;
 private final String name;

 GoodsCategoryEnum(int id, String name) {
 this.id = id;
 this.name = name;
 }

 public int getId() {
 return id;
 }

 public String getName() {
 return name;
 }

}