package br.com.sw2you.realmeet.integration;

import br.com.sw2you.realmeet.api.facade.RoomApi;
import br.com.sw2you.realmeet.core.BaseIntegrationTest;
import br.com.sw2you.realmeet.domain.entity.Room;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.utils.TestDataCreator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static br.com.sw2you.realmeet.utils.TestDataCreator.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomApiIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RoomApi roomApi;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    protected void setupEach() throws Exception{
        setLocalHostBasePath(roomApi.getApiClient(), "/v1");
    }

    @Test
    void testGetRoomSuccess(){
        var room = newRoomBuilder().build();
        roomRepository.saveAndFlush(room);

        assertNotNull(room.getId());
        assertTrue(room.getActive());

        var dto = roomApi.getRoom("api-key",DEFAULT_ROOM_ID);
        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getName(), dto.getName());
        assertEquals(room.getSeats(), dto.getSeats());
    }
}
