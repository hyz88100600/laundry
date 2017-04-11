package com.laundry.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.domain.User;
import com.laundry.domain.type.StatusCode;
import com.laundry.dto.LoginDTO;
import com.laundry.dto.PhoneDTO;
import com.laundry.dto.UserDTO;
import com.laundry.pojo.BaseResult;
import com.laundry.pojo.DataResult;
import com.laundry.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/user")
public class UserController {

	@Autowired
	private UserService userService;

	// 注册
	@ApiOperation(value = "用户注册", notes = "根据手机号和验证码注册")
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult register(@RequestBody UserDTO userDTO) {
		if (!phoneCheck(userDTO.getPhone())) {
			return new BaseResult(StatusCode.phone_error);
		} else {
			if (StringUtils.isBlank(userDTO.getSmsCode())) {
				return new BaseResult(StatusCode.smsCode_error);
			} else {
				if (!"1234".equals(userDTO.getSmsCode())) {
					return new BaseResult(StatusCode.smsCode_error);
				}
			}
		}
		//
		if (StringUtils.isBlank(userDTO.getPassword())) {
			return new BaseResult(StatusCode.password_error_blank);
		} else {
			if (userDTO.getPassword().length() < 6
					|| userDTO.getPassword().length() > 12) {
				return new BaseResult(StatusCode.password_error_length);
			}
		}

		// 判断是否用户已存在
		User user = userService.findByPhone(userDTO.getPhone());
		if (user == null) {
			user = new User();
			user.setPhone(userDTO.getPhone());
			user.setPassword(userDTO.getPassword());
			user.setNickName(userDTO.getPhone());
			userService.save(user);

			return new BaseResult(StatusCode.success);
		} else {
			return new BaseResult(StatusCode.user_already_exist);
		}
	}

	// 获取验证码
	@ApiOperation(value = "获取手机验证码", notes = "获取手机验证码")
	@RequestMapping(value = "getSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult getSmsCode(@RequestBody PhoneDTO phoneDTO) {
		if (!phoneCheck(phoneDTO.getPhone())) {
			return new BaseResult(StatusCode.phone_error);
		}
		DataResult data = new DataResult();
		data.setSmsCode("1234");

		BaseResult baseResult = new BaseResult(StatusCode.success);
		baseResult.setData(data);
		return baseResult;
	}

	// 用户登录
	@ApiOperation(value = "用户登录", notes = "用户登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult login(@RequestBody LoginDTO loginDTO) {
		if (!phoneCheck(loginDTO.getPhone())) {
			return new BaseResult(StatusCode.phone_error);
		}

		if (StringUtils.isBlank(loginDTO.getPassword())) {
			return new BaseResult(StatusCode.password_error_blank);
		}

		User user = userService.findByPhone(loginDTO.getPhone());

		if (user == null) {
			return new BaseResult(StatusCode.user_not_exist);
		} else {
			if (!user.getPassword().equals(loginDTO.getPassword())) {
				return new BaseResult(StatusCode.user_password_error);
			} else {
				 BaseResult baseResult = new BaseResult(StatusCode.success);
				 DataResult dataResult = new DataResult();
				 dataResult.setNickName(user.getNickName());
				 baseResult.setData(dataResult);
				 
				 return baseResult;
			}
		}
	}

	// 验证手机号是否符合规则
	private boolean phoneCheck(String phone) {

		if (StringUtils.isBlank(phone)) {
			return false;
		}
		String regex = "1[358][0-9]{9,9}";

		return phone.matches(regex);
	}

}
