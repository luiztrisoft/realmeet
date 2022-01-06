package br.com.sw2you.realmeet;

//import br.com.sw2you.realmeet.api.facade.RoomsApi;
//import br.com.sw2you.realmeet.api.model.RoomDTO;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import br.com.sw2you.realmeet.api.facade.RoomsApi;
import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.domain.entity.Room;
import br.com.sw2you.realmeet.service.RoomService;
import org.springframework.http.ResponseEntity;

public class RoomController implements RoomsApi {



//    @Override
//    public CompletableFuture<ResponseEntity<Room>> listRooms(){
//        return CompletableFuture.supplyAsync(()-> ResponseEntity.ok(new Room().id(1L).name("Room #1")));
//    }

    private final RoomService roomService;

    public RoomController( RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public CompletableFuture<ResponseEntity<RoomDTO>> getRoom(String apiKey, Long id) {
        Objects.requireNonNull(id);
        Room room = roomService.findById(id);
        return new RoomDTO().name(room.getName()).id(room.getId()).seats(room.getSeats());
    }

}
