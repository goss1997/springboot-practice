package com.githrd.demo_transaction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.githrd.demo_transaction.service.ProductService;
import com.githrd.demo_transaction.vo.ProductVo;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping("/product/list.do")
	public String list(Model model) {
		
		Map<String,List<ProductVo>> map = productService.selectTotalMap();
		
		model.addAttribute("map", map);
		
		return "product/product_list";
	}
	
	
	//입고처리
	// /product/insert_in.do?name=TV&cnt=100
	@RequestMapping("/product/insert_in.do")
	public String insert_in(ProductVo vo) {
		
		try {
			
			productService.insert_in(vo);
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return "redirect:list.do";
	}
	
	
	//출고처리
	// /product/insert_out.do?name=TV&cnt=100
	@RequestMapping("/product/insert_out.do")
	public String insert_out(ProductVo vo,RedirectAttributes ra) {
		
		try {
			
			productService.insert_out(vo);
			
		} catch (Exception e) {
			//e.printStackTrace();
			String message = e.getMessage();
			//System.out.println(message);
			ra.addAttribute("error", message);
			
		}
		//               list.do?error=remain_not  
		return "redirect:list.do";
	}
	
	
	
	//입고취소
	// /product/delete_in.do?idx=5
	@RequestMapping("/product/delete_in.do")
	public String delete_in(int idx,RedirectAttributes ra) {
		
		//System.out.println("--입고취소처리--");
		try {
			productService.delete_in(idx);
			
		} catch (Exception e) {
			ra.addAttribute("error", e.getMessage());
		}
		
		
		return "redirect:list.do";
	}
	
	
	//  출고취소
	// /product/delete_out.do?idx=5
	@RequestMapping("/product/delete_out.do")
	public String delete_out(int idx,RedirectAttributes ra) {
		
		try {
			productService.delete_out(idx);
			
		} catch (Exception e) {
			ra.addAttribute("error", e.getMessage());
		}
		
		
		return "redirect:list.do";
	}

	
	
	
	
	
	
	
}
