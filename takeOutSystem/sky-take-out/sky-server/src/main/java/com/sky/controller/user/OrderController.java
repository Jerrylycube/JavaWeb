package com.sky.controller.user;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@Api(tags = "C端订单接口")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 用户下单
	 *
	 * @param ordersSubmitDTO
	 * @return
	 */
	@PostMapping("/submit")
	@ApiOperation("用户下单")
	public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
		log.info("用户下单：{}", ordersSubmitDTO);
		OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
		return Result.success(orderSubmitVO);
	}
	
	/**
	 * 订单支付
	 *
	 * @param ordersPaymentDTO
	 * @return
	 */
	@PutMapping("/payment")
	@ApiOperation("订单支付")
	public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
		log.info("订单支付：{}", ordersPaymentDTO);
		//测试阶段,暂时不使用
		//直接修改订单状态为支付成功
		//要查看订单状态为待支付的订单才能进行支付
		OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
		log.info("生成预支付交易单：{}", orderPaymentVO);
		orderService.paySuccess(ordersPaymentDTO.getOrderNumber());
		return Result.success(orderPaymentVO);
	}
	
	/**
	 * 查询该用户所有的订单信息
	 *
	 */
	@GetMapping("historyOrders")
	@ApiOperation("查询该用户所有的订单信息")
	public Result<PageResult> list(OrdersPageQueryDTO ordersPageQueryDTO) {
		log.info("查询历史订单：{}", ordersPageQueryDTO);
		PageResult pageResult = orderService.pageQueryUser(ordersPageQueryDTO);
		return Result.success(pageResult);
	}
	
	/**
	 * 查询订单详情
	 */
	@GetMapping("/orderDetail/{id}")
	@ApiOperation("查询订单详情")
	public Result<OrderVO> getOrderDetail(@PathVariable Long id) {
		log.info("查询订单详情：{}", id);
		return Result.success(orderService.getOrderDetail(id));
	}
	
	/**
	 * 取消订单
	 */
	@PutMapping("/cancel/{id}")
	@ApiOperation("取消订单")
	public Result<String> cancel(@PathVariable Long id) throws Exception {
		log.info("取消订单：{}", id);
		orderService.cancel(id);
		return Result.success();
	}
	
	/**
	 * 再来一单
	 */
	@PostMapping("/repetition/{id}")
	@ApiOperation("再来一单")
	public Result<String> repetition(@PathVariable Long id) {
		log.info("再来一单：{}", id);
		orderService.repetition(id);
		return Result.success();
	}
	
	/**
	 * 用户催单
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/reminder/{id}")
	@ApiOperation("用户催单")
	public Result reminder(@PathVariable("id") Long id) {
		orderService.reminder(id);
		return Result.success();
	}
}
