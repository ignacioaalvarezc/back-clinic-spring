package chi.voll.api.controller;

import chi.voll.api.domain.direccion.DatosDireccion;
import chi.voll.api.domain.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
    										UriComponentsBuilder uriComponentsBuilder) {
    	Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
    	DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(),
    			medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
    			new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
    					medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
    					medico.getDireccion().getComplemento()));
    	URI url = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
    	return ResponseEntity.created(url).body(datosRespuestaMedico);
    }
    
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> ListadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
//    	return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
    	return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));

    }
    
    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
    	Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
    	medico.actualizarDatos(datosActualizarMedico);
    	return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(),
    			medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
    			new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
    					medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
    					medico.getDireccion().getComplemento())));
    }
    
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
    	Medico medico = medicoRepository.getReferenceById(id);
    	medico.desactivarMedico();
    	return ResponseEntity.noContent().build();
    }
    
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id) {
//    	Medico medico = medicoRepository.getReferenceById(id);
//    	medicoRepository.delete(medico);
//    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornarDatosMedicos(@PathVariable Long id) {
    	Medico medico = medicoRepository.getReferenceById(id);
    	var datosMedicos = new DatosRespuestaMedico(medico.getId(), medico.getNombre(),
    			medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
    			new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
    					medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
    					medico.getDireccion().getComplemento()));
    	return ResponseEntity.ok(datosMedicos);
    }
}
