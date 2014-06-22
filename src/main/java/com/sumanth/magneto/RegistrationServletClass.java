package com.sumanth.magneto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Class Name: RegistrationServletClass.java
 * 
 * Servlet to post the data to suitable end point on
 * http://myopenissues.com/magento/index.php and then parse the response and
 * display proper error message or success on your displayed form. i.e. the
 * servelet is sitting in between the user and http://myopenissues.com/magento/
 * and processing the input and return.
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */

@SuppressWarnings("serial")
public class RegistrationServletClass extends HttpServlet {

	public static final String SUCCESS_PARAMETER = "success";
	public static final String MESSAGE_ATTRIBUTE = "messages";

	public static final String URL = "http://myopenissues.com";
	public static final String URL_PATH = "/magento/index.php/customer/account/create/";
	public static final String URL_POST = "/magento/index.php/customer/account/createpost/";

	public static final String LOCATION = "location";
	public static final String DOM_MESSAGES = "ul.messages";

	public static final String NEWSLETTER_STATUS_ON = "on";
	public static final String VALUE_ONE = "1";

	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String EMAIL = "email";
	public static final String NEWSLETTER = "newsletter";
	public static final String SUBSCRIBE = "is_subscribed";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_2 = "password2";
	public static final String CONFIRMPASSWORD = "confirmation";
	public static final String SUCCESS_URL = "success_url";
	public static final String ERROR = "error_url";

	@Override
	protected void doPost(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws ServletException,
			IOException {
		try {

			// Initialize the post data
			String formUrl = URL + URL_PATH;
			String postUrl = URL + URL_POST;
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(postUrl);
			String firstName = httpRequest.getParameter(FIRSTNAME);
			String lastName = httpRequest.getParameter(LASTNAME);
			String email = httpRequest.getParameter(EMAIL);
			String newsLetter = httpRequest.getParameter(NEWSLETTER);
			String password = httpRequest.getParameter(PASSWORD);
			String password2 = httpRequest.getParameter(PASSWORD_2);

			List<NameValuePair> formData = getFormParams(firstName, lastName,
					email, newsLetter, password, password2);

			post.setEntity(new UrlEncodedFormEntity(formData));

			// obtaining session ID
			client.execute(new HttpGet(formUrl));
			// sending/posting the user information
			HttpResponse response = client.execute(post);
			// release connection to free resources
			post.releaseConnection();

			// process response from POST to determine success state
			Boolean successState = null;

			for (Header header : response.getAllHeaders()) {
				if (header.getName().equalsIgnoreCase(LOCATION)) {
					// check on location url to determine the success
					if (header.getValue().equalsIgnoreCase(formUrl)) {
						successState = false;
						// set the error messages
						HttpResponse redirect = client.execute(new HttpGet(
								header.getValue()));
						Document dom = Jsoup.parse(redirect.getEntity()
								.getContent(), null, header.getValue());
						Elements messages = dom.select(DOM_MESSAGES);
						String htmlMessages=messages.html();
						if(htmlMessages.contains("<li>"))
							htmlMessages=htmlMessages.substring(htmlMessages.indexOf("<li>"),htmlMessages.indexOf("</ul></li>"));
						httpRequest.setAttribute(MESSAGE_ATTRIBUTE,		htmlMessages);
					} else {
						successState = true;
					}
					break;
				}
			}
			httpRequest.setAttribute(SUCCESS_PARAMETER, successState);
			httpRequest.getRequestDispatcher("/").forward(httpRequest,		httpResponse);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public List<NameValuePair> getFormParams(String firstname, String lastname,
			String email, String newsletter, String password,
			String confirmationPassword) throws UnsupportedEncodingException {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		paramList.add(new BasicNameValuePair(FIRSTNAME, firstname));
		paramList.add(new BasicNameValuePair(LASTNAME, lastname));
		paramList.add(new BasicNameValuePair(EMAIL, email));

		if (newsletter != null
				&& newsletter.equalsIgnoreCase(NEWSLETTER_STATUS_ON)) {
			paramList.add(new BasicNameValuePair(SUBSCRIBE, VALUE_ONE));
		}
		paramList.add(new BasicNameValuePair(PASSWORD, password));
		paramList.add(new BasicNameValuePair(CONFIRMPASSWORD,
				confirmationPassword));
		paramList.add(new BasicNameValuePair(SUCCESS_URL, ""));
		paramList.add(new BasicNameValuePair(ERROR, ""));

		return paramList;
	}
}