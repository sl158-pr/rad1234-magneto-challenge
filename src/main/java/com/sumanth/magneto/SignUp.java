package com.sumanth.magneto;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.lang.Thread;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
@SuppressWarnings("deprecation")
public class SignUp extends HttpServlet
{
	private static final long serialVersionUID = 1L;	
	// public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 //    {
 //    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {    		
		int flag = Integer.parseInt(request.getParameter("flag"));
		String url = "http://myopenissues.com/magento/index.php/customer/account/create/";
		String urlpost = "http://myopenissues.com/magento/index.php/customer/account/createpost/";
		String webresult ="http://myopenissues.com/magento/index.php/";
		String loginurl = "http://myopenissues.com/magento/index.php/customer/account/login/";
		String loginurlpost = "http://myopenissues.com/magento/index.php/customer/account/loginPost/";
		String loginwebresult ="http://myopenissues.com/magento/index.php/customer/account/";
		String regEmail = request.getParameter("regEmail");
		String regpass = request.getParameter("regpass");
		String n=request.getParameter("fname");  
		String p=request.getParameter("lname");  
		String e=request.getParameter("email");  
		String c=request.getParameter("pass");
		String cp=request.getParameter("conpass");
		System.out.println(flag);		
		CookieHandler.setDefault(new CookieManager());
		DataInteraction http = new DataInteraction();
		try{
			switch (flag){
			case 0:
				String loginpage = http.GetPageContent(loginurl);
				List<NameValuePair> loginpostParams = http.getFormParams("login", loginpage, "", "", regEmail, regpass);
				http.sendPost(loginurlpost, loginpostParams);
				String loginresult = http.GetPageContent(loginwebresult);
				System.out.println(loginresult);
				response.getWriter().println(loginresult);
				System.out.println("Done");
				break;
			case 1:
				String page = http.GetPageContent(url);
				List<NameValuePair> postParams = http.getFormParams("register", page, n, p, e, c);
				http.sendPost(urlpost, postParams);

				String result = http.GetPageContent(webresult);
				response.getWriter().println(result);
				response.getWriter().println("<H2> Wait 3 Seconds and return</H2>");
				response.setHeader("Refresh", "3; URL=http://localhost:8080/rad1234/Register.jsp");
				System.out.println("Done");							
				break;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
    	}
}
    
