package pl.fus.doctor_manager.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.DoctorDto;
import pl.fus.doctor_manager.DTO.HospitalDto;
import pl.fus.doctor_manager.DtoMapper.AddressMapper;
import pl.fus.doctor_manager.DtoMapper.DoctorMapper;
import pl.fus.doctor_manager.DtoMapper.HospitalMapper;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Entity.Doctor;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Repository.AddressRepo;
import pl.fus.doctor_manager.Repository.DoctorRepo;
import pl.fus.doctor_manager.Repository.HospitalRepo;

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
//        Hospital hospital = hospitalRepo.findById(id).orElseThrow();
//        HospitalDto hospitalDto = hospitalMapper.map(hospital);
//
        return hospitalRepo.findById(id).map(hospitalMapper::map);
    }

    public Optional<HospitalDto> updateHospital(Long id, HospitalDto dto) {
        if (!hospitalRepo.existsById(id)) {
            return Optional.empty();
        }
        Hospital hospital = hospitalRepo.findById(id).orElseThrow();
        Address address = hospital.getAddress();
        dto.getAddress().setId(address.getId());
        addressRepo.save(addressMapper.map(dto.getAddress()));
//        dto.setId(id);
        Hospital hospToUpdate = hospitalMapper.map(dto);
        hospToUpdate.setId(id);
        Hospital savedHospital = hospitalRepo.save(hospToUpdate);
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
//            hospitalDto.setId(id);
            HospitalDto hospitalPatched = applyPatch(hospitalDto, patch);
            Hospital hospital = hospitalMapper.map(hospitalPatched);
            hospital.setId(id);
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
