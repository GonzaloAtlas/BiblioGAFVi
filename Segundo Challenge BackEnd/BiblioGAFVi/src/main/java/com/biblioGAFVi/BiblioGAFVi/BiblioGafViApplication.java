package com.biblioGAFVi.BiblioGAFVi;

import com.biblioGAFVi.BiblioGAFVi.Principal.Principal;
import com.biblioGAFVi.BiblioGAFVi.Repository.IAutoresRepository;
import com.biblioGAFVi.BiblioGAFVi.Repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BiblioGafViApplication implements CommandLineRunner {
	@Autowired
	private ILibroRepository repositorioDeLibros;
	@Autowired
	private IAutoresRepository repositorioDeAutores;

	public static void main(String[] args) {
		SpringApplication.run(BiblioGafViApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioDeAutores,repositorioDeLibros);
		principal.muestraElMenu();
	}
}
