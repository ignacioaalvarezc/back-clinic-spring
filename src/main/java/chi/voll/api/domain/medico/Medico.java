package chi.voll.api.domain.medico;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import chi.voll.api.domain.direccion.Direccion;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Table(name = "medico")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;
    
    public Medico(DatosRegistroMedico datosRegistroMedico) {
    	this.activo = true;
    	this.nombre = datosRegistroMedico.nombre();
    	this.email = datosRegistroMedico.email();
    	this.telefono = datosRegistroMedico.telefono();
    	this.documento = datosRegistroMedico.documento();
    	this.especialidad = datosRegistroMedico.especialidad();
    	this.direccion = new Direccion(datosRegistroMedico.direccion());
    	
    }
    
    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
    	if (datosActualizarMedico.nombre() != null) {
    		this.nombre = datosActualizarMedico.nombre();
    	}
    	if (datosActualizarMedico.documento() != null) {
    		this.documento = datosActualizarMedico.documento();
    	}
    	if (datosActualizarMedico.direccion() != null) {
        	this.direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());
    	}    			
    }
    
    public void desactivarMedico() {
    	this.activo = false;
    }

}
