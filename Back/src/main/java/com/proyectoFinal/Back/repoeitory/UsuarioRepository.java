package com.proyectoFinal.Back.repoeitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoFinal.Back.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Optional<Usuario> SelectCorreo(String correo);

}
