package pl.fus.doctor_manager.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.stereotype.Service;
import pl.fus.doctor_manager.DTO.HospitalDto;
import pl.fus.doctor_manager.DtoMapper.HospitalMapper;
import pl.fus.doctor_manager.Entity.Address;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Repository.AddressRepo;
import pl.fus.doctor_manager.Repository.DoctorRepo;
import pl.fus.doctor_manager.Repository.HospitalRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class HospitalService {
    private final HospitalRepo hospitalRepo;
    private final HospitalMapper hospitalMapper;
    private final AddressRepo addressRepo;
    private final ObjectMapper objectMapper;

    public HospitalService(HospitalRepo hospitalRepo, HospitalMapper hospitalMapper, AddressRepo addressRepo, DoctorRepo doctorRepo, ObjectMapper objectMapper) {
        this.hospitalRepo = hospitalRepo;
        this.hospitalMapper = hospitalMapper;
        this.addressRepo = addressRepo;
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
        Hospital hospital = hospitalRepo.findById(id).orElseThrow();
        Address address = hospital.getAddress();
        dto.getAddress().setId(address.getId());
        addressRepo.save(dto.getAddress());
        dto.setId(id);
        Hospital hospToUpdate = hospitalMapper.map(dto);
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
        if(!hospitalRepo.existsById(id))
            throw new NoSuchElementException();
        try {
            HospitalDto hospitalDto = getHospitalById(id).orElseThrow();
            hospitalDto.setId(id);
            HospitalDto hospitalPatched = applyPatch(hospitalDto, patch);
            Hospital hospital = hospitalMapper.map(hospitalPatched);
            hospitalRepo.save(hospital);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        }
    }

    private HospitalDto applyPatch(HospitalDto hospital, String patch) throws JsonProcessingException, JsonPatchException {
        JsonNode node = objectMapper.convertValue(hospital, JsonNode.class);
        JsonNode patchNode = objectMapper.readTree(patch);
        JsonMergePatch mergePatch = JsonMergePatch.fromJson(patchNode);
        node = mergePatch.apply(node);
        return objectMapper.treeToValue(node, hospital.getClass());
    }
}
