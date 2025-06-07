package com.proyectoFinal.Back.repoeitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoFinal.Back.entity.SalaDeporte;

@Repository
public interface CustomRepository extends JpaRepository<SalaDeporte, Long> {

}
