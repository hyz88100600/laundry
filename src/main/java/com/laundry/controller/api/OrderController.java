package com.laundry.controller.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.domain.Order;
import com.laundry.domain.OrderItem;
import com.laundry.domain.type.StatusCode;
import com.laundry.dto.IdDTO;
import com.laundry.dto.OrderDTO;
import com.laundry.dto.OrderItemDTO;
import com.laundry.dto.OrderQueryDTO;
import com.laundry.pojo.BaseResult;
import com.laundry.pojo.DataResult;
import com.laundry.service.OrderService;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "保存订单", notes = "保存订单")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult save(@RequestBody OrderDTO orderDTO) {
		BaseResult validateOrder = validateOrder(orderDTO);
		if (!validateOrder.getCode().equals(StatusCode.success.getCode())) {
			return validateOrder;
		}
		// 保存
		orderService.create(orderDTO);
		BaseResult baseResult = new BaseResult(StatusCode.success);
		return baseResult;
	}

	// 查询
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表")
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult query(@RequestBody OrderQueryDTO orderQueryDTO) {
		if(StringUtils.isBlank(orderQueryDTO.getOrderStatus())||StringUtils.isBlank(orderQueryDTO.getPhone())){
			return new BaseResult(StatusCode.param_error_blank);
		}
		
		List<Order> orders = orderService.findByPhoneAndOrderStatus(orderQueryDTO.getPhone(), orderQueryDTO.getOrderStatus());
	
		BaseResult baseResult = new BaseResult(StatusCode.success);
		DataResult data = new DataResult();
		data.setOrder(orders);
		baseResult.setData(data);
		return baseResult;
	}
	
	//查询明细
	@ApiOperation(value = "查询明细", notes = "查询明细")
	@RequestMapping(value = "queryItem", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult queryItem(@RequestBody IdDTO idDTO) {
		if(idDTO.getId()==null){
			return new BaseResult(StatusCode.param_error_blank);
		}
		
		List<OrderItem> Items = orderService.findByItemsById(idDTO.getId());
	
		BaseResult baseResult = new BaseResult(StatusCode.success);
		DataResult data = new DataResult();
		data.setOrderItem(Items);
		baseResult.setData(data);
		return baseResult;
	}

	//
	private BaseResult validateOrder(OrderDTO orderDTO) {
		if (orderDTO.getLaundryId() == null
				|| StringUtils.isBlank(orderDTO.getPhone())
				|| orderDTO.getItems() == null
				|| orderDTO.getItems().length == 0) {
			return new BaseResult(StatusCode.param_error_blank);
		} else {
			OrderItemDTO[] items = orderDTO.getItems();

			for (OrderItemDTO item : items) {
				if (StringUtils.isBlank(item.getPicture())) {
					return new BaseResult(StatusCode.param_error_blank);
				}
			}
		}

		return new BaseResult(StatusCode.success);
	}
}
