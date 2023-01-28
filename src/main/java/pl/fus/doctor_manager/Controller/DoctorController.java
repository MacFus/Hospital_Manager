package pl.fus.doctor_manager.Controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.fus.doctor_manager.DTO.DoctorDto;
import pl.fus.doctor_manager.Entity.Doctor;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Service.DoctorService;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/doctor",consumes = MediaType.APPLICATION_JSON_VALUE)
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
        }catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatusCode.valueOf(404));
        }
    }


}
