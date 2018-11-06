package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.AuthToken;
import com.revature.services.AuthTokenService;

@WebServlet("/auth")
public class AuthController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private AuthTokenService authService = new AuthTokenService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Cookie[] clientCookies = request.getCookies();
		
		if(clientCookies != null) {
			for(Cookie cookie : clientCookies) {
				if(authService.removeTokenByID(cookie.getValue())) {
					System.out.println("[LOG] - Token with sessionID: " + cookie.getValue() + " successfully invalidated");
					response.setStatus(200);
				} else {
					System.out.println("[LOG] - No token with sessionID: " + cookie.getValue() + " found");
					response.setStatus(401);
				}
			}
		} else {
			System.out.println("[LOG] - No client cookies found");
			response.setStatus(401);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		String[] credentials = mapper.readValue(request.getInputStream(), String[].class);
		
		if(credentials.length != 2) {
			System.out.println("[LOG] - Invalid request, unexpected number of credential values");
			response.setStatus(400);
			return;
		}
		
		AuthToken authToken = authService.loginUser(credentials[0], credentials[1]);
		
		if(authToken == null) {
			response.setStatus(401);
			return;
		}

		response.setStatus(200);
		Cookie authCookie = new Cookie("sessionID", authToken.getSessionID().toString());
		authCookie.setMaxAge(3600);
		response.addCookie(authCookie);
	}
	
	
}
