package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.models.JwtDto;
import com.shop.models.LoginUser;
import com.shop.models.User;
import com.shop.repositories_dao.IUserDAO;
import com.shop.security.token.JwtProvider;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	IUserDAO iUserDAO;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@PreAuthorize("hasRole('USER')")
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> usuarios = iUserDAO.findAll();
		if (usuarios.isEmpty()) {
			return new ResponseEntity("users not found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(usuarios, HttpStatus.OK);

	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {

		Optional<User> user = iUserDAO.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("document/{document}")
	public ResponseEntity<User> getUserByDocument(@PathVariable String document) {

		List<User> user = iUserDAO.findByIdentityDocument(document);
		if (!user.isEmpty()) {
		
			return new ResponseEntity<User>(user.get(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("nick/{nick}")
	public ResponseEntity<User> getUserByNick(@PathVariable String nick) {

		List<User> user = iUserDAO.findByNick(nick);
		if (!user.isEmpty()) {
			return new ResponseEntity<User>(user.get(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<User> newUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(bindingResult.getAllErrors().toString() + "Please check the fiels",
					HttpStatus.BAD_REQUEST);

		if (!iUserDAO.findByNick(user.getNick()).isEmpty()) {
			return new ResponseEntity("Ya hay un nick con dicho nombre", HttpStatus.BAD_REQUEST);
		}
		if (!iUserDAO.findByEmail(user.getEmail()).isEmpty())
			return new ResponseEntity("este email ya esta registrado", HttpStatus.BAD_REQUEST);
		try {
			user.addUserRol();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userToSave = iUserDAO.save(user);
			return new ResponseEntity<User>(userToSave, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("user not save", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
		 if(iUserDAO.findByNick(loginUser.getNick()).isEmpty()){
			 return new ResponseEntity("please check all fields", HttpStatus.BAD_REQUEST);
		  }	
		User user = iUserDAO.findByNick(loginUser.getNick()).get(0);
	
	Authentication authentication =
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNick(), loginUser.getPassword()));
   SecurityContextHolder.getContext().setAuthentication(authentication);
   String jwt = jwtProvider.generateToken(authentication);
   UserDetails userDetails = (UserDetails)authentication.getPrincipal();
   JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
   return new ResponseEntity(jwtDto,  HttpStatus.OK);
	
	}
	
	@GetMapping("/auth")
	public  ResponseEntity<?>checkifTokenIsValid(@RequestHeader(value="Authorization") String token) {
		if (jwtProvider.validateToken(token)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping ("/{id}")
	public ResponseEntity<HttpStatus> deleteUserById (@PathVariable String id) {
		try {
			iUserDAO.deleteById(id);
			return new ResponseEntity("User deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("we couldn't delete that user sory", HttpStatus.FORBIDDEN);
		}
		
	}
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable String id, @Valid @RequestBody User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("Please check the fiels", HttpStatus.BAD_REQUEST);
		}
		Optional<User> userFound = iUserDAO.findById(id);
		if (userFound.isPresent()) {
			user.addUserRol();
			user.setId(id);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userSaved = iUserDAO.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
