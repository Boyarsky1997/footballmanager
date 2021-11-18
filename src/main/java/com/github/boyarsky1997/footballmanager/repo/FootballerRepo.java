package com.github.boyarsky1997.footballmanager.repo;

import com.github.boyarsky1997.footballmanager.models.Footballer;
import org.springframework.data.repository.CrudRepository;

public interface FootballerRepo extends CrudRepository<Footballer, Integer> {

}
