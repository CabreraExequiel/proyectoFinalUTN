package com.proyectoFinal.Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.repoeitory.CustomRepository;


@Service
public class CustomizarService implements ICustomizarService {
    @Autowired
    private CustomRepository customRepository;

    @Override
    public List<SalaDeporte> verSalasDeporte(Long id_usuario) {
        return customRepository.findAll();
    }

    @Override
    public Usuario verUsuario(Long id_usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verUsuario'");
    }

    

}
