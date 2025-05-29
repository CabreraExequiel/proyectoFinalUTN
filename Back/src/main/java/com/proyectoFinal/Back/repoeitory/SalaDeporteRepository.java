package com.proyectoFinal.Back.repoeitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectoFinal.Back.entity.SalaDeporte;

@Repository
public interface SalaDeporteRepository extends JpaRepository<SalaDeporte, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, si necesitas buscar por nombre o ubicación de la sala de deporte

}
