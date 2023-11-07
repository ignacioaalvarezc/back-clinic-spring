package chi.voll.api.domain.patient;

import chi.voll.api.domain.direccion.AddressData;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateData(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        AddressData direccion) {
}
