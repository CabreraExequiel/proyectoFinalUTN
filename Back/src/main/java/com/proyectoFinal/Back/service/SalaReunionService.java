package com.proyectoFinal.Back.service;

import com.proyectoFinal.Back.entity.SalaReunion;
import com.proyectoFinal.Back.repoeitory.SalaReunionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaReunionService implements ISalaReunionService{

    @Autowired
    private SalaReunionRepositorio salaREpo;

    @Override
    public List<SalaReunion> findAll() {
        return salaREpo.findAll();
    }

    @Override
    public Optional<SalaReunion> findById(Long id) {
        return salaREpo.findById(id);
    }

    @Override
    public SalaReunion save(SalaReunion sala) {
        return salaREpo.save(sala);
    }

    @Override
    public void deleteById(Long id) {
        salaREpo.deleteById(id);
    }


}
