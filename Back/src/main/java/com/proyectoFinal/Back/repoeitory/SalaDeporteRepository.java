package com.proyectoFinal.Back.repoeitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyectoFinal.Back.entity.SalaDeporte;

@Repository
public interface SalaDeporteRepository extends JpaRepository<SalaDeporte, Long> {
    @Query("SELECT s FROM SalaDeporte s WHERE s.id = ?1")
    Optional<SalaDeporte> findById(Long id);

    @Query("SELECT s FROM SalaDeporte s WHERE :id NOT MEMBER OF s.id_integrantes")
    List<SalaDeporte> findSalasWhereUsuarioNotIntegrante(Long id);

    @Query("SELECT s FROM SalaDeporte s WHERE :id MEMBER OF s.id_integrantes")
    List<SalaDeporte> findSalasWhereUsuarioIntegrante(Long id);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM SalaDeporte s JOIN s.id_integrantes i " +
            "WHERE s.id = :idSala AND i = :idUsuario")
    boolean esIntegrante(@Param("idSala") Long idSala, @Param("idUsuario") Long idUsuario);
}
