package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

@WebServlet("/users/*")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[LOG] - UserController.doGet() received request");
		
		String requestURI = request.getRequestURI();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		if(requestURI.equals("/servlet-mockito/users") || requestURI.equals("/servlet-mockito/users/")) {
			
			List<User> users = userService.getAllUsers();
			String usersJSON = mapper.writeValueAsString(users);
			response.setStatus(200);
			out.write(usersJSON);
			
		} else if (requestURI.contains("users/")) {
			
			String[] fragments = requestURI.split("/");
			
			try {
				int userId = Integer.parseInt(fragments[3]);
				User user = userService.getUserById(userId);
				String userJSON = mapper.writeValueAsString(user);
				response.setStatus(200);
				out.write(userJSON);
				
			} catch (NumberFormatException nfe) {
				response.setContentType("text/html");
				out.write("<h1>Operation Not Supported</h1><br/><a href=\"http://localhost:8080/servlet-mockito/users\">All Users</a>");
				response.setStatus(501);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User newUser = mapper.readValue(request.getInputStream(), User.class);
		newUser = userService.addUser(newUser);
		String userJSON = mapper.writeValueAsString(newUser);
		PrintWriter out = response.getWriter();
		out.write(userJSON);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User updatedUser = mapper.readValue(request.getInputStream(), User.class);
		if(userService.updateUser(updatedUser)) {
			response.setStatus(200);
		} else {
			response.setStatus(500);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("Operation not supported");
		response.setContentType("text/html");
		out.write("<h1>Operation Not Supported</h1><br/><a href=\"http://localhost:8080/servlet-mockito/users\">All Users</a>");
		response.setStatus(502);
	}
}
