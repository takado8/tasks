package com.crud.tasks.trello.facade;

import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.domain.*;
import com.crud.tasks.trello.mapper.TrelloMapper;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());
        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(0);
    }

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoardsDtos =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDtos);
        when(trelloMapper.mapToBoards(trelloBoardsDtos)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(mappedTrelloBoards)).thenReturn(trelloBoardsDtos);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDto = trelloFacade.fetchTrelloBoards();
        // Then
        assertThat(trelloBoardDto).isNotNull();
        assertThat(trelloBoardDto.size()).isEqualTo(1);

        trelloBoardDto.forEach(BoardDto -> {

            assertThat(BoardDto.getId()).isEqualTo("1");
            assertThat(BoardDto.getName()).isEqualTo("test");

            BoardDto.getLists().forEach(trelloListDto -> {
                assertThat(trelloListDto.getId()).isEqualTo("1");
                assertThat(trelloListDto.getName()).isEqualTo("test_list");
                assertThat(trelloListDto.isClosed()).isFalse();
            });
        });
    }

    @Test
    void shouldCreateCard() {
        // given
        String name = "name_test1";
        String desc = "desc_test1";
        String pos = "1";
        String listId = "1";
        String url = "testUrl";

        TrelloCardDto trelloCardDto = new TrelloCardDto(name, desc, pos, listId);
        TrelloCard trelloCard = new TrelloCard(name, desc, pos, listId);
        Trello trello = new Trello(1,1);
        AttachmentsByType attachments = new AttachmentsByType(trello);
        Badges badges = new Badges(1, attachments);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(listId, name, url, badges);

        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        // when
        CreatedTrelloCardDto result = trelloFacade.createCard(trelloCardDto);

        // then
        assertThat(result.getId()).isEqualTo(listId);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getShortUrl()).isEqualTo(url);
        assertThat(result.getBadges()).isNotNull();

        assertThat(result.getBadges().getVotes()).isEqualTo(1);
        assertThat(result.getBadges().getAttachmentsByType().getTrello().getCard()).isEqualTo(1);
        assertThat(result.getBadges().getAttachmentsByType().getTrello().getBoard()).isEqualTo(1);
    }
}
