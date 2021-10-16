package com.mst.MST.insurer.services;

import com.mst.MST.insurer.entities.Insurer;
import com.mst.MST.insurer.repositories.InsurerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class InsurerService implements InsurerServiceInterface{
    private final InsurerRepository insurerRepository;

    public InsurerService(InsurerRepository insurerRepository) {
        this.insurerRepository = insurerRepository;
    }

    public Long getIdByName(String name){
        Insurer insurer = insurerRepository.findByName(name);
        return insurer != null ? insurer.getId() : null;
    }

    public Insurer getInsurerByName(String name){
        Insurer insurer = insurerRepository.findByName(name);
        return insurer != null ? insurer : null;
    }

    public Insurer addInsurer(Insurer insurer) {
        Long id = getIdByName(insurer.getName());
        if (id == null) {
            insurer = insurerRepository.save(insurer);
            return insurer;
        }
        return null;
    }

    public List<Insurer> getAll() {
        List<Insurer> list = new LinkedList<>();
        insurerRepository.findAll().forEach(list::add);
        return list;
    }

    @Transactional
    public Long deleteInsurer(String name) {
        Long idInsurer = insurerRepository.deleteByName(name);
        return idInsurer;
    }
}
