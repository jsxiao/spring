package org.xiaojs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xiaojs.domain.Project;
import org.xiaojs.validator.ProjectValidator;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@RequestMapping(value = "list")
	public String list(Project params, BindingResult binding, Model model){
		
		ProjectValidator validator = new ProjectValidator();
		validator.validate(params, binding);
		
		model.addAttribute("project", params);
		
		if(binding.hasErrors()){
			System.out.println(binding);
			model.addAttribute("errors", validator.getErrors());
		}
		
		return "list";
	}
}
