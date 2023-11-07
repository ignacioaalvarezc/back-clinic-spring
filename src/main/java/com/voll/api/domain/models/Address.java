package com.voll.api.domain.models;

import com.voll.api.domain.dto.address.AddressData;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    
    public Address(AddressData address) {
    	this.street = address.street();
    	this.number = address.number();
    	this.district = address.district();
    	this.complement = address.complement();
    	this.city = address.city();
	}
    
    public Address actualizarDireccion(AddressData direccion) {
    	this.street = direccion.street();
    	this.number = direccion.number();
    	this.district = direccion.district();
    	this.complement = direccion.complement();
    	this.city = direccion.city();
    	return this;
    }
}
