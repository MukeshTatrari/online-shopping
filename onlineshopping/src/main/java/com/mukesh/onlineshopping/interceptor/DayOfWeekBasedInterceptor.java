package com.mukesh.onlineshopping.interceptor;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DayOfWeekBasedInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {

		try {
			Calendar calendar = Calendar.getInstance();
			int dayOfTheWeek = calendar.get(calendar.DAY_OF_WEEK);

			if (dayOfTheWeek == 1) {

				response.getWriter()
						.write("The WebSite is closed on Sunday , Please try Accessing it on Some Other Day");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

}
