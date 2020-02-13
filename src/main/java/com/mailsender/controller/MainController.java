package com.mailsender.controller;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	public static int genotp;
	@Autowired
	public JavaMailSender sender;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sendMail1() {
		return "emailPage";

	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public String sendMail2(HttpServletRequest request) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		genotp = getotp();
		try {

			String email = request.getParameter("email");
			helper.setTo(email);
			helper.setText("Your OTP is " + genotp);
			helper.setSubject("OTP validation");
			sender.send(message);

		} catch (MessagingException e) {

		}

		return "redirect:/otpPage";

	}

	public int getotp() {

		Random r = new Random();
		int a = r.nextInt(9000) + 1000;
		return a;
	}

	@RequestMapping(value = "/otp", method = RequestMethod.POST)
	public String otpSuccess(HttpServletRequest request) {
		int OTP = Integer.valueOf(request.getParameter("otp"));

		if (OTP == genotp) {
			return "redirect:/success";
		} else {
			return "redirect:/failure";
		}

	}

	@RequestMapping(value = "/otpPage", method = RequestMethod.GET)
	public String otpPage(HttpServletRequest request) {

		return "otpPage";
	}
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {
		return "success";

	}
	@RequestMapping(value = "/failure", method = RequestMethod.GET)
	public String failure() {
		return "otperrorPage";

	}
	
	
	@Value("${message}")
	String message;
	
	@GetMapping("/welcome")
	public String getMessage()
	{
		return "Hi "+message;
	}
}
