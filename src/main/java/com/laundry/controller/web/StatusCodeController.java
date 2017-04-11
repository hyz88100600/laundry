package com.laundry.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping(value = "statuscode")
public class StatusCodeController {

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String show() {
		return "statusCode/index";
	}
}
