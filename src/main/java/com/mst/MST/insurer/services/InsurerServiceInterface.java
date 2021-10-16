package com.mst.MST.insurer.services;

import com.mst.MST.insurer.entities.Insurer;

import java.util.List;

public interface InsurerServiceInterface {
    List<Insurer> getAll();
    Long getIdByName(String name);
    Insurer getInsurerByName(String name);
    Insurer addInsurer(Insurer insurer);
    Long deleteInsurer(String name) ;
}
