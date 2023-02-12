package pl.fus.doctor_manager.Doctor;

import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.Hospital.HospitalRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DoctorMapper doctorMapper;


    public DoctorService(DoctorRepo doctorRepo, HospitalRepo hospitalRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.hospitalRepo = hospitalRepo;
        this.doctorMapper = doctorMapper;
    }

    public DoctorDto saveDoctor(DoctorDto doctor) {
        if (!hospitalRepo.existsById(doctor.getHospitalId())) {
            throw new NoSuchElementException();
        }
        Doctor doc = doctorMapper.map(doctor);
        doc.setAddDate(LocalDateTime.now());
        doc.setHospital(hospitalRepo.findById(doctor.getHospitalId())
                .orElseThrow(NoSuchElementException::new));
        DoctorDto savedDoctor = doctorMapper.map(doctorRepo.save(doc));
        return savedDoctor;
    }

    public List<DoctorDto> getAll() {
        List<Doctor> all = doctorRepo.findAll();
        return all.stream().map(doctorMapper::map).toList();
    }

    public Optional<DoctorDto> getDoctorById(Long id) throws NoSuchElementException {
        return doctorRepo.findById(id).map(doctorMapper::map);
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepo.existsById(id)) {
            throw new NoSuchElementException();
        }
        doctorRepo.deleteById(id);
    }

    public void updateDoctor(Long id, DoctorDto dto) throws NoSuchElementException {
        if (!doctorRepo.existsById(id))
            throw new NoSuchElementException();
        Doctor obj = doctorRepo.findById(id).orElseThrow();
        Doctor doctor = doctorMapper.map(dto);
        doctor.setId(id);
        doctor.setAddDate(obj.getAddDate());
        doctor.getAddress()
                .setId(obj.getAddress().getId());
        doctor.setHospital(hospitalRepo.findById(dto.getHospitalId()).orElseThrow());
        doctorRepo.save(doctor);
    }
}
