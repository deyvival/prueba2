package com.ejercicio.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.dto.CodigoHum;

@Repository
public interface CodigoHumDAO extends CrudRepository<CodigoHum, Long> {
	public abstract ArrayList<CodigoHum> findByXmen (String xmen);

}

