package com.itplus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.itplus.entity.Truyen;
import com.itplus.service.TruyenService;

@RestController
public class TruyenRestController {
	@Autowired
	TruyenService truyenService;

	// Danh sách tất cả Truyện
	@RequestMapping(value = "/search/name/truyen/", method = RequestMethod.GET)
	public String getTruyenList(HttpServletRequest request) {
		List<Truyen> list = truyenService.getAll();
		request.setAttribute("listTruyen", list);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// Thêm Truyện
	@RequestMapping(value = "/ws-add-new-truyen", method = RequestMethod.POST)
	public String addTruyen(@RequestBody Truyen truyen) {
		try {
			truyenService.addGTruyen(truyen);
			return "add truyen success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add truyen error";
		}

	}

	// Sửa Truyện
	@RequestMapping(value = "/ws-edit-truyen", method = RequestMethod.POST)
	public String save(@RequestBody Truyen truyen) {
		try {
			truyenService.updateTruyen(truyen);
			return "add truyen success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add truyen error";
		}
	}

	// Xóa Truyên
	@RequestMapping(value = "/ws-delete-truyen/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			truyenService.deleteTruyen(id);
			return "add truyen success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add truyen error";
		}
	}

	// tìm theo tên Truyện
	@RequestMapping(value = "/search/name/truyen/{name}")
	public String getTruyenByName(HttpServletRequest request, @PathVariable String name) {
		List<Truyen> truyens = truyenService.findByName(name);
		request.setAttribute("listTruyenName", truyens);
		Gson gson = new Gson();
		return gson.toJson(truyens);
	}

	// Tìm kiếm tên theo thể loại Truyện
	@RequestMapping(value = "/search/the-loai-truyen/{categoryId}")
	public String getTruyenByCategoryId(HttpServletRequest request, @PathVariable String categoryId) {
		List<Truyen> truyenCategory = truyenService.findByCategoryTruyenId(categoryId);
		request.setAttribute("listCategoryTruyen", truyenCategory);
		Gson gson = new Gson();
		return gson.toJson(truyenCategory);
	}
}
