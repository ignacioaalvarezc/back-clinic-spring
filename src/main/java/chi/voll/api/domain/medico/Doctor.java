package chi.voll.api.domain.medico;
// IMPORTS
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import chi.voll.api.domain.direccion.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Table(name = "doctor")
@Entity(name = "Doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String dni;
    private boolean status;
    @Enumerated(EnumType.STRING)
    private chi.voll.api.domain.medico.Speciality Speciality;
    @Embedded
    private Address address;
    
    public Doctor(DoctorsRecordDetails doctorsRecordDetails) {
    	this.status = true;
    	this.name = doctorsRecordDetails.nombre();
    	this.email = doctorsRecordDetails.email();
    	this.phoneNumber = doctorsRecordDetails.telefono();
    	this.dni = doctorsRecordDetails.documento();
    	this.Speciality = doctorsRecordDetails.speciality();
    	this.address = new Address(doctorsRecordDetails.direccion());
    	
    }
    
    public void updateData(DoctorUpdateData doctorUpdateData) {
    	if (doctorUpdateData.nombre() != null) {
    		this.name = doctorUpdateData.nombre();
    	}
    	if (doctorUpdateData.documento() != null) {
    		this.dni = doctorUpdateData.documento();
    	}
    	if (doctorUpdateData.direccion() != null) {
        	this.address = address.actualizarDireccion(doctorUpdateData.direccion());
    	}    			
    }
    
    public void disableDoctor() {
    	this.status = false;
    }

}
