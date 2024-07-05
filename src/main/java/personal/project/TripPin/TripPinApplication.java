package personal.project.TripPin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TripPinApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripPinApplication.class, args);
	}

}
