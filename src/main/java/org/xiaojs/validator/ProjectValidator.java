package org.xiaojs.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xiaojs.domain.Project;

public class ProjectValidator extends BasValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.equals(Project.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		if(target != null){
			Project project = (Project) target;
			
			if(project.getProjectName() == null || "".equals(project.getProjectName())){
				super.addError("projectName", "请输入项目名称!", errors);
			}
		}
	}
}
