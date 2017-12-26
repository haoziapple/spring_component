package com.fzrj.starter.service.sandbox.validator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class ValidateController
{
	@Autowired
	protected Validator validator;

	@Autowired
	private MyCustomValidator myCustomValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		binder.setValidator(validator);
		binder.addValidators(this.myCustomValidator);
	}

	@PostMapping(value = "/")
	public String save(@Valid @RequestBody MyClass myObject, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			System.out.println(bindingResult.getFieldError().getDefaultMessage());
		}

		return "success";
	}
}
