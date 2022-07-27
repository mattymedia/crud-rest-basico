package co.com.crud.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.crud.app.models.dao.ICiudadanoDao;
import co.com.crud.app.models.entity.Ciudadano;

@Service
public class ImplCiudadanoService implements ICiudadanoService {

	@Autowired
	private ICiudadanoDao ciudadanoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ciudadano> listar() {
		return ciudadanoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ciudadano buscarCiudadano(Integer id) {
		return ciudadanoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Ciudadano crear(Ciudadano ciudadano) {
		return ciudadanoDao.save(ciudadano);
	}

	@Override
	@Transactional
	public void eliminar(Integer id) {
		ciudadanoDao.deleteById(id);
	}
	

}
