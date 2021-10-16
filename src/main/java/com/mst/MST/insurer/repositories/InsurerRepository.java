package com.mst.MST.insurer.repositories;

import com.mst.MST.insurer.entities.Insurer;
import org.springframework.data.repository.CrudRepository;

public interface InsurerRepository extends CrudRepository<Insurer, Long> {
    Insurer findByName(String name);
    Long deleteByName(String name);
}
