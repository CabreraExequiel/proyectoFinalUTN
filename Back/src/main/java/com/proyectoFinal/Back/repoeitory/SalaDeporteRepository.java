package com.proyectoFinal.Back.repoeitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoFinal.Back.entity.SalaDeporte;

@Repository
public interface SalaDeporteRepository extends JpaRepository<SalaDeporte, Long> {
    @Query("SELECT s FROM SalaDeporte s WHERE s.id = ?1")
    Optional<SalaDeporte> findById(Long id);

}
