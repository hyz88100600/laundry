//package com.laundry.controller.api;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.laundry.domain.Laundry;
//import com.laundry.domain.type.StatusCode;
//import com.laundry.pojo.BaseResult;
//import com.laundry.pojo.DataResult;
//import com.laundry.service.LaundryService;
//import com.wordnik.swagger.annotations.ApiOperation;
//
//@Controller
//@RequestMapping(value = "api/laundry")
//public class LaundryController {
//
//	@Autowired
//	private LaundryService laundryService;
//
//	// 获取店铺
//	@ApiOperation(value = "获取店铺", notes = "获取3千米内的店铺,目前只能获取所有")
//	@RequestMapping(value = "getAll", method = RequestMethod.POST)
//	@ResponseBody
//	public BaseResult getAll() {
//		List<Laundry> all = laundryService.findAll();
//		BaseResult baseResult = new BaseResult(StatusCode.success);
//		DataResult data = new DataResult();
//		data.setLaundry(all);
//
//		baseResult.setData(data);
//
//		return baseResult;
//
//	}
//}
