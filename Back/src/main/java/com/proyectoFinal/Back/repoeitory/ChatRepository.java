
package com.proyectoFinal.Back.repoeitory;

import com.proyectoFinal.Back.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByIdSalaOrderByTimestampAsc(String idSala);
}
