package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.ItemCatalog;

public interface IRepositoryCatalog {
    Integer searchByName(String name);
    Integer searchById(int id);
    void include(ItemCatalog Item);
    Boolean delete(int id);
    Boolean update(int id, String name, int year, String genre);
}

