package com.sumanth.magneto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataInteraction {
	private String cookies;
	private HttpClient client = HttpClientBuilder.create().build();
	private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

	public void sendPost(String url, List<NameValuePair> postParams)
			throws Exception {

		HttpPost post = new HttpPost(url);
		post.setHeader("Host", "myopenissues.com");
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
		post.setHeader("Cookie", getCookies());

		post.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
		HttpResponse response = client.execute(post);

		int responseCode = response.getStatusLine().getStatusCode();

		if ((responseCode == HttpStatus.SC_MOVED_TEMPORARILY)
				|| (responseCode == HttpStatus.SC_MOVED_PERMANENTLY)
				|| (responseCode == HttpStatus.SC_SEE_OTHER)
				|| (responseCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
			Header Headerheader = post.getFirstHeader("location");
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
	}

	public String GetPageContent(String url) throws Exception {

		HttpGet request = new HttpGet(url);

		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		request.setHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");

		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();


		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		setCookies(response.getFirstHeader("Set-Cookie") == null ? ""
				: response.getFirstHeader("Set-Cookie").toString());

		return result.toString();

	}

	public List<NameValuePair> getFormParams(String flag, String html,
			String firstname, String lastname, String email, String password)
			throws UnsupportedEncodingException {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		if (flag == "login") {
			System.out.println("Do Nothing");
		}

		else if (flag == "register") {
			Document doc = Jsoup.parse(html);
			Elements inputElements = doc.getElementsByTag("input");
			for (Element inputElement : inputElements) {
				String key = inputElement.attr("name");
				String value = inputElement.attr("value");
				if (key.equals("firstname"))
					value = firstname;
				else if (key.equals("lastname"))
					value = lastname;
				else if (key.equals("email"))
					value = email;
				else if (key.equals("password"))
					value = password;
				else if (key.equals("confirmation"))
					value = password;

				paramList.add(new BasicNameValuePair(key, value));

			}
		}
		return paramList;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}
