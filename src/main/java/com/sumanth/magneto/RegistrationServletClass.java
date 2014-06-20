/**
 * 
 */
package com.sumanth.magneto;

import java.io.IOException;
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
 * Middleman form submit servlet which acts as a proxy for creating users within a
 * Magento system.  Form data is submitted to this servlet to create a user.  This servlet 
 * in turn repackages the form POST data and performs a POST on behalf of the user/system
 * to a Magento system, utilizing the same HTML POST endpoints as the Magento system's
 * own create user form submit.
 * 
 * Upon successful user creation, the user is created in the remote Magento system with
 * newsletter preferences set.
 * 
 * Upon failure, any error messages provided by the Magento system, via HTML scraping, are
 * relayed to this system's create user form.
 * 
 * Please note that this approach is not the recommended approach to acting as a proxy to
 * Nagento system operations.  This approach was required due to a lack of credentials to the
 * web services exposed by Magento.  A more appropriate approach is, having credentials, to use
 * either the SOAP web service endpoint customerCustomerCreate as defined by the WSDL 
 * (http://magentohost/api/v2_soap?wsdl=1).
 * 
 * @author stevefink
 *
 */
@SuppressWarnings("serial")
public class RegistrationServletClass extends HttpServlet {
	public static final String HOST = "http://myopenissues.com";
	public static final String FORM_PATH = "/magento/index.php/customer/account/create/";
	public static final String POST_PATH = "/magento/index.php/customer/account/createpost/";
	
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_FIRSTNAME = "firstname";
	public static final String PARAM_FIRST_NAME = "firstName";
	public static final String PARAM_LASTNAME = "lastname";
	public static final String PARAM_LAST_NAME = "lastName";
	public static final String PARAM_NEWSLETTER = "newsletter";
	public static final String PARAM_SUBSCRIBE = "is_subscribed";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_PASSWORD_2 = "password2";
	public static final String PARAM_CONFIRMATION = "confirmation";
	public static final String PARAM_SUCCESS_URL = "success_url";
	public static final String PARAM_ERROR_URL = "error_url";
	
	public static final String VALUE_ON = "on";
	public static final String VALUE_ONE = "1";
	
	public static final String LOCATION = "location";
	public static final String DOM_MATCH_MESSAGES = "ul.messages";
	
	public static final String ATTRIBUTE_SUCCESS = "success";
	public static final String ATTRIBUTE_MESSAGES = "messages";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//prepare POST data
		String formUrl = HOST + FORM_PATH;
		String postUrl = HOST + POST_PATH;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(postUrl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(PARAM_EMAIL, req.getParameter(PARAM_EMAIL)));
		params.add(new BasicNameValuePair(PARAM_FIRSTNAME, req.getParameter(PARAM_FIRST_NAME)));
		params.add(new BasicNameValuePair(PARAM_LASTNAME, req.getParameter(PARAM_LAST_NAME)));
		String newsletter = req.getParameter(PARAM_NEWSLETTER);
		if (newsletter != null && newsletter.equalsIgnoreCase(VALUE_ON)) {
			params.add(new BasicNameValuePair(PARAM_SUBSCRIBE, VALUE_ONE));
		}
		params.add(new BasicNameValuePair(PARAM_PASSWORD, req.getParameter(PARAM_PASSWORD)));
		params.add(new BasicNameValuePair(PARAM_CONFIRMATION, req.getParameter(PARAM_PASSWORD_2)));
		params.add(new BasicNameValuePair(PARAM_SUCCESS_URL, ""));
		params.add(new BasicNameValuePair(PARAM_ERROR_URL, ""));
		
		post.setEntity(new UrlEncodedFormEntity(params));
		
		//make initial connection to Magento to obtain a sessionId; wihtout this step the form
		//submit will not success as there is no valid session to post data within
		client.execute(new HttpGet(formUrl));
		//POST customer create data
		HttpResponse response = client.execute(post);
		//release the connection to free up resources
		post.releaseConnection();
		
		//process response from POST to determine success state
		Boolean ret = null;
		//read through header elements to find "location" which will inform us on the success state
		for (Header header : response.getAllHeaders()) {
			if (header.getName().equalsIgnoreCase(LOCATION)) {
				//check for location value equal to original form URL; this would indicate an error
				if (header.getValue().equalsIgnoreCase(formUrl)) {
					ret = false;	//set unsuccess state
					//follow redirect to form to obtain error messages
					HttpResponse redirect = client.execute(new HttpGet(header.getValue()));
					//scrape HTML for error messages
					Document dom = Jsoup.parse(redirect.getEntity().getContent(), null, header.getValue());
					Elements messages = dom.select(DOM_MATCH_MESSAGES);
					//add messages HTML to request attribute for use by renderers
					req.setAttribute(ATTRIBUTE_MESSAGES, messages.html());
				}
				else {	//redirect header location value is not the form URL; success condition
					ret = true;
				}
				break;
			}
		}
		
		//set success state into request attribute for renderer
		req.setAttribute(ATTRIBUTE_SUCCESS, ret);
		//forward to renderer
		req.getRequestDispatcher("/").forward(req, resp);
	}

	

}
