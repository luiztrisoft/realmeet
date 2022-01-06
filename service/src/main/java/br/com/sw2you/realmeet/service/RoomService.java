package br.com.sw2you.realmeet.service;

import java.util.Objects;
import br.com.sw2you.realmeet.domain.entity.Room;
import br.com.sw2you.realmeet.domain.repository.RoomRepository;
import br.com.sw2you.realmeet.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room findById(Long id){
        Objects.requireNonNull(id);
        return roomRepository.findById(id).orElseThrow(RoomNotFoundException::new); //()->new RoomNotFoundException() lambda or reference
    }
}
