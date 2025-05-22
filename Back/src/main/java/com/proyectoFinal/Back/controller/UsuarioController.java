package com.proyectoFinal.Back.controller;

import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    public IUsuarioService userService;
    @Autowired
    private JwtUtil jwtUtil; // ESTA ES LA CLASE PARA GENERAR TOKENS DE SESIÓN


    /*
    * Esta funcioncita se encarga de verificar la validez del token.
    * Cada vez que se haga una petición, deben validarla usando en el atributo del payload '@RequestHeader("Authorization") String token'.
    * Después simplemente lo comparan con un "if(!validarToken(token))".Si no es válido, retornan un error 401.
    */
    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token); // Hasta ahora no la usé porque aún solo trabajamos con login y register (Los cuales no necesitan sesión obviamente).
        return usuarioId != null;
    }



    @PostMapping("/nuevo/usuario")
    public void agregarUsuario(@RequestBody Usuario user){
        userService.agregarUsuario(user);
    };

    @GetMapping("/ver/usuario")
    @ResponseBody
    public List<Usuario> verUsuario(){
        return userService.verUsuario();
    };

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody Usuario user) {
        boolean registroExitoso = userService.registrarUsuario(user);
        if (!registroExitoso) {
            return ResponseEntity.status(400).body("Error al registrar el usuario");
        }
        return ResponseEntity.ok("Registro exitoso");
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody Usuario user) {
        boolean inicioExitoso = userService.iniciarSesion(user);
        if (!inicioExitoso) {
            return ResponseEntity.status(401).body("Error al iniciar sesión");
        }
        String token = jwtUtil.create(String.valueOf(user.getId()),user.getCorreo(),null); //Acá creamos el token
        return ResponseEntity.ok(token);
    }

}
