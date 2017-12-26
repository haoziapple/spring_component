package com.fzrj.starter.service.sandbox.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MyCustomValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return MyClass.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors)
	{
		MyClass myObject = (MyClass) target;

		if (StringUtils.isEmpty(myObject.getName()))
		{
			errors.rejectValue("name", "{myproject.myclass.validation.name}");
		}
	}
}
