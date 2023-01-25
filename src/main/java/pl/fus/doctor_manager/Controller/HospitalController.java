package pl.fus.doctor_manager.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fus.doctor_manager.Entity.Hospital;
import pl.fus.doctor_manager.Service.HospitalService;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    private final HospitalService hospitalService;
    private final ObjectMapper objectMapper;

    public HospitalController(HospitalService hospitalService, ObjectMapper objectMapper) {
        this.hospitalService = hospitalService;
        this.objectMapper = objectMapper;
    }


    @PostMapping
    ResponseEntity<Hospital> save(@RequestBody Hospital hospital) {
//        objectMapper.treeToValue(Hospital);
//        System.out.println(json.get("address").get("street"));
        Hospital savedHospital = hospitalService.save(hospital);
        return ResponseEntity.ok().build();
    }

//    @PostMapping
//    ResponseEntity<Hospital> save(@RequestBody ObjectNode json) {
////        System.out.println(json.get("address").get("street"));
////        Hospital savedHospital = hospitalService.save(hospital);
//        return ResponseEntity.ok().build();
//    }
}
