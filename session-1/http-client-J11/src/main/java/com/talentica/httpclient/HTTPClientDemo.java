package com.talentica.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import reactor.core.publisher.Mono;

/**
 * The HttpClient, introduced with Java11, implements the client-side of the
 * most recent HTTP standards.
 * <li>It supports HTTP/1.1 and HTTP/2,</li>
 * <li>Supports both synchronous and asynchronous programming model</li>
 * 
 *
 */
/**
 * @author AnupamG
 *
 */
public class HTTPClientDemo {

	private UserService userService;
	private UserPreferenceService userPreferenceService;

	/**
	 * Before Java 11, we had to rely on a the basic URLConnection implementation or
	 * third-party libraries such as Apache HttpClient. This obviously is quite
	 * verbose and full of noise.
	 */
	public void httpClientPreJ11n() {
		URL url;
		try {
			url = new URL("https://foo.com/posts/1");
		} catch (MalformedURLException e) {
			// Deal with it.
			throw new RuntimeException(e);
		}
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			try (InputStream inputStream = connection.getInputStream();
					InputStreamReader isr = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(isr)) {
				// Wrap, wrap, wrap

				StringBuilder response = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					response.append(line);
				}
				// Here is the response body
				System.out.println(response.toString());
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	/**
	 * Third-party libraries like {@link OkHttpClient} provided some relief but
	 * still verbose and for simple tasks also complete library need to be included
	 * increasing the memory foot print also
	 */
	public String okHttp() {
		OkHttpClient okHttpClient = new OkHttpClient();
		Request request = new Request.Builder().url("https://foo.com/posts/1").build();
		Call call = okHttpClient.newCall(request);
		try (Response response = call.execute(); ResponseBody body = response.body()) {
			return body.string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Autowired
	RestTemplate restTemplate;

	/**
	 * RestTemplate just cleverly hides away all the clutter. If we start peeling
	 * layers we will see that createRequest method of
	 * {@link SimpleClientHttpRequestFactory} internally uses
	 * {@link HttpURLConnection}}
	 */
	public String restTemplate() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("https://foo.com/bar", HttpMethod.GET, entity, String.class).getBody();
	}

	/**
	 * Java 11 HTTP Client Synchronous - Send
	 * <p/>
	 * We can send the request using this default send method. This method will
	 * block the code until the response has been received:
	 */
	public String httpClientJ11() {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> response;
		try {
			response = httpClient.send(
					HttpRequest.newBuilder().uri(URI.create("https://foo.com/posts/1")).GET().build(),
					HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Java 11 HTTP Client ASynchronous - Send
	 * <p/>
	 * the same request can be sent asynchronously using the sendAsync method.
	 * Instead of blocking our code, this method will immediately return a
	 * CompletableFuture instance:
	 */
	public String httpClientJ11Async() {
		HttpClient httpClient = HttpClient.newHttpClient();
		CompletableFuture<HttpResponse<String>> futureResponse;
		try {
			futureResponse = httpClient.sendAsync(
					HttpRequest.newBuilder().uri(URI.create("https://foo.com/posts/1")).GET().build(),
					HttpResponse.BodyHandlers.ofString());
			HttpResponse<String> response = futureResponse.get();// Though this is still blocking
			return response.body();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Java 11 HTTP Client - Send Multiple non-blocking Concurrently
	 * <p/>
	 * To non dependent tasks can be completed in parallel. Getting User record and
	 * User Preferences are independent items
	 */
	public String httpClientJ11AsyncMultiple(String userId) {
		HttpClient httpClient = HttpClient.newHttpClient();
		CompletableFuture<HttpResponse<String>> userResult;
		CompletableFuture<HttpResponse<String>> preferencesResult;
		try {
			userResult = httpClient.sendAsync(getUser(userId), HttpResponse.BodyHandlers.ofString());
			preferencesResult = httpClient.sendAsync(getUserPreferences(userId), HttpResponse.BodyHandlers.ofString());
			CompletableFuture.allOf(userResult, preferencesResult); // This is still blocking
			String user = userResult.get().body();
			String preference = preferencesResult.get().body();
			String userData = user + preference;
			return userData;
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public Mono<User> getUserDetails(String userID) {
		return userService.getUser(userID)
				.zipWith(userPreferenceService.getPreferences(userID))
				.map(tuple -> {
						User user = tuple.getT1();
						user.setUserPreference(tuple.getT2());
						return user;
					});
	}

	private HttpRequest getUser(String userId) {
		return HttpRequest.newBuilder().uri(URI.create("https://foo.com/user/" + userId)).GET().build();
	}

	private HttpRequest getUserPreferences(String userId) {
		return HttpRequest.newBuilder().uri(URI.create("https://foo.com/user/" + userId)).GET().build();
	}

}

class User {

	public void setUserPreference(User t2) {
		// TODO Auto-generated method stub

	}

}
