package com.groundcollector.admin.repository;

import com.groundcollector.model.Grounds;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroundRepository extends CrudRepository<Grounds, Integer> {
    List<Grounds> findGroundByName(String str);

    Grounds findOriginalGroundByName(String str);

    boolean existsByName(String name);

}
