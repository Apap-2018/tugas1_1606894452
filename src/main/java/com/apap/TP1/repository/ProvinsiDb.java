package com.apap.TP1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.TP1.model.ProvinsiModel;

@Repository
public interface ProvinsiDb extends JpaRepository<ProvinsiModel, Long>{

}
