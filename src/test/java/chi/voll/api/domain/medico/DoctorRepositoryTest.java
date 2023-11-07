package chi.voll.api.domain.medico;

import chi.voll.api.domain.attention.Consulta;
import chi.voll.api.domain.direccion.AddressData;
import chi.voll.api.domain.patient.Patient;
import chi.voll.api.domain.patient.PatientRecordData;
import chi.voll.api.repository.DoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deberia retornar nulo cuando el médico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConSpecialityEnFechaEscenario1() {
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registrarMedico("Jose", "j@gmail.com", "123456", Speciality.CARDIOLOGIA);
        var patient = registrarPaciente("Antonio", "a@gmail.com", "654321");
        registrarConsulta(medico, patient, proximoLunes10H);

        var medicoLibre = doctorRepository.selectDoctorWithSpecialityDate(Speciality.CARDIOLOGIA,proximoLunes10H);

        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registrarConsulta(Doctor doctor, Patient patient, LocalDateTime fecha)  {
        em.persist(new Consulta(null, doctor, patient, fecha, null));
    }

    private Doctor registrarMedico(String nombre, String email, String documento, Speciality speciality) {
        var medico = new Doctor(datosMedico(nombre, email, documento, speciality));
        em.persist(medico);
        return medico;
    }

    private Patient registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Patient(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DoctorsRecordDetails datosMedico(String nombre, String email, String documento, Speciality speciality) {
        return new DoctorsRecordDetails(
                nombre,
                email,
                "61999999999",
                documento,
                speciality,
                datosDireccion()
        );
    }

    private PatientRecordData datosPaciente(String nombre, String email, String documento) {
        return new PatientRecordData(
                nombre,
                email,
                "61999999999",
                documento,
                datosDireccion()
        );
    }

    private AddressData datosDireccion() {
        return new AddressData(
                " loca",
                "azul",
                "acapulpo",
                "321",
                "12"
        );
    }
}
