package pl.fus.doctor_manager.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.fus.doctor_manager.DTO.HospitalDto;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Service.HospitalService;

import javax.print.attribute.standard.Media;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/hospital",consumes = MediaType.APPLICATION_JSON_VALUE)
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/all")
    ResponseEntity<List<HospitalDto>> getAllHospitals() {
        List<HospitalDto> hospitalDtoList = hospitalService.getAll();
        return  ResponseEntity.ok(hospitalDtoList);
    }

    @PostMapping
    ResponseEntity<Hospital> save(@RequestBody Hospital hospital) {
        Hospital savedHospital = hospitalService.save(hospital);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedHospital.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedHospital);
    }

}
