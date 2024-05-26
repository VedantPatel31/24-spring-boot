package com.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.UserBean;
import com.dao.UserDao;
@RestController
public class SessionController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public String addUser(@RequestBody UserBean userBean) {
		System.out.println(userBean.getEmail());
		String hashPassword = passwordEncoder.encode(userBean.getPassword());
		System.out.println(hashPassword);
		userBean.setPassword(hashPassword);     
		userDao.addUser(userBean);
		return "signUp success";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserBean userBean) {
		System.out.println(userBean.getEmail());     
		UserBean user =  userDao.loginUser(userBean);
		Boolean ans = passwordEncoder.matches(userBean.getPassword(), user.getPassword());
		if(user!=null && ans) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
		}
	}
	@DeleteMapping("/deleteuser/{userId}")
	public void deleteUser(@PathVariable("userId") Integer userId) {    
		userDao.deleteUser(userId);
	}
	
	@PutMapping("updateUser/{userId}")
	public void updateUser(@PathVariable("userId") Integer userId , @RequestBody UserBean userBean) {
		Boolean ans =  userDao.updateUser(userId , userBean);
	}
}
  