package br.com.sw2you.realmeet.controller;

//import br.com.sw2you.realmeet.api.facade.RoomsApi;
//import br.com.sw2you.realmeet.api.model.RoomDTO;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import br.com.sw2you.realmeet.api.facade.RoomsApi;
import br.com.sw2you.realmeet.api.model.RoomDTO;
import br.com.sw2you.realmeet.service.RoomService;
import br.com.sw2you.realmeet.util.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController implements RoomsApi {
    private final Executor controllerExecutor;
    private final RoomService roomService;

    public RoomController(Executor controllerExecutor, RoomService roomService) {
        this.controllerExecutor = controllerExecutor;
        this.roomService = roomService;
    }

    @Override
//    public CompletableFuture<ResponseEntity<RoomDTO>> getRoom(String apiKey, Long id) {
    public CompletableFuture<ResponseEntity<RoomDTO>> getRoom(Long id) {
        Objects.requireNonNull(id);
        //return CompletableFuture.supplyAsync(()-> ResponseEntity.ok(roomService.getRoom(id)));
        return CompletableFuture.supplyAsync(()-> roomService.getRoom(id), controllerExecutor).thenApply(ResponseEntityUtils::ok);
    }

}
