package tech.yangxm.sims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SimsApplication{

	public static void main(String[] args) {
		SpringApplication.run(SimsApplication.class, args);
	}

}
