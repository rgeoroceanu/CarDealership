package rgeoroceanu.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;

import rgeoroceanu.BaseTest;

/**
 * Test Spring Data Rest API endpoints.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class ApiTest extends BaseTest {
	
	@Value("${local.server.port}")
	private int port;
	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers =  new HttpHeaders();
	{
		String auth = "testuser:admin";
	    headers.add("Authorization", "Basic " +  new String(Base64Utils.encode(auth.getBytes())));
	}
	
	@Test
	public void testUnauthorized() {
		final ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:{port}/api/", String.class, port);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	public void testFindAllCars() {
		final ResponseEntity<String> response = restTemplate.exchange("http://localhost:{port}/api/cars", 
				HttpMethod.GET, new HttpEntity<String>(headers), String.class, port);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).contains("BMW");
		assertThat(response.getBody()).contains("LANDROVER");
	}
	
	@Test
	public void testFindCarNotFound() {
		final ResponseEntity<String> response = restTemplate.exchange("http://localhost:{port}/api/cars/{id}", 
				HttpMethod.GET, new HttpEntity<String>(headers), String.class, port, 44);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testFindCarById() {
		final ResponseEntity<String> response = restTemplate.exchange("http://localhost:{port}/api/cars/{id}", 
				HttpMethod.GET, new HttpEntity<String>(headers), String.class, port, 2);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).contains("LANDROVER");
		assertThat(response.getBody()).doesNotContain("BMW");
	}
	
	@Test
	public void testFindAllDealerships() {
		final ResponseEntity<String> response = restTemplate.exchange("http://localhost:{port}/api/dealerships", 
				HttpMethod.GET, new HttpEntity<String>(headers), String.class, port);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).contains("Test Dealership");
		assertThat(response.getBody()).contains("Munich");
	}
	
	@Test
	public void testFindDealershipById() {
		final ResponseEntity<String> response = restTemplate.exchange("http://localhost:{port}/api/dealerships/{id}", 
				HttpMethod.GET, new HttpEntity<String>(headers), String.class, port, 1);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).contains("Test Dealership");
		assertThat(response.getBody()).contains("Munich");
	}
}
