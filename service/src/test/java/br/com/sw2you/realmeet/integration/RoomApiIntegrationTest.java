package br.com.sw2you.realmeet.integration;

import br.com.sw2you.realmeet.api.facade.RoomApi;
import br.com.sw2you.realmeet.core.BaseIntegrationTest;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static br.com.sw2you.realmeet.utils.TestDataCreator.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomApiIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RoomApi api;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    protected void setupEach() throws Exception{
        setLocalHostBasePath(api.getApiClient(), "/v1");
    }

    @Test
    void testGetRoomSuccess(){
        var room = newRoomBuilder().active(true).build();
        roomRepository.saveAndFlush(room);

        assertNotNull(room.getId());
        assertTrue(room.getActive());

        var dto = api.getRoom("api-key",DEFAULT_ROOM_ID);
        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getName(), dto.getName());
        assertEquals(room.getSeats(), dto.getSeats());
    }

    @Test
    void testGetRoomInactive(){
        var room = newRoomBuilder().active(false).build();
        roomRepository.saveAndFlush(room);

        assertFalse(room.getActive());
        assertThrows(HttpClientErrorException.NotFound.class, ()-> api.getRoom("api-key", room.getId()));
    }

    @Test
    void testGetRoomDoesNotExist(){
        assertThrows(HttpClientErrorException.NotFound.class, ()-> api.getRoom("api-key", DEFAULT_ROOM_ID));
    }
}
