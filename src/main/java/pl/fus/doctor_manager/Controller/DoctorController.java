package pl.fus.doctor_manager.Controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.fus.doctor_manager.DTO.DoctorDto;
import pl.fus.doctor_manager.Service.DoctorService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/doctor") //,consumes = MediaType.APPLICATION_JSON_VALUE
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    ResponseEntity<DoctorDto> saveDoctor(@RequestBody DoctorDto doctor) {
        try {
            DoctorDto saveDoctor = doctorService.saveDoctor(doctor);
//            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                    .path("/{id}")
//                    .buildAndExpand(saveDoctor.getId())
//                    .toUri();
            return ResponseEntity.ok(saveDoctor);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    ResponseEntity<List<DoctorDto>> getAllDoctors() {
        List<DoctorDto> doctorDtoList = doctorService.getAll();
        return ResponseEntity.ok(doctorDtoList);
    }

    @GetMapping("/{id}")
    ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        try {
            DoctorDto doctorDto = doctorService.getDoctorById(id).orElseThrow(NoSuchElementException::new);
            return ResponseEntity.ok(doctorDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        try {
            doctorService.deleteDoctor(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto dto) {
        try {
            doctorService.updateDoctor(id, dto);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
