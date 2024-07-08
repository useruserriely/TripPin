package personal.project.TripPin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "personal.project.TripPin")
public class TripPinApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripPinApplication.class, args);
	}

}
