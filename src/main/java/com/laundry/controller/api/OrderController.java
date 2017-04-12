package com.laundry.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.domain.type.StatusCode;
import com.laundry.dto.OrderDTO;
import com.laundry.dto.OrderItemDTO;
import com.laundry.pojo.BaseResult;
import com.laundry.service.OrderService;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	// 获取验证码
	@ApiOperation(value = "保存订单", notes = "保存订单")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult save(@RequestBody OrderDTO orderDTO) {
		BaseResult validateOrder = validateOrder(orderDTO);
		if(!validateOrder.getCode().equals(StatusCode.success.getCode())){
			return validateOrder;
		}

		//保存
		orderService.create(orderDTO);
		
		BaseResult baseResult = new BaseResult(StatusCode.success);
		return baseResult;
	}

	//
	private BaseResult validateOrder(OrderDTO orderDTO) {
		if(orderDTO.getLaundryId()==null||orderDTO.getItems()==null||orderDTO.getItems().length==0){
			return new BaseResult(StatusCode.param_error_blank);
		}else{
			OrderItemDTO[] items = orderDTO.getItems();
			
			for (OrderItemDTO item : items) {
				if(StringUtils.isBlank(item.getPicture())){
					return new BaseResult(StatusCode.param_error_blank);
				}
			}
		}
		
		return new BaseResult(StatusCode.success);
	}
}
