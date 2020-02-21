package com.cistek.store;

import java.util.Arrays;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    private static final String ITEM_ID_PREFIX = "ITEM-";
    private static final long SUMMARY_MAX_WORD_COUNT = 9;

    private final ItemRepository itemRepository;

	public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
	}

    @Override
    public Item retrieveItem(String itemId) throws ItemNotFoundException, InvalidItemIdException {
        validateItemId(itemId);
        return Optional.ofNullable(itemRepository.retrieveItem(itemId)).orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    @Override
    public String getItemSummary(String itemId) throws ItemNotFoundException, InvalidItemIdException {
        Item itemToSummarise = retrieveItem(itemId);
        return summarise(itemToSummarise);
    }

    private void validateItemId(String itemId) throws InvalidItemIdException {
        if(itemId == null){
            throw new InvalidItemIdException("null");
        }
        if(!itemId.startsWith(ITEM_ID_PREFIX)){
            throw new InvalidItemIdException(itemId);
        }
    }

    private String summarise(Item itemToSummarise){
        String[] descriptionWords = itemToSummarise.getDescription().stripLeading().split(" ");
        String summary = Arrays.stream(descriptionWords)
            .limit(SUMMARY_MAX_WORD_COUNT)
            .reduce(String.format("[%s] %s -", itemToSummarise.getId(), itemToSummarise.getName()), (a,b) -> a + " " + b);
        return descriptionWords.length > SUMMARY_MAX_WORD_COUNT ? sanitiseFinalWord(summary) + "..." : summary;
    }

    private String sanitiseFinalWord(String summary) {
        if (Character.isLetter(summary.charAt(summary.length() - 1))){
            return summary;
        }
        return summary.substring(0, summary.length() - 1);
    }
}