package com.foodie.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Menu {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key menuId;

    @Persistent
    private String menuName;

    @Persistent
    private String description;

    // @Persistent
    // private Key resaurantId;

    @Persistent(defaultFetchGroup = "true")
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public String getMenuId() {
        return KeyFactory.keyToString(menuId);
    }

    public void setMenuId(Key menuId) {
        this.menuId = menuId;
    }

    public Menu(String menuName, String description) {
        this.menuName = menuName;
        this.description = description;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

}
