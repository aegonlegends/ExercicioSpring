package br.iff.edu.exercicio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.iff.edu.exercicio.model.Aluno;
import br.iff.edu.exercicio.model.Alunos;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

	@Autowired
	private Alunos alunos;
	
	@GetMapping
	public ModelAndView listar(){
		ModelAndView modelAndView = new	ModelAndView("ListaAlunos");
		modelAndView.addObject(new Aluno());
		modelAndView.addObject("alunos", alunos.findAll());
		return modelAndView;
	}
	
	@PostMapping
	public String salvar(Aluno aluno){
		this.alunos.save(aluno);
		return "redirect:/alunos";
	}
	
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
		
	this.alunos.deleteById(id);
	return "redirect:/alunos";
	}
	
	@RequestMapping(value="/alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv = new ModelAndView("ListaAlunos");
	mv.addObject("alunos", alunos.findAll());
	Aluno aluno = alunos.findById(id).get();
	mv.addObject(aluno);
	return	mv;
	}
}
