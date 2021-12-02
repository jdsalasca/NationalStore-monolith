package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.shop.security.token.JwtProvider;


@Controller
public class PrincipalController {
	
    @Autowired
    private JwtProvider jwtProvider;
	
	@GetMapping("/")
	public String index () {
		return "/swagger-ui.html#/";
	}
	


}
