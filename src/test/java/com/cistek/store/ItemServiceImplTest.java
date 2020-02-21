package com.cistek.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

public class ItemServiceImplTest {

    private ItemRepository itemRepositoryStub = new ItemRepositoryStub();
    private ItemService underTest = new ItemServiceImpl(itemRepositoryStub);

    /*
    *   Retrieve item tests
    */

    @Test
    void retrievesItemHappyPath() throws ItemNotFoundException, InvalidItemIdException {
        
        String itemId = "ITEM-FROZEN3242";
        Item actualItem = underTest.retrieveItem(itemId);

        assertThat(actualItem)
            .isEqualToComparingFieldByField(ItemRepositoryStub.items.get(itemId));
    }

    @Test
    void retrievesItemErrorInvalidItemId(){

        assertThatThrownBy(() -> {
            underTest.retrieveItem("INVALID-TEXT");
        })
            .isInstanceOf(InvalidItemIdException.class)
            .hasMessage("INVALID-TEXT is invalid");
    }

    @Test
    void retrievesItemErrorNullItemId(){

        assertThatThrownBy(() -> {
            underTest.retrieveItem(null);
        })
            .isInstanceOf(InvalidItemIdException.class)
            .hasMessage("null is invalid");
    }

    @Test
    void retrieveItemErrorItemNotFound(){

        assertThatThrownBy(() -> {
            underTest.retrieveItem("ITEM-0000");
        })
            .isInstanceOf(ItemNotFoundException.class)
            .hasMessage("ITEM-0000 is not found");
    }

    /*
    *   Get item summary tests
    */

    @ParameterizedTest
    @MethodSource("getItemSummaryHappyPaths")
    void getItemSummaryHappyPath(String itemId, String expectedSummary) throws ItemNotFoundException, InvalidItemIdException {
        
        String actualSummary = underTest.getItemSummary(itemId);

        assertThat(actualSummary).isEqualTo(expectedSummary);
    }

    static Stream<Arguments> getItemSummaryHappyPaths(){
        return Stream.of(
            Arguments.of("ITEM-FROZEN3242", "[ITEM-FROZEN3242] Frozen Peas - Some really nice peas from pods then frozen."),
            Arguments.of("ITEM-TOILET4325", "[ITEM-TOILET4325] Toilet Paper - Luxury toilet paper madness, made from other toilet papers..."),
            Arguments.of("ITEM-SODA4367", "[ITEM-SODA4367] Dr Pepper - Dr Pepper is a carbonated soft drink from America...")
        );
    }

    @Test
    void getItemSummaryErrorInvalidItemId(){

        assertThatThrownBy(() -> {
            underTest.getItemSummary("INVALID-TEXT");
        })
            .isInstanceOf(InvalidItemIdException.class)
            .hasMessage("INVALID-TEXT is invalid");
    }

    @Test
    void getItemSummaryErrorNullItemId(){

        assertThatThrownBy(() -> {
            underTest.getItemSummary(null);
        })
            .isInstanceOf(InvalidItemIdException.class)
            .hasMessage("null is invalid");
    }

    @Test
    void getItemSummaryErrorItemNotFound(){

        assertThatThrownBy(() -> {
            underTest.getItemSummary("ITEM-0000");
        })
            .isInstanceOf(ItemNotFoundException.class)
            .hasMessage("ITEM-0000 is not found");
    }
}