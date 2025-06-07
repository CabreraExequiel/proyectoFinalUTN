package com.proyectoFinal.Back.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.proyectoFinal.Back.repoeitory.CustomRepository;
import java.util.List;
import com.proyectoFinal.Back.entity.SalaDeporte;


@Service
public class CustomizarService implements ICustomizarService {
    @Autowired
    private CustomRepository customRepository;

    @Override
    public List<SalaDeporte> verSalasDeporte(Long id_usuario) {
        return customRepository.findAll();
    }
}
