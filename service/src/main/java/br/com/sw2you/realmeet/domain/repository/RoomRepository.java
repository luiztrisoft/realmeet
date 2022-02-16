package br.com.sw2you.realmeet.domain.repository;

import java.util.Optional;
import br.com.sw2you.realmeet.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndActive(Long id, Boolean active);
}
