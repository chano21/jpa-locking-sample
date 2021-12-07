package kr.pco.core.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class HeaderConverter extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
	    public HeaderConverter(Authentication authentication) {
		super(authentication);
		// pco Auto-generated constructor stub
	}

		public boolean customAuthCheck(HttpServletRequest request) {
	        String jwtString = request.getHeader("apiKey");
	        System.out.println(jwtString);
	        // decode/process the JWT
	        // check authorization
	        return true;
	    }

		@Override
		public void setFilterObject(Object filterObject) {
			// pco Auto-generated method stub
			
		}

		@Override
		public Object getFilterObject() {
			// pco Auto-generated method stub
			return null;
		}

		@Override
		public void setReturnObject(Object returnObject) {
			// pco Auto-generated method stub
			
		}

		@Override
		public Object getReturnObject() {
			// pco Auto-generated method stub
			return null;
		}

		@Override
		public Object getThis() {
			// pco Auto-generated method stub
			return null;
		}
}
