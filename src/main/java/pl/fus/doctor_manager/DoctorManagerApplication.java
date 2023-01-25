package pl.fus.doctor_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DoctorManagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DoctorManagerApplication.class, args);
	}

}
