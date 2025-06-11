package com.proyectoFinal.Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Back.EntidadesPersonalizadas.DataPerfil;
import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.repoeitory.CustomRepository;
import com.proyectoFinal.Back.repoeitory.UsuarioRepository;


@Service
public class CustomizarService implements ICustomizarService {
    @Autowired
    private CustomRepository customRepository;
    @Autowired
    private UsuarioRepository customUsuarioRepository;

    @Override
    public List<SalaDeporte> verSalasDeporte(Long id_usuario) {
        return customRepository.findAll();
    }

    @Override
    public DataPerfil verUsuario(Long id_usuario) {
        System.out.println("ID del usuario: " + id_usuario);
        Usuario usuario = customUsuarioRepository.findById(id_usuario).orElse(null);
        if (usuario != null) {
            return new DataPerfil(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getDescripcion_usuario(),
                usuario.getImagen_perfil(),
                usuario.getImagen_portada()
            );
        }
       return null;
    }

    @Override
    public DataPerfil editarUsuario(Long id_usuario, DataPerfil dataPerfil) {
        Usuario usuario = customUsuarioRepository.findById(id_usuario).orElse(null);
        if (usuario != null) {
            if (dataPerfil.getImagen_perfil() != null) {
                usuario.setImagen_perfil(dataPerfil.getImagen_perfil());
            }
            if (dataPerfil.getImagen_portada() != null) {
                usuario.setImagen_portada(dataPerfil.getImagen_portada());
            }
            if (dataPerfil.getDescripcion_usuario() != null) {
                usuario.setDescripcion_usuario(dataPerfil.getDescripcion_usuario());
            }
            customUsuarioRepository.save(usuario);
            return new DataPerfil(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getDescripcion_usuario(),
                usuario.getImagen_perfil(),
                usuario.getImagen_portada()
            );
        }
        return null;
    }

    @Override
    public String subirFoto(Long id_usuario, String rutaFoto, String tipoFoto) {
        Usuario usuario = customUsuarioRepository.findById(id_usuario).orElse(null);
        if (usuario != null) {
            if ("foto_perfil".equals(tipoFoto)) {
                usuario.setImagen_perfil(rutaFoto);
            } else if ("foto_portada".equals(tipoFoto)) {
                usuario.setImagen_portada(rutaFoto);
            }
            customUsuarioRepository.save(usuario);
            return rutaFoto;
        }
        return null;
    }

    @Override
    public String verFotoPerfil(Long id_usuario) {
        Usuario usuario = customUsuarioRepository.findById(id_usuario).orElse(null);
        if (usuario != null) {
            return usuario.getImagen_perfil();
        }
        return null;
    }

    @Override
    public String verFotoPortada(Long id_usuario) {
        Usuario usuario = customUsuarioRepository.findById(id_usuario).orElse(null);
        if (usuario != null) {
            return usuario.getImagen_portada();
        }
        return null;
    }

    

}
