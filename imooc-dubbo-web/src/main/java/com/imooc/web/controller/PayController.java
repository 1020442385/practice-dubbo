package com.imooc.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.common.utils.IMoocJSONResult;
import com.imooc.curator.utils.ZKCurator;
import com.imooc.item.pojo.Items;
import com.imooc.item.service.ItemsService;
import com.imooc.web.service.CulsterService;

/**
 * @Description: 订购商品controller
 */
@Controller
public class PayController {
	
	@Autowired
	private CulsterService culsterService;
	
	@Autowired
	private ZKCurator zkCurator;
	
	@Autowired
	private ItemsService itemService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/buy")
	@ResponseBody
	public IMoocJSONResult buy(String itemId) {
		boolean result;
		if (StringUtils.isNotBlank(itemId)) {
			result = culsterService.displayBuy(itemId);
		} else {
			return IMoocJSONResult.errorMsg("商品id不能为空");
		}
		
		return IMoocJSONResult.ok(result ? "订单创建成功..." : "订单创建失败...");
	}
	
	/**
	 * 模拟集群下的数据不一致
	 * @param itemId
	 * @return
	 */
	
	@GetMapping("/buy2")
	@ResponseBody
	public IMoocJSONResult buy2(String itemId) {
		boolean result;
		if (StringUtils.isNotBlank(itemId)) {
			result = culsterService.displayBuy(itemId);
		} else {
			return IMoocJSONResult.errorMsg("商品id不能为空");
		}
		
		return IMoocJSONResult.ok(result ? "订单创建成功..." : "订单创建失败...");
	}
	
	@GetMapping("/getItem")
	@ResponseBody
	public IMoocJSONResult getItem(String itemId) {
		Items items = itemService.getItem(itemId);
		return IMoocJSONResult.ok(items);
	}
	
	/**
	 * 判断zk是否是连接的
	 * @return
	 */
	@RequestMapping("/isZKAlive")
	@ResponseBody
	public IMoocJSONResult isZKAlive() {
		boolean isAlive = zkCurator.isZKAlive();
		String result = isAlive ? "连接" : "断开";
		return IMoocJSONResult.ok(result);
	}
	
}
