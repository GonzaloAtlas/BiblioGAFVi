package com.biblioGAFVi.BiblioGAFVi;

import com.biblioGAFVi.BiblioGAFVi.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BiblioGafViApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BiblioGafViApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
	}
}
