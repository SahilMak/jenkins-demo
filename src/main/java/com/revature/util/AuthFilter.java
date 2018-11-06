package com.revature.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.AuthToken;
import com.revature.services.AuthTokenService;

@WebFilter("/*")
public class AuthFilter implements Filter {
	
	private AuthTokenService authService = new AuthTokenService();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		PrintWriter out = resp.getWriter();
		String requestURI = req.getRequestURI();
		String requestMethod = req.getMethod();
		Cookie[] clientCookies = req.getCookies();
		
		// Convenience variables to simplify if-statement
		boolean requestToGetAllUsers = (requestURI.equals("/servlet-mockito/users/") || requestURI.equals("/servlet-mockito/users")) && requestMethod.equals("GET");
		
		if(requestToGetAllUsers) {
			
			System.out.println("[LOG] - Checking to see if client is properly authenticated with the server");
			if(!isAuthenticated(clientCookies)) {
				
				System.out.println("[LOG] - Client is not properly authenticated with the server");
				response.setContentType("text/html");
				out.write("<h1>Forbidden</h1><br/><p>You are not properly authenticated with the server</p>");
				resp.setStatus(403);
				return;
				
			} else if(!isAdmin(clientCookies)) {
				
				System.out.println("[LOG] - Client is properly authenticated, checking for valid authorization");
				resp.setContentType("text/html");
				out.write("<h1>Unauthorized</h1><br/><p>You do not have the authorization to perform this action</p>");
				resp.setStatus(401);
				return;
				
			} else {
				System.out.println("[LOG] - Client is properly authenticated: " + isAuthenticated(clientCookies));
				if(clientCookies != null) System.out.println("[LOG] - Client has valid admin token: " + isAdmin(clientCookies));
				chain.doFilter(request, response);
				return;
			}
		
		}
		
		// If the request is not going to one of the above endpoints, then pass the request along to the proper controller
		chain.doFilter(request, response);
        
    }
	
	private boolean isAuthenticated(Cookie[] clientCookies) {
		
		AuthToken token = null;
		
		// If there are no client cookies, then the client has not been properly authenticated
		if(clientCookies == null) return false;
		
		// Loop through client cookies and find the one named "sessionID", set the value of this cookie to the AuthToken
		for(Cookie cookie : clientCookies) {
			if(cookie.getName().equals("sessionID")) {
				token = authService.getAuthTokenByID(cookie.getValue());
			}
		}
		
		// If the token is null, then the client has not been properly authenticated
		if(token == null) {
			System.out.println("[LOG] - No authentication token found in client cookies");
			return false;
		}
		
		// If the token is expired, then the client is not properly authenticated with the server
		if(token.getExpiration().isBefore(LocalDateTime.now())) {
			System.out.println("[LOG] - Authentication token is present, but expired");
			return false;
		}
		
		// If the above checks pass, then the client has been properly authenticated
		return true;
	}
	
	private boolean isAdmin(Cookie[] clientCookies) {
		
		AuthToken token = null;
				
		// Loop through client cookies and find the one named "sessionID", set the value of this cookie to the AuthToken
		for(Cookie cookie : clientCookies) {
			if(cookie.getName().equals("sessionID")) {
				token = authService.getAuthTokenByID(cookie.getValue());
			}
		}
		
		// If the token does not belong to an admin user, then the client does not have admin authorization
		return token.isAdmin();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("[LOG] - AuthFilter instance initialized");
		
	}

	@Override
	public void destroy() {
		System.out.println("[LOG] - AuthFilter instance destroyed");
	}
}
