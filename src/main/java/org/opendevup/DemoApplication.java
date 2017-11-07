package org.opendevup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.opendevup.dao.EtudiantRepository;
import org.opendevup.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx= SpringApplication.run(DemoApplication.class, args);
		EtudiantRepository etudiantRepository=ctx.getBean(EtudiantRepository.class);
		DateFormat  df=new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("AHMED", df.parse("1988-11-10"), "ahmed@gmail.com","ahmed.jpg"));
		etudiantRepository.save(new Etudiant("NADER", df.parse("1990-11-10"), "NADER@gmail.com","nader.jpg"));
		etudiantRepository.save(new Etudiant("SAMIR", df.parse("1992-11-10"), "SAMIR@gmail.com","samir.jpg"));

		Page<Etudiant> etds=etudiantRepository.findAll(new PageRequest(0, 5));

		etds.forEach(e->System.out.println(e.getNom()));


		Page<Etudiant> etds1=etudiantRepository.chercherEtudiants("%S%", new PageRequest(0, 5));

		etds1.forEach(e->System.out.println(e.getNom()));

	}
}
