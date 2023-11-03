package chi.voll.api.domain.consulta.validations;

import chi.voll.api.domain.consulta.DatosAgendarConsulta;

public interface ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos);
}
