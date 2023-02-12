package pl.fus.doctor_manager.Hospital;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.fus.doctor_manager.Doctor.DoctorDto;

import java.net.URI;
import java.time.ZonedDateTime;
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
        HospitalDto hospital = hospitalService.updateHospital(id, dto).orElseThrow();
        HttpHeaders headers = new HttpHeaders();
        System.out.println(ZonedDateTime.now());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(hospital, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteHospital(@PathVariable Long id) throws NoSuchElementException {
        try {
            hospitalService.deleteHospital(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updatePartly(@PathVariable Long id, @RequestBody String patch) {
        try {
            hospitalService.updatePartly(id, patch);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatusCode.valueOf(400));

        }
    }
    @GetMapping("/{id}/doctors")
    ResponseEntity<List<DoctorDto>> getDoctorsFromHospital(@PathVariable Long id){
        List<DoctorDto> doctors = hospitalService.getDoctorsByHospitalId(id);
        return ResponseEntity.ok(doctors);
    }


}
