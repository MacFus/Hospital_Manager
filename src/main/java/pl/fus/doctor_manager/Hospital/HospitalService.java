package pl.fus.doctor_manager.Hospital;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.Doctor.DoctorDto;
import pl.fus.doctor_manager.Address.AddressMapper;
import pl.fus.doctor_manager.Doctor.DoctorMapper;
import pl.fus.doctor_manager.Doctor.Address;
import pl.fus.doctor_manager.Doctor.Doctor;
import pl.fus.doctor_manager.Address.AddressRepo;
import pl.fus.doctor_manager.Doctor.DoctorRepo;

import java.util.*;


@Service
public class HospitalService {
    private final HospitalRepo hospitalRepo;
    private final DoctorRepo doctorRepo;
    private final AddressRepo addressRepo;
    private final HospitalMapper hospitalMapper;
    private final DoctorMapper doctorMapper;
    private final AddressMapper addressMapper;
    private final ObjectMapper objectMapper;

    public HospitalService(HospitalRepo hospitalRepo, HospitalMapper hospitalMapper, AddressRepo addressRepo, DoctorRepo doctorRepo, DoctorMapper doctorMapper, AddressMapper addressMapper, ObjectMapper objectMapper) {
        this.hospitalRepo = hospitalRepo;
        this.hospitalMapper = hospitalMapper;
        this.addressRepo = addressRepo;
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
        this.addressMapper = addressMapper;
        this.objectMapper = objectMapper;
    }

    public HospitalDto save(HospitalDto hospitalDto) {
        Hospital hospital = hospitalMapper.map(hospitalDto);
        Hospital savedHospital = hospitalRepo.save(hospital);
        return hospitalMapper.map(savedHospital);
    }

    public List<HospitalDto> getAll() {
        return hospitalRepo.findAll().stream().map(hospitalMapper::map).toList();
    }

    public Optional<HospitalDto> getHospitalById(Long id) {
        return hospitalRepo.findById(id).map(hospitalMapper::map);
    }

    public Optional<HospitalDto> updateHospital(Long id, HospitalDto dto) {
        if (!hospitalRepo.existsById(id)) {
            return Optional.empty();
        }
        Address address = hospitalRepo.findById(id).get().getAddress();

        Hospital hospitalToUpdate = hospitalMapper.map(dto);
        hospitalToUpdate.setId(id);
        hospitalToUpdate.getAddress().setId(address.getId());
        Hospital savedHospital = hospitalRepo.save(hospitalToUpdate);
        return Optional.of(hospitalMapper.map(savedHospital));
    }


    public void deleteHospital(Long id) throws NoSuchElementException {
        if (!hospitalRepo.existsById(id)) {
            throw new NoSuchElementException();
        }
        hospitalRepo.deleteById(id);
    }

    public void updatePartly(Long id, String patch) {
        if (!hospitalRepo.existsById(id))
            throw new NoSuchElementException();
        try {
            HospitalDto hospitalDto = getHospitalById(id).orElseThrow();
            HospitalDto hospitalPatched = applyPatch(hospitalDto, patch);
            Hospital hospital = hospitalMapper.map(hospitalPatched);
            hospital.setId(id);
            hospital.getAddress().setId(hospitalRepo.findById(id).get().getAddress().getId());
            hospitalRepo.save(hospital);
        } catch (JsonProcessingException | JsonPatchException e) {
            throw new InputMismatchException();
        }

    }

    private HospitalDto applyPatch(HospitalDto hospital, String patch) throws JsonProcessingException, JsonPatchException {
        JsonNode node = objectMapper.convertValue(hospital, JsonNode.class);
        JsonNode patchNode = objectMapper.readTree(patch);
        JsonMergePatch mergePatch = JsonMergePatch.fromJson(patchNode);
        node = mergePatch.apply(node);
        return objectMapper.treeToValue(node, hospital.getClass());
    }

    public List<DoctorDto> getDoctorsByHospitalId(Long id) {
        List<Doctor> doctorList = doctorRepo.findAllByHospitalId(id);
        List<DoctorDto> doctorDtoList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            doctorDtoList.add(doctorMapper.map(doctor));
        }
        return doctorDtoList;
    }
}