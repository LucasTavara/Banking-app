package net.javaguides.backing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackingAppApplication.class, args);
		System.out.println("Pronto!");
	}

}
