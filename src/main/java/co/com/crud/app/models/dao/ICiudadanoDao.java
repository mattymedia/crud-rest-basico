package co.com.crud.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.crud.app.models.entity.Ciudadano;

public interface ICiudadanoDao extends JpaRepository<Ciudadano, Integer>{

}
