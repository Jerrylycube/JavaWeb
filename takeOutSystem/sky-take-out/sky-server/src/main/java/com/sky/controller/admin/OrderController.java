package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
@Api(tags = "管理端订单接口")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 订单搜索,分页查询
	 */
	@GetMapping("/conditionSearch")
	@ApiOperation("订单搜索,分页查询")
	public Result<PageResult> page(OrdersPageQueryDTO ordersPageQueryDTO) {
		log.info("订单搜索,分页查询：{}", ordersPageQueryDTO);
		PageResult pageResult = orderService.pageQueryAdmin(ordersPageQueryDTO);
		return Result.success(pageResult);
	}
	
	/**
	 * 订单分类的数量(待派送..)
	 */
	@GetMapping("/statistics")
	@ApiOperation("订单分类的数量(待派送..)")
	public Result<OrderStatisticsVO> statistics() {
		log.info("订单分类的数量(待派送..)");
		return Result.success(orderService.statistics());
	}
	
	/**
	 * 订单详情
	 */
	@GetMapping("/details/{id}")
	@ApiOperation("订单详情")
	public Result<OrderVO> getOrderDetail(@PathVariable Long id) {
		log.info("订单详情：{}", id);
		return Result.success(orderService.getOrderDetail(id));
	}
	
	/**
	 * 接单
	 *
	 * @return
	 */
	@PutMapping("/confirm")
	@ApiOperation("接单")
	public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
		orderService.confirm(ordersConfirmDTO);
		return Result.success();
	}
	
	/**
	 * 拒单
	 *
	 * @return
	 */
	@PutMapping("/rejection")
	@ApiOperation("拒单")
	public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
		orderService.rejection(ordersRejectionDTO);
		return Result.success();
	}
	
	/**
	 * 取消订单
	 *
	 * @return
	 */
	@PutMapping("/cancel")
	@ApiOperation("取消订单")
	public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
		orderService.cancel(ordersCancelDTO);
		return Result.success();
	}
	
	/**
	 * 派送订单
	 *
	 * @return
	 */
	@PutMapping("/delivery/{id}")
	@ApiOperation("派送订单")
	public Result delivery(@PathVariable("id") Long id) {
		orderService.delivery(id);
		return Result.success();
	}
	
	/**
	 * 完成订单
	 *
	 * @return
	 */
	@PutMapping("/complete/{id}")
	@ApiOperation("完成订单")
	public Result complete(@PathVariable("id") Long id) {
		orderService.complete(id);
		return Result.success();
	}
}
