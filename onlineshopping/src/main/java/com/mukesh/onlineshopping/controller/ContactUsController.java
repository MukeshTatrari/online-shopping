package com.mukesh.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.mukesh.shoppingbackend.dto.ContactUs;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Controller
public class ContactUsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsController.class);

	/**
	 * 
	 * @return display contact us page
	 */

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		mv.addObject("contactUs", this.getContact());

		if (operation != null) {
			if (operation.equals("contact")) {
				mv.addObject("message", "Your Message send Successfully !");
			}
		}
		return mv;

	}

	@ModelAttribute("contactUs")
	public ContactUs getContact() {
		return new ContactUs();

	}

	/**
	 * 
	 * 
	 * 
	 * handling product submission
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String handleContactUsSubmission(@Valid @ModelAttribute("contactUs") ContactUs contactUs,
					BindingResult results, Model model, HttpServletRequest request) {

		LOGGER.info("inside contact us Submission");

		if (results.hasErrors()) {
			LOGGER.info("product validation failed " + results);

			model.addAttribute("title", "Contact Us");
			model.addAttribute("userClickContact", true);
			model.addAttribute("message", "Message Submission Failed");

			LOGGER.info(contactUs.toString());
			return "page";

		}

		LOGGER.info(contactUs.toString());

		sendEmail();

		return "redirect:/contact?operation=contact";
	}

	private void sendEmail() {
		String to = "mukesh.tatrari@gmail.com";
		String from = "mukesh.tatrari@gmail.com";
		String host = "10.101.3.229";
		String port = "587";

		// Get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", port);
		Session session = Session.getDefaultInstance(properties);

		// compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Ping");
			message.setText("Hello, this is example of sending email  ");
			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
