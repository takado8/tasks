package com.crud.tasks.trello.mapper;

import com.crud.tasks.trello.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapTrelloCardToDto() {
        // given
        TrelloCard trelloCard = new TrelloCard("name1", "desc", "pos", "1");
        // when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        // then
        assertEquals("name1", trelloCardDto.getName());
        assertEquals("desc", trelloCardDto.getDescription());
        assertEquals("pos", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void testMapFromDtoToTrelloCard() {
        // given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "desc", "pos", "1");
        // when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        // then
        assertEquals("name1", trelloCard.getName());
        assertEquals("desc", trelloCard.getDescription());
        assertEquals("pos", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());

    }

    @Test
    public void testMapToListDto(){
        // given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name1", false);
        trelloLists.add(trelloList);
        //when
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        // then
        assertEquals("1", trelloListDtos.get(0).getId());
        assertEquals("name1", trelloListDtos.get(0).getName());
        assertFalse(trelloListDtos.get(0).isClosed());
    }

    @Test
    public void testMapToList(){
        // given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "name1", false);
        trelloListDtos.add(trelloListDto1);
        // when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        // then
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("name1", trelloLists.get(0).getName());
        assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto(){
        // given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name1", false);
        trelloLists.add(trelloList);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("1", "name1", trelloLists);
        trelloBoards.add(trelloBoard);

        // when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        // then
        assertEquals("1", trelloBoardDtos.get(0).getId());
        assertEquals("name1", trelloBoardDtos.get(0).getName());
        assertEquals(1, trelloBoardDtos.get(0).getLists().size());

        var newTrelloList = trelloBoardDtos.get(0).getLists();
        assertEquals("1", newTrelloList.get(0).getId());
        assertEquals("name1", newTrelloList.get(0).getName());
        assertFalse(newTrelloList.get(0).isClosed());

    }

    @Test
    public void testMapToBoards() {
        // given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "name1", false);
        trelloListDtos.add(trelloListDto1);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","name1", trelloListDtos);
        trelloBoardDtos.add(trelloBoardDto);
        // when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        // then
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("name1", trelloBoards.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());

        var newTrelloList = trelloBoards.get(0).getLists();
        assertEquals("1", newTrelloList.get(0).getId());
        assertEquals("name1", newTrelloList.get(0).getName());
        assertFalse(newTrelloList.get(0).isClosed());
    }
}
