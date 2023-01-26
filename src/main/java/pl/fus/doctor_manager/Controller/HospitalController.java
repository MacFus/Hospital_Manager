package pl.fus.doctor_manager.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import java.util.NoSuchElementException;

@RestController
//,method = RequestMethod.GET ,consumes = MediaType.APPLICATION_JSON_VALUE
@RequestMapping(value = "/hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/all")
    ResponseEntity<List<HospitalDto>> getAllHospitals() {
        List<HospitalDto> hospitalDtoList = hospitalService.getAll();
        return ResponseEntity.ok(hospitalDtoList);
    }

    @GetMapping("/{id}")
    ResponseEntity<HospitalDto> getHospitalById(@PathVariable Long id) {
        HospitalDto hospital = hospitalService.getHospitalById(id)
                .orElseThrow(NoSuchElementException::new);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok(hospital);
    }

    @PostMapping
    ResponseEntity<HospitalDto> save(@RequestBody HospitalDto hospitalDto) {
        HospitalDto savedHospital = hospitalService.save(hospitalDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedHospital.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedHospital);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateHospital(@PathVariable Long id, @RequestBody HospitalDto dto) {
//        ResponseEntity<Object> responseEntity = hospitalService.updateHospital(id, dto)
//                .map(r -> ResponseEntity.noContent().build())
//                .orElse(ResponseEntity.notFound().build());
        HospitalDto hospital = hospitalService.updateHospital(id, dto).orElseThrow();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(hospital, HttpStatus.NO_CONTENT);
    }

}
