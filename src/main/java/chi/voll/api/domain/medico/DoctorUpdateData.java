package chi.voll.api.domain.medico;

import chi.voll.api.domain.direccion.AddressData;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(@NotNull Long id, String nombre, String documento, AddressData direccion) {

}
