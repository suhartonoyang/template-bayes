package com.project.templatebayes.repo;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.templatebayes.model.MstType;

@Repository
public interface MstTypeRepository extends CrudRepository<MstType, Integer>{

}
