package com.proyectoFinal.Back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoFinal.Back.Token.JwtUtil;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @GetMapping("/ver/usuario")
    @ResponseBody
    public List<Usuario> verUsuario(){
        return userService.verUsuario();
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody Usuario user) {
        boolean registroExitoso = userService.registrarUsuario(user);
        if (!registroExitoso) {
            return ResponseEntity.status(400).body("Error al registrar el usuario");
        }
        return ResponseEntity.ok("Registro exitoso");
    }



    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody Usuario user,HttpSession session) {
        boolean inicioExitoso = userService.iniciarSesion(user);
        if (!inicioExitoso) {
            return ResponseEntity.status(401).body("Error al iniciar sesión");
        }
        // Obtener el usuario completo desde la BD para generar token con id real
        Optional<Usuario> usuarioOptional = userService.buscarPorCorreo(user.getCorreo());
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
        Usuario usuario = usuarioOptional.get();

        session.setAttribute("usuario", usuario); // Guardo info del usuario en cookie de sesión

        String token = jwtUtil.create(String.valueOf(usuario.getId()), usuario.getCorreo(), null);
        return ResponseEntity.ok(token);
    }

}
