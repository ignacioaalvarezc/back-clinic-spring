package com.voll.api.domain.dto.doctor;

// IMPORTS.
import com.voll.api.domain.dto.address.AddressData;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING SIMPLIFIED DOCTOR INFORMATION FOR API RESPONSES.
 * This DTO class follows Java record syntax, providing a concise representation of data.
 * It includes a specialized constructor to create DTO objects easily.
 * @param id Doctor's ID.
 * @param name Doctor's name.
 * @param email Doctor's email.
 * @param phoneNumber Doctor's phone number.
 * @param dni Doctor's DNI.
 * @param address Doctor's address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record DoctorsResponseData(
		Long id, 
		String name,
		String email,
		String phoneNumber,
		String dni,
		AddressData address) {
	
}
