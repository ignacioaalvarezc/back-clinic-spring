package com.voll.api.domain.models;

// IMPORTS.
import com.voll.api.domain.dto.address.AddressData;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * THIS MODEL CLASS REPRESENTS THE ADDRESS INFORMATION EMBEDDED WITHIN OTHER ENTITIES.
 * An embeddable class used to store address details.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2013-11-07
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String street;
    private String number;
    private String complement;
    private String district;
    private String city;

	/**
	 * CONSTRUCTS AN ADDRESS OBJECT FROM THE PROVIDED ADDRESS DATA DTO.
	 *
	 * @param address The AddressData object containing address details.
	 */
	public Address(AddressData address) {
    	this.street = address.street();
    	this.number = address.number();
    	this.district = address.district();
    	this.complement = address.complement();
    	this.city = address.city();
	}

	/**
	 * UPDATES THE ADDRESS INFORMATION BASED ON THE PROVIDED ADDRESS DATA DTO.
	 *
	 * @param address The AddressData object containing updated address details.
	 * @return The updated Address object.
	 */
	public Address updateAddress(AddressData address) {
    	this.street = address.street();
    	this.number = address.number();
    	this.district = address.district();
    	this.complement = address.complement();
    	this.city = address.city();
    	return this;
    }
}
