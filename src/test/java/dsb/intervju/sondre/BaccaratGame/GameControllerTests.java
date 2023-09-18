package dsb.intervju.sondre.BaccaratGame;

import dsb.intervju.sondre.BaccaratGame.exceptions.OutOfCardsException;
import dsb.intervju.sondre.BaccaratGame.model.Card;
import dsb.intervju.sondre.BaccaratGame.model.Deck;
import dsb.intervju.sondre.BaccaratGame.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.reactive.function.client.WebClient;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebClient.ResponseSpec responseSpec;

    private Deck deck;

    @BeforeEach
    public void setup() {
        deck = MockUtils.createMockedFullDeck();
    }

    @Test
    public void testPlayBaccaratOnce() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/play"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testPlayBaccaratMultipleRounds() throws Exception {
        // Assuming rounds are passed as a parameter
        mockMvc.perform(MockMvcRequestBuilders.post("/play")
                        .param("numberOfRounds", "5"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPlayBaccaratNewDeckEachRound() throws Exception {
        // Assuming newDeck is passed as a parameter to indicate using a new deck for each round
        mockMvc.perform(MockMvcRequestBuilders.post("/play")
                        .param("numberOfRounds", "5")
                        .param("newDeck", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testOutOfCardsException() throws Exception {
        // Mock the WebClient to throw an OutOfCardsException
        when(responseSpec.bodyToMono(Card[].class)).thenThrow(new OutOfCardsException("Out of Cards"));

        mockMvc.perform(MockMvcRequestBuilders.post("/play")
                        .param("numberOfRounds", "100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Error: No more cards in the deck.")));
    }
}
