package org.awesley.samples;

import static org.junit.Assert.*;

import org.awesley.shoppinglist.resources.models.ShoppingList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CxfServiceSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingListTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void canLoadShoppingList() {
		ResponseEntity<ShoppingList> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/services/shoppingList/1", ShoppingList.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

}
