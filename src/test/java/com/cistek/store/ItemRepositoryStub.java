package com.cistek.store;

import java.util.HashMap;
import java.util.Map;

public class ItemRepositoryStub implements ItemRepository {

    private static final String ITEM_ID_PREFIX = "ITEM-";

    private static final String FROZEN_PEAS_ITEM_ID = ITEM_ID_PREFIX + "FROZEN3242";
    private static final String TOILET_PAPER_ITEM_ID = ITEM_ID_PREFIX + "TOILET4325";
    private static final String DR_PEPPER_ITEM_ID = ITEM_ID_PREFIX + "SODA4367";

    static final Map<String, Item> items;

    static {
        items = new HashMap<String, Item>();
        items.put(FROZEN_PEAS_ITEM_ID, new Item(FROZEN_PEAS_ITEM_ID, "Frozen Peas", "Some really nice peas from pods then frozen."));
        items.put(TOILET_PAPER_ITEM_ID, new Item(TOILET_PAPER_ITEM_ID, "Toilet Paper", "Luxury toilet paper madness, made from other toilet papers from the largest rain forest."));
        items.put(DR_PEPPER_ITEM_ID, new Item(DR_PEPPER_ITEM_ID, "Dr Pepper", "Dr Pepper is a carbonated soft drink from America, " +
                 "created in the 1880s by pharmacist Charles Alderton in Waco, Texas, and first served around 1885. Dr Pepper was first" + 
                 "nationally marketed in the United States in 1904 and is now also sold in Europe, Asia, North and South America, and " +
                 "Australia, as well as New Zealand and South Africa as an imported good. Variants include Diet Dr Pepper and, beginning " +
                 "in the 2000s, a line of additional flavors."));
    }

    public Item retrieveItem(String itemId) {
        return items.get(itemId);
    }
}
