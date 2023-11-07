package chi.voll.api.domain.medico;

import chi.voll.api.domain.direccion.AddressData;

public record DoctorsResponseData(
		Long id, 
		String nombre, 
		String email, 
		String telefono, 
		String documento, 
		AddressData direccion) {
	
}
