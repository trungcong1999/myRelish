package com.itplus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itplus.dao.impl.CreatorDaoImpl;
import com.itplus.entity.Creator;
import com.itplus.entity.Truyen;
import com.itplus.service.CreatorService;
import com.itplus.service.TruyenService;

@Controller
public class TruyenController {
	@Autowired
	TruyenService truyenService;
	
	//lấy tất cả truyện kèm tác giả
	@RequestMapping(value = "/pages/novel/list", method = RequestMethod.GET)
	public String getFilmList(HttpServletRequest request) {
		List<Truyen> listNovel = truyenService.getAllWithTG();
		request.setAttribute("listNovel", listNovel);
		return "/pages/novel/list";	
	}
	
	//thêm Truyện
			@RequestMapping(value = "/pages/novel/addNovel",method = RequestMethod.GET)
			public String showAddTruyen(Model model,HttpServletRequest request) {
				Truyen truyen = new Truyen();
				/* List<Creator> creators = creatorService.getAll(); */
				/* request.setAttribute("listCreator", creators); */
				model.addAttribute("addNovel", truyen);
				return "pages/novel/addNovel";
				
			}
			
			@RequestMapping(value = "/pages/novel/addNovel",method = RequestMethod.POST)
			public String AddNovel(@ModelAttribute("addNovel") Truyen truyen, @RequestParam String name,@RequestParam String author_id,@RequestParam String description,
					@RequestParam String cover_img,@RequestParam String release_date ) {
				truyenService.addGTruyen(truyen);
				return "redirect:list";
				
			}
			//Sửa thông tin truyên
			  @RequestMapping(value = "/pages/novel/editNovel/{id}", method = RequestMethod.GET)
			  public String editNovel(@PathVariable int id, Model model,HttpServletRequest request) {
				  Truyen truyen = truyenService.getTruyenById(id);
				  List<Truyen> list = truyenService.getAllWithTG();
				request.setAttribute("listNovel", list);
				  model.addAttribute("editNovel", truyen);
				  return "/pages/novel/editNovel";
			  }
			  
			  @RequestMapping(value = "/pages/novel/editNovel", method = RequestMethod.POST)
			  public String editNovelSave(@ModelAttribute("editNovel") Truyen truyen) {
				  truyenService.updateTruyen(truyen);
				  return "redirect:list";
			  }
			  // Xóa truyên
			  
			  @RequestMapping(value = "/pages/novel/delete/{id}", method = RequestMethod.GET)
			  public String deleteNovel(@PathVariable int id) {
				truyenService.deleteTruyen(id);
				return "redirect:/pages/novel/list";
				  
			  }
}
