package co.com.crud.app.models.service;

import java.util.List;

import co.com.crud.app.models.entity.Ciudadano;

public interface ICiudadanoService {
	public List<Ciudadano> listar();
	
	public Ciudadano buscarCiudadano(Integer id);
	
	public Ciudadano crear(Ciudadano ciudadano);
	
	public void eliminar(Integer id);
}
