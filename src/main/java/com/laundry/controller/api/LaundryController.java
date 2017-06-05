package com.laundry.controller.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.domain.Laundry;
import com.laundry.domain.type.StatusCode;
import com.laundry.dto.CreateLaundryDTO;
import com.laundry.dto.GetLaundryDTO;
import com.laundry.pojo.BaseResult;
import com.laundry.pojo.GetLaundryResult;
import com.laundry.service.LaundryService;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/laundry")
public class LaundryController {

	@Autowired
	private LaundryService laundryService;

	// 获取店铺
	@ApiOperation(value = "获取店铺", notes = "根据坐标获取店铺")
	@RequestMapping(value = "getLaundry", method = RequestMethod.POST)
	@ResponseBody
	public GetLaundryResult getLaundry(@RequestBody GetLaundryDTO getLaundryDTO) {
		List<Laundry> laundryByCoordinate = laundryService.getLaundryByCoordinate(getLaundryDTO);
		GetLaundryResult result = new GetLaundryResult(StatusCode.success);
		result.setLaundry(laundryByCoordinate);
		return result;
	}
	
	//创建店铺
	@ApiOperation(value = "创建店铺", notes = "创建店铺")
	@RequestMapping(value = "createLaundry", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult createLaundry(@RequestBody CreateLaundryDTO dto) {
		
		if(StringUtils.isBlank(dto.getName())||StringUtils.isBlank(dto.getHoster())||StringUtils.isBlank(dto.getContact())||dto.getDimension()==null||dto.getLongitude()==null){
			return new BaseResult(StatusCode.laundry_params_blank);
		}
		
		Laundry laundry = laundryService.getLaundry(dto.getDimension(), dto.getLongitude());
		
		if(laundry!=null){
			return new BaseResult();
		}
		
		laundryService.create(dto);
		
		return new BaseResult(StatusCode.success);
	}
}
