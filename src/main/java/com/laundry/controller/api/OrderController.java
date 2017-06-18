package com.laundry.controller.api;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.laundry.domain.Order;
import com.laundry.domain.OrderItem;
import com.laundry.domain.type.OrderStatus;
import com.laundry.domain.type.StatusCode;
import com.laundry.dto.BaseDTO;
import com.laundry.dto.IdDTO;
import com.laundry.dto.ModifyOrderStatusDTO;
import com.laundry.dto.OrderDTO;
import com.laundry.dto.OrderItemDTO;
import com.laundry.dto.OrderOfferDTO;
import com.laundry.dto.OrderQueryDTO;
import com.laundry.pojo.BaseResult;
import com.laundry.pojo.QueryOrderItemResult;
import com.laundry.pojo.QueryOrderResult;
import com.laundry.pojo.UploadResult;
import com.laundry.service.OrderService;
import com.laundry.utils.BaseConfig;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	//保存
	@ApiOperation(value = "保存订单", notes = "保存订单")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult save(@RequestBody OrderDTO orderDTO,@RequestHeader(required=false) String token) {
		BaseDTO baseDTO = new BaseDTO();
		baseDTO.setToken(token);
		StatusCode validateBaseDTO = UserController.validateBaseDTO(baseDTO);
		if(validateBaseDTO!=StatusCode.success){
			return new UploadResult(validateBaseDTO);
		}
		//获取user
		JSONObject user = JSONObject.fromObject(UserController.tokenUserMap.get(baseDTO.getToken()));
		BaseResult validateOrder = validateOrder(orderDTO);
		if (!validateOrder.getCode().equals(StatusCode.success.getCode())) {
			return validateOrder;
		}
		orderDTO.setPhone(user.getString("phone"));
		// 保存
		orderService.create(orderDTO);
		BaseResult baseResult = new BaseResult(StatusCode.success);
		return baseResult;
	}
	
	//报价
	@ApiOperation(value = "报价", notes = "报价")
	@RequestMapping(value = "offer", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult offer(@RequestBody OrderOfferDTO dto) {
		//判断状态是否正确
		Long orderId = dto.getOrderId();
		Order findOne = orderService.findOne(orderId);
		
		if(findOne.getOrderStatus()!=OrderStatus.init){
			return new BaseResult(StatusCode.order_status_error);
		}
		// 保存
		orderService.offer(dto);
		return new BaseResult(StatusCode.success);
	}
	
	//改变状态
	@ApiOperation(value = "改变状态", notes = "改变状态")
	@RequestMapping(value = "modifyStatus", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult modifyStatus(@RequestBody ModifyOrderStatusDTO dto) {
		// 保存
		orderService.modifyStatus(dto);
		return new BaseResult(StatusCode.success);
	}

	// 查询
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表")
	@RequestMapping(value = "queryOrder", method = RequestMethod.POST)
	@ResponseBody
	public QueryOrderResult queryOrder(@RequestBody OrderQueryDTO orderQueryDTO) {
		
		if(StringUtils.isBlank(orderQueryDTO.getOrderStatus())||StringUtils.isBlank(orderQueryDTO.getPhone())){
			return new QueryOrderResult(StatusCode.param_error_blank);
		}
		
		List<Order> orders = orderService.findByPhoneAndOrderStatus(orderQueryDTO.getPhone(), orderQueryDTO.getOrderStatus());
		QueryOrderResult baseResult = new QueryOrderResult(StatusCode.success);
		baseResult.setOrder(orders);
		return baseResult;
	}
	
	//查询明细
	@ApiOperation(value = "查询明细", notes = "查询明细")
	@RequestMapping(value = "queryOrderItem", method = RequestMethod.POST)
	@ResponseBody
	public QueryOrderItemResult queryOrderItem(@RequestBody IdDTO idDTO) {
		if(idDTO.getId()==null){
			return new QueryOrderItemResult(StatusCode.param_error_blank);
		}
		List<OrderItem> Items = orderService.findByItemsById(idDTO.getId());
		QueryOrderItemResult baseResult = new QueryOrderItemResult(StatusCode.success);
		baseResult.setOrderItem(Items);
		return baseResult;
	}
	
	//上传图片
	@ApiOperation(value = "上传图片", notes = "上传图片")
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public UploadResult upload(@RequestParam(value = "file", required = false) MultipartFile file,@RequestHeader(required=false) String token){
		BaseDTO baseDTO = new BaseDTO();
		baseDTO.setToken(token);
		StatusCode validateBaseDTO = UserController.validateBaseDTO(baseDTO);
		if(validateBaseDTO!=StatusCode.success){
			return new UploadResult(validateBaseDTO);
		}
		//获取user
		JSONObject user = JSONObject.fromObject(UserController.tokenUserMap.get(baseDTO.getToken()));
		//获取基本上传路径
		String upload = BaseConfig.getCfgValue("upload");
		//拼接拼接完整路径
		String url = upload + user.getString("phone") +"/";
		//拼接图片名称
		String name = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		//保存图片
		File targetD = new File(url);
		if(!targetD.exists()){
			targetD.mkdirs();
		}
		File target = new File(url+name);
		try {
			file.transferTo(target);
			UploadResult uploadResult = new UploadResult(StatusCode.success);
			uploadResult.setUrl("/"+user.getString("phone")+"/"+name);
			return uploadResult;
		} catch (Exception e) {
			return new UploadResult(StatusCode.sys_error);
		}
	}

	//校验
	private BaseResult validateOrder(OrderDTO orderDTO) {
		if (orderDTO.getLaundryId() == null
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
