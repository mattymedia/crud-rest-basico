package co.com.crud.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.crud.app.models.entity.Ciudadano;
import co.com.crud.app.models.service.ICiudadanoService;

@RestController
@RequestMapping("/api")
public class CiudadanoController {
	
	@Autowired
	private ICiudadanoService ciudadanoService;
	
	@GetMapping("/listar")
	public List<Ciudadano> listar(){
		return ciudadanoService.listar();
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> buscarCiudadano(@PathVariable Integer id) {
		
		Ciudadano ciudadano = ciudadanoService.buscarCiudadano(id);
		Map<String, Object> mensajes = new HashMap<>();
		
		if(ciudadano == null) {
			mensajes.put("error", "el ciudadano con ID: " + id + " no existe en la bd");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}
		
		mensajes.put("mensaje", "Ciudadano encontrado!! ID: " + ciudadano.getId() + " Nombre: " + ciudadano.getNombre());
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crear(@RequestBody Ciudadano ciudadano) {
		Map<String, Object> mensajes = new HashMap<>();
		Ciudadano creado = ciudadanoService.crear(ciudadano);
		mensajes.put("mensaje", "Ciudadano '" + ciudadano.getNombre() +  "', creado con exito!!");
		mensajes.put("id", creado.getId());
		mensajes.put("nombre", creado.getNombre());
		mensajes.put("telefono", creado.getTelefono());
		mensajes.put("createAt", creado.getCreateAt());
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editar(@RequestBody Ciudadano ciudadano, @PathVariable Integer id){
		Map<String, Object> mensajes = new HashMap<>();
		Ciudadano encontrado = ciudadanoService.buscarCiudadano(id);
		
		if(encontrado == null) {
			mensajes.put("error", "el ciudadano con ID: " + id + " no existe en la bd");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}
		
		encontrado.setNombre(ciudadano.getNombre());
		encontrado.setTelefono(ciudadano.getTelefono());
		ciudadanoService.crear(encontrado);
		
		mensajes.put("mensaje", "el ciudadano con ID:[" + id  + "], ha sido editado!!");

		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);	
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		Map<String, Object> mensajes = new HashMap<>();
		Ciudadano encontrado = ciudadanoService.buscarCiudadano(id);
		
		if(encontrado == null) {
			mensajes.put("error", "el ciudadano con ID: " + id + " no existe en la bd");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}
		
		ciudadanoService.eliminar(id);
		mensajes.put("mensaje", "el usuario con id: " + encontrado.getId() + "ha sido borrado de la bd.");
		
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);	
		
	}
	
}
