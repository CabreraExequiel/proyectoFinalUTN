package com.proyectoFinal.Back.repoeitory;

import com.proyectoFinal.Back.entity.SalaReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaReunionRepositorio  extends JpaRepository<SalaReunion, Long> {
}
