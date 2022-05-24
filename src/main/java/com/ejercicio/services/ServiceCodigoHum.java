package com.ejercicio.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.dto.CodigoHum;
import com.ejercicio.dao.CodigoHumDAO;

@Service
public class ServiceCodigoHum {

	@Autowired
	private CodigoHumDAO codigoHumDao;
	
	public ArrayList<CodigoHum> obtenerCodigos(){
		return (ArrayList<CodigoHum>) codigoHumDao.findAll();
	}
	
	public CodigoHum GuardarCodigo(ArrayList<CodigoHum> lstCodigoHum){
		return (CodigoHum) codigoHumDao.saveAll(lstCodigoHum);
	}
	
	public Optional<CodigoHum> obtenerPorId(Long id){
		return codigoHumDao.findById(id);
	}
	
	public ArrayList<CodigoHum> obtenerPorXmen(String xmen){
		return codigoHumDao.findByXmen(xmen);
	}

}
