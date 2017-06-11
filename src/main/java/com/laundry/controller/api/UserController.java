package com.laundry.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.domain.User;
import com.laundry.domain.type.StatusCode;
import com.laundry.dto.BaseDTO;
import com.laundry.dto.LoginDTO;
import com.laundry.dto.PhoneDTO;
import com.laundry.dto.UserDTO;
import com.laundry.pojo.GetSmsCodeResult;
import com.laundry.pojo.LoginResult;
import com.laundry.pojo.RegisterResult;
import com.laundry.service.AddressService;
import com.laundry.service.UserService;
import com.laundry.utils.BaseUtils;
import com.laundry.utils.MessageDigestUtils;
import com.laundry.utils.PublishSMSMessageUtils;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;

	private static Map<String, String> smsMap = new HashMap<String, String>();
	public static Map<String,String> tokenUserMap = new HashMap<String,String>();

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
				if (!smsMap.get(userDTO.getPhone())
						.equals(userDTO.getSmsCode())) {
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

		try {
			// 判断是否用户已存在
			User user = userService.findByPhone(userDTO.getPhone());
			if (user == null) {
				user = new User();
				user.setPhone(userDTO.getPhone());
				user.setPassword(MessageDigestUtils.encryptSHA1(userDTO
						.getPassword()));
				user.setNickName(userDTO.getPhone());
				userService.save(user);
				// 清除缓存
				smsMap.remove(userDTO.getPhone());
				return new RegisterResult(StatusCode.success);
			} else {
				return new RegisterResult(StatusCode.user_already_exist);
			}
		} catch (Exception e) {
			return new RegisterResult(StatusCode.sys_error);
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
		// 获取验证码
		Random random = new Random();
		String smsCode = "" + random.nextInt(10) + random.nextInt(10)
				+ random.nextInt(10) + random.nextInt(10);
		smsMap.put(phoneDTO.getPhone(), smsCode);
		// 发送短信
		PublishSMSMessageUtils.publish(phoneDTO.getPhone(), smsCode);
		// 返回
		GetSmsCodeResult getSmsCodeResult = new GetSmsCodeResult(
				StatusCode.success);
		getSmsCodeResult.setSmsCode(smsCode);
		return getSmsCodeResult;
	}

	// 用户登录
	@ApiOperation(value = "用户登录", notes = "用户登录(账号密码)")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResult login(@RequestBody LoginDTO loginDTO) {

		try {
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
				if (!user.getPassword().equals(
						MessageDigestUtils.encryptSHA1(loginDTO.getPassword()))) {
					return new LoginResult(StatusCode.user_password_error);
				} else {
					//生成token
					String token = MessageDigestUtils.encryptSHA1(user.getPhone()+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
					tokenUserMap.put(token, JSONObject.fromObject(user).toString());
					LoginResult loginResult = new LoginResult(
							StatusCode.success);
					loginResult.setNickName(user.getNickName());
					loginResult.setAddress(addressService.findByUserId(user.getId()));
					loginResult.setToken(token);
					return loginResult;
				}
			}
		} catch (Exception e) {
			return new LoginResult(StatusCode.sys_error);
		}
	}
	
	
	@ApiOperation(value = "用户登录", notes = "用户登录(token)")
	@RequestMapping(value = "login/token", method = RequestMethod.POST)
	@ResponseBody
	public LoginResult loginToken(@RequestHeader BaseDTO baseDTO) {

		try {
			StatusCode validateBaseDTO = validateBaseDTO(baseDTO);
			if(validateBaseDTO!=StatusCode.success){
				return new LoginResult(validateBaseDTO);
			}
			//获取user
			JSONObject user = JSONObject.fromObject(tokenUserMap.get(baseDTO.getToken()));
			
			//生成token
			String token = MessageDigestUtils.encryptSHA1(user.getString("phone")+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
			tokenUserMap.put(token, user.toString());
			LoginResult loginResult = new LoginResult(
					StatusCode.success);
			loginResult.setNickName(user.getString("nickName"));
			loginResult.setAddress(addressService.findByUserId(user.getLong("id")));
			loginResult.setToken(token);
			return loginResult;
		} catch (Exception e) {
			return new LoginResult(StatusCode.sys_error);
		}
	}
	
	public static StatusCode validateBaseDTO(BaseDTO baseDTO){
		if(StringUtils.isBlank(baseDTO.getToken())){
			return StatusCode.token_error_blank;
		}
		if(!tokenUserMap.containsKey(baseDTO.getToken())){
			return StatusCode.token_error_Invalid;
		}
		return StatusCode.success;
	}
}
