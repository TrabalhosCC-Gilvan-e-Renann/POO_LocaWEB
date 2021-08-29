package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.ItemCatalog;

public interface IRepositoryCatalog {
    public abstract void adicionar(ItemCatalog item);
    void remover(ItemCatalog item);
    void atualizar(ItemCatalog item);
    Account consultar(ItemCatalog item);
    Account consultar(int numero);
}
