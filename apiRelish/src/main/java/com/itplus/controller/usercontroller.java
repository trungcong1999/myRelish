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

import com.itplus.entity.User;
import com.itplus.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	//hiển thị danh sách userAdmin
	@RequestMapping(value = "/admin/pages/user/list", method = RequestMethod.GET)
	public String getUserListPage(HttpServletRequest request) {
		List<User> list = userService.getAllUser();
		request.setAttribute("listUser", list);
		
		return "pages/manages/alluser";
	}
	
	//đăng ký useradmin
	@RequestMapping(value = "/admin/pages/user/adduser", method = RequestMethod.GET)
	  public String showRegister(Model model) {
		User user = new User();
		model.addAttribute("userAdmin", user);
		return "pages/manages/adduser";
	   
	  }
	 
	  @RequestMapping(value = "/admin/pages/user/add", method = RequestMethod.POST)
	  public String addUser(@ModelAttribute("userAdmin") User user,@RequestParam("name") String name,
			  @RequestParam("email") String email,@RequestParam("password") String password,
			  @RequestParam("birthday") String birthday,@RequestParam("gender") String gender,
			  @RequestParam("bio") String bio,@RequestParam("image") String image) {
		  userService.addUser(user);
		  return "redirect:list";
	  }
	  
	  //Sửa thông tin
	  @RequestMapping(value = "/admin/pages/user/edit/{id}", method = RequestMethod.GET)
	  public String editUser(@PathVariable int id, Model model) {
		  User user = userService.findUserById(id);
		  model.addAttribute("editUser", user);
		  return "/pages/manages/editUser";
	  }
	  
	  @RequestMapping(value = "/admin/pages/user/editSave", method = RequestMethod.POST)
	  public String editUserSave(@ModelAttribute("editUser") User user) {
		  userService.updateUser(user);
		  return "redirect:list";
	  }
	  //Xóa user
	  @RequestMapping(value = "/admin/pages/user/delete/{id}", method = RequestMethod.GET)
	  public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "redirect:/pages/user/list";
		  
	  }
	  
	 //profile user {chưa đăng nhập được nên không thể vào profileuser}
	  @RequestMapping(value = "/admin/pages/user/profileUser/{id}",method = RequestMethod.GET)
	  public String profileUser(@PathVariable int id,Model model) {
		  User user = userService.findUserById(id);
		  model.addAttribute("profileUser", user);
		  return "/pages/manages/profileuser";
	  }
}
