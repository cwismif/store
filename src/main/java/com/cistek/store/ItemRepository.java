package com.cistek.store;

public interface ItemRepository {
    Item retrieveItem(String itemId) throws ItemNotFoundException;
}
