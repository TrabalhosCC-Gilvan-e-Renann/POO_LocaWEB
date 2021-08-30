package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.ItemCatalog;

import java.util.ArrayList;

public interface IRepositoryCatalog {
    ArrayList<ItemCatalog> searchByName(String name);
    Integer searchById(int id);
    void include(ItemCatalog Item);
    Boolean delete(int id);
    Boolean update(ItemCatalog itemCatalog);
}

