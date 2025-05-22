package com.proyectoFinal.Back.repoeitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoFinal.Back.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    boolean findByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.password = ?1")
    boolean findByPassword(String password);
    @Query("SELECT * FROM Usuario WHERE email = ?1")
    Usuario SelectObject(String email);
}
