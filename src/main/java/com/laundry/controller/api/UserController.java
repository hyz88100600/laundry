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
import com.laundry.pojo.GetSmsCodeResult;
import com.laundry.pojo.LoginResult;
import com.laundry.pojo.RegisterResult;
import com.laundry.service.UserService;
import com.laundry.utils.BaseUtils;
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
	public RegisterResult register(@RequestBody UserDTO userDTO) {
		if (!BaseUtils.phoneCheck(userDTO.getPhone())) {
			return new RegisterResult(StatusCode.phone_error);
		} else {
			if (StringUtils.isBlank(userDTO.getSmsCode())) {
				return new RegisterResult(StatusCode.smsCode_error);
			} else {
				if (!"1234".equals(userDTO.getSmsCode())) {
					return new RegisterResult(StatusCode.smsCode_error);
				}
			}
		}
		//
		if (StringUtils.isBlank(userDTO.getPassword())) {
			return new RegisterResult(StatusCode.password_error_blank);
		} else {
			if (userDTO.getPassword().length() < 6
					|| userDTO.getPassword().length() > 12) {
				return new RegisterResult(StatusCode.password_error_length);
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
			return new RegisterResult(StatusCode.success);
		} else {
			return new RegisterResult(StatusCode.user_already_exist);
		}
	}

	// 获取验证码
	@ApiOperation(value = "获取手机验证码", notes = "获取手机验证码")
	@RequestMapping(value = "getSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public GetSmsCodeResult getSmsCode(@RequestBody PhoneDTO phoneDTO) {
		if (!BaseUtils.phoneCheck(phoneDTO.getPhone())) {
			return new GetSmsCodeResult(StatusCode.phone_error);
		}
		GetSmsCodeResult getSmsCodeResult = new GetSmsCodeResult(StatusCode.success);
		getSmsCodeResult.setSmsCode("1234");
		return getSmsCodeResult;
	}

	// 用户登录
	@ApiOperation(value = "用户登录", notes = "用户登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResult login(@RequestBody LoginDTO loginDTO) {
		if (!BaseUtils.phoneCheck(loginDTO.getPhone())) {
			return new LoginResult(StatusCode.phone_error);
		}

		if (StringUtils.isBlank(loginDTO.getPassword())) {
			return new LoginResult(StatusCode.password_error_blank);
		}

		User user = userService.findByPhone(loginDTO.getPhone());

		if (user == null) {
			return new LoginResult(StatusCode.user_not_exist);
		} else {
			if (!user.getPassword().equals(loginDTO.getPassword())) {
				return new LoginResult(StatusCode.user_password_error);
			} else {
				 LoginResult loginResult = new LoginResult(StatusCode.success);
				 loginResult.setNickName(user.getNickName());
				 return loginResult;
			}
		}
	}

}
