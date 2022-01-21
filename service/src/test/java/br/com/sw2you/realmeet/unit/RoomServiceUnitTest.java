package br.com.sw2you.realmeet.unit;

import static br.com.sw2you.realmeet.utils.MapperUtils.roomMapper;
import static br.com.sw2you.realmeet.utils.TestConstants.DEFAULT_ROOM_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;
import br.com.sw2you.realmeet.core.BaseUnitTest;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.service.RoomService;
import br.com.sw2you.realmeet.utils.TestDataCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class RoomServiceUnitTest extends BaseUnitTest {

    private RoomService victim;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    void setupEach(){
        victim = new RoomService(roomRepository, roomMapper());
    }

    @Test
    void testGetRoom(){
        var room = TestDataCreator.newRoomBuilder().id(DEFAULT_ROOM_ID).build();
        Mockito.when(roomRepository.findById(DEFAULT_ROOM_ID)).thenReturn(Optional.of(room));

        var dto = victim.getRoom(DEFAULT_ROOM_ID);
        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getName(), dto.getName());
        assertEquals(room.getSeats(), dto.getSeats());
    }
}

/*
@Mock: injeta um objeto ficticio e assim podemos controlar o que vai recuperar

NOTA: Por isso é interessante usar injeção de dependencia através dos construtores, pois assim, na hora dos testes podemos
instanciar passando outros objetos que não são gerenciados pela arquitetura do spring. Sendo assim podemos passar mocks,
objetos proprios, etc.
 */