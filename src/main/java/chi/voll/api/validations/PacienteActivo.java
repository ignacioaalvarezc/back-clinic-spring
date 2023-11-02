package chi.voll.api.validations;

import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import chi.voll.api.domain.paciente.PacienteRepository;

public class PacienteActivo {

    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.idPaciente()==null) {
            return;
        }
    }
}
