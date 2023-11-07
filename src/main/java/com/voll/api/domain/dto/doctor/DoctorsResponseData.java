package com.voll.api.domain.dto.doctor;

import com.voll.api.domain.dto.address.AddressData;

public record DoctorsResponseData(
		Long id, 
		String nombre, 
		String email, 
		String telefono, 
		String documento, 
		AddressData direccion) {
	
}
