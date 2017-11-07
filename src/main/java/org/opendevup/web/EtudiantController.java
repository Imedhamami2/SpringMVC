package org.opendevup.web;

import java.util.List;

import org.opendevup.dao.EtudiantRepository;
import org.opendevup.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  
@RequestMapping(value="/Etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	@RequestMapping(value="/Index")
	public String Index(Model model, 
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="motCle", defaultValue="")String mc )
	{
		// int âge c'est la page courante
		Page<Etudiant> etds=etudiantRepository.chercherEtudiants("%"+mc+"%", new PageRequest(page, 2));
		// sotcker les données dans un model avant de les retournées dans la vue
		int pagesCount= etds.getTotalPages();
		int [] pages=new int[pagesCount];
		
		for (int i = 0; i < pagesCount; i++) {
			pages[i]=i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pagesCourante",page);
		model.addAttribute("pageEtudiants",etds);
		model.addAttribute("motCle",mc);
		return "etudiants";
	}
}
