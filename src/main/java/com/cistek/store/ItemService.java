package com.cistek.store;

public interface ItemService {

    Item retrieveItem(String itemId) throws ItemNotFoundException, InvalidItemIdException;
    String getItemSummary(String itemId)  throws ItemNotFoundException, InvalidItemIdException;
}
