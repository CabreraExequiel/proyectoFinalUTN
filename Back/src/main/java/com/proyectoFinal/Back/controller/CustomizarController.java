package com.proyectoFinal.Back.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoFinal.Back.EntidadesPersonalizadas.DataPerfil;
import com.proyectoFinal.Back.Token.JwtUtil;
import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.service.ICustomizarService;

import jakarta.servlet.http.HttpSession;


@RestController
public class CustomizarController {

    @Autowired
    private ICustomizarService customizarService;
    @Autowired
    private JwtUtil jwtUtil;


    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @GetMapping("/custom/deporte/salas")
    public ResponseEntity<List<SalaDeporte>> customizar(@RequestHeader("Authorization") String token,HttpSession session) {
        if (!validarToken(token)) {
            return ResponseEntity.status(401).build(); 
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
        return ResponseEntity.ok(customizarService.verSalasDeporte(id_usuario));
    }

    @GetMapping("/custom/usuario/info")
    public ResponseEntity<DataPerfil> customizarUsuario(@RequestHeader("Authorization") String token, HttpSession session) {
        if (!validarToken(token)) {
            return ResponseEntity.status(401).build(); 
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
        return ResponseEntity.ok(customizarService.verUsuario(id_usuario));
    }

    @PutMapping(value = "/custom/usuario/editar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataPerfil> actualizarUsuario(@RequestHeader("Authorization") String token, HttpSession session, @RequestBody DataPerfil dataPerfil) {
        if (!validarToken(token)) {
            return ResponseEntity.status(401).build(); 
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
        DataPerfil perfilEditado = customizarService.editarUsuario(id_usuario, dataPerfil);
        if (perfilEditado == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 si el usuario no existe
        }
        return ResponseEntity.ok(perfilEditado);
    }

    @PutMapping(value = "/custom/usuario/editar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> actualizarUsuarioMultipart(
        @RequestHeader("Authorization") String token,
        HttpSession session,
        @RequestPart(required = false) MultipartFile imagen_perfil,
        @RequestPart(required = false) MultipartFile imagen_portada
    ) {
        if (!validarToken(token)) {
            return ResponseEntity.status(401).build(); 
        }

        String ruta_imagen = null;

        try {
            // Obtener ID del usuario desde la sesión
            Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
            
            MultipartFile archivo = (imagen_perfil != null) ? imagen_perfil : imagen_portada;

            if (archivo != null && !archivo.isEmpty()) {
                String tipo = (imagen_perfil != null) ? "foto_perfil" : "foto_portada";
                String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename());
                if (extension == null) extension = "jpg";

                // Nombre del archivo basado en tipo e ID
                String nombreArchivo = tipo + "_" + id_usuario + "." + extension;

                // RUTA ABSOLUTA
                Path rutaCarpeta = Paths.get("C:/miApp/uploads");
                if (!Files.exists(rutaCarpeta)) {
                    Files.createDirectories(rutaCarpeta);
                }

                Path rutaCompleta = rutaCarpeta.resolve(nombreArchivo);

                // Eliminar archivo existente si existe
                if (Files.exists(rutaCompleta)) {
                    Files.delete(rutaCompleta);
                }

                // Guardar nuevo archivo
                archivo.transferTo(rutaCompleta.toFile());

                // Guardar la ruta como string
                ruta_imagen = rutaCompleta.toString();
                customizarService.subirFoto(id_usuario, ruta_imagen, tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
        
        return ResponseEntity.ok(ruta_imagen);
    }


    @GetMapping("/custom/usuario/foto_perfil")
    public ResponseEntity<String> obtenerFotoUsuario(@RequestHeader("Authorization") String token, HttpSession session) {
        if (!validarToken(token)) {
            return ResponseEntity.status(401).build(); 
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
        String rutaFotoPerfil = customizarService.verFotoPerfil(id_usuario);
        if (rutaFotoPerfil == null || rutaFotoPerfil.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra la foto
        }
        return ResponseEntity.ok(rutaFotoPerfil);
    }

    @GetMapping("/custom/usuario/foto_portada")
    public ResponseEntity<String> obtenerFotoPortada(@RequestHeader("Authorization") String token, HttpSession session) {
        if (!validarToken(token)) {
            return ResponseEntity.status(401).build(); 
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
        String rutaFotoPortada = customizarService.verFotoPortada(id_usuario);
        if (rutaFotoPortada == null || rutaFotoPortada.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra la foto
        }
        return ResponseEntity.ok(rutaFotoPortada);
    }



    
    
}
