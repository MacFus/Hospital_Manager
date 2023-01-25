package pl.fus.doctor_manager.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Service.HospitalService;

import java.net.URI;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
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
