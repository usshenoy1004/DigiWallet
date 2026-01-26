package com.orion.DigiWallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orion.DigiWallet.model.Card;
import com.orion.DigiWallet.model.Wallet;
import com.orion.DigiWallet.service.CardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//TODO: 4.5.1
// Create unit tests for CardController
// covering all CRUD operations
// Use MockMvc and Mockito for testing
// USE proper annotations and structure similar to UserControllerTest and WalletControllerTest
class CardControllerTest {


    //TODO: 4.5.2
    // create private variables for MockMvc, CardService and ObjectMapper
    // use @Autowired for MockMvc and ObjectMapper
    // use @MockitoBean for CardService

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("POST /api/cards/create")
    class CreateCardTests {

        @Test
        @DisplayName("Given valid card with wallet when createCard then return created card")
        void givenValidCardWithWallet_whenCreateCard_thenReturnCreatedCard() throws Exception {

            //TODO: 4.5.3
            // GIVEN


            // WHEN & THEN

        }
    }

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("GET /api/cards/{id}")
    class GetCardByIdTests {

        @Test
        @DisplayName("Given existing card id when getCardById then return card")
        void givenExistingCardId_whenGetCardById_thenReturnCard() throws Exception {
            //TODO: 4.5.4
            // GIVEN

            // WHEN & THEN

        }
    }

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("PUT /api/cards/{id}")
    class UpdateCardTests {

        @Test
        @DisplayName("Given valid update request when updateCard then return updated card")
        void givenValidUpdate_whenUpdateCard_thenReturnUpdatedCard() throws Exception {
            //TODO: 4.5.5
            // GIVEN
            // WHEN & THEN
        }
    }

    // ---------------------------------------------------------------------
    @Nested
    @DisplayName("DELETE /api/cards/{id}")
    class DeleteCardTests {

        @Test
        @DisplayName("Given card id when deleteCard then return success message")
        void givenCardId_whenDeleteCard_thenReturnSuccessMessage() throws Exception {
            //TODO: 4.5.6
            // GIVEN
            // WHEN & THEN
        }
    }
}
