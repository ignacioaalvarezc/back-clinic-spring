package chi.voll.api.domain.consulta;

import chi.voll.api.domain.medico.Medico;
import chi.voll.api.domain.medico.MedicoRepository;
import chi.voll.api.domain.paciente.PacienteRepository;
import chi.voll.api.infra.errores.ValidacionDeIntegridad;
import chi.voll.api.domain.consulta.validations.ValidadorDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos) {
        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este id para el paciente no fue encontrado");
        }
        if(datos.idMedico()!=null && medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionDeIntegridad("Este id para el medico no fue encontrado");
        }

        validadores.forEach(v->v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);
        if(medico==null) {
            throw new ValidacionDeIntegridad(("No existen medicos disponibles para este horario y especialidad."));
        }
        var consulta = new Consulta(medico,paciente,datos.fecha());
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }
    /*
    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionDeIntegridad("Id de la consulta informado no existe.");
        }
        validadoresCancelamiento.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }*/

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico()!=null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionDeIntegridad("Debe seleccionarse una especialidad para el meédico.");
        }
        return medicoRepository.seleccionMedicoConEspecialidadFecha(datos.especialidad(), datos.fecha());
    }
}
