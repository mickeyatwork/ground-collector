package com.groundcollector.admin.repository;

import com.groundcollector.model.Teams;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Teams, Integer> {
    List<Teams> findTeamsByName (String str);

    int findTeamsById (int teamId);

    boolean existsByName (String name);

}