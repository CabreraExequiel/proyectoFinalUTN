package com.proyectoFinal.Back.controller;

import com.proyectoFinal.Back.Token.JwtUtil;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Usuario user) {
        boolean inicioExitoso = userService.iniciarSesion(user);
        if (!inicioExitoso) {
            return ResponseEntity.status(401).build(); // Mejor sin texto plano
        }

        Optional<Usuario> usuarioOptional = userService.buscarPorCorreo(user.getCorreo());
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        Usuario usuario = usuarioOptional.get();
        String token = jwtUtil.create(String.valueOf(usuario.getId()), usuario.getCorreo(), null);

        // Crear el response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);

        // Puedes personalizar qué datos del usuario deseas enviar
        Map<String, Object> usuarioData = new HashMap<>();
        usuarioData.put("id", usuario.getId());
        usuarioData.put("nombre", usuario.getNombre());
        usuarioData.put("correo", usuario.getCorreo());

        response.put("usuario", usuarioData);

        return ResponseEntity.ok(response);
    }


}
