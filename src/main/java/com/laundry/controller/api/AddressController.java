package com.laundry.controller.api;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.domain.User;
import com.laundry.domain.type.StatusCode;
import com.laundry.dto.AddressDTO;
import com.laundry.dto.BaseDTO;
import com.laundry.pojo.BaseResult;
import com.laundry.service.AddressService;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	// 创建地址
	@ApiOperation(value = "上传位置信息", notes = "上传位置信息")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult save(@RequestHeader(required=false) String token,@RequestBody AddressDTO addressDTO) {
		StatusCode validateBaseDTO = UserController.validateBaseDTO(new BaseDTO(token, null));
		if(validateBaseDTO!=StatusCode.success){
			return new BaseResult(validateBaseDTO);
		}
		User user = (User)JSONObject.toBean(JSONObject.fromObject(UserController.tokenUserMap.get(token)), User.class);
		addressService.create(addressDTO,user.getId());
		return new BaseResult(StatusCode.success);
	}
}
