package gzaesthetics.com.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import gzaesthetics.com.data.model.Treatment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TreatmentControllerIntegrationTest {

	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void getAllTreatmentsTest() throws Exception{
		String listOfTreatsAsJSON = this.mapper.writeValueAsString(List.of(
				new Treatment(1, "wax", "remove...", 45.00, 30),
				new Treatment(2, "mask","clean",60.00,25)
				));
		RequestBuilder request = get("/treatment");
		ResultMatcher status =MockMvcResultMatchers.status().isOk();
		ResultMatcher content =MockMvcResultMatchers.content().json(listOfTreatsAsJSON);
		
		this.mvc.perform(request).andExpect(status).andExpect(content);
	}
	@Test
	void createTreatmentTest() throws Exception {
		String testTreatmentAsJson= this.mapper.writeValueAsString(new Treatment(null, "massage","full body...", 90.00, 60));
		String testTreatmentAsJsonResponse = this.mapper.writeValueAsString(new Treatment(3, "massage","full body...", 90.00, 60));
	
		RequestBuilder request = post("/treatment/create").contentType(MediaType.APPLICATION_JSON).content(testTreatmentAsJson);
		ResultMatcher status= MockMvcResultMatchers.status().isCreated();
		ResultMatcher content= MockMvcResultMatchers.content().json(testTreatmentAsJsonResponse);
		
		this.mvc.perform(request).andExpect(status).andExpect(content);
	}
	@Test
	void deleteTreatmentTest() throws Exception  {
		this.mvc.perform(delete("/treatment/delete/1")).andExpect(status().isOk());
	}
	@Test
	void updateTreatmentTest() throws Exception {
		String updateTreatmentAsJson= this.mapper.writeValueAsString(new Treatment(1, "leg massage","leg...", 90.00, 60));
	
	RequestBuilder req = put("/treatment/update/1").contentType(MediaType.APPLICATION_JSON).content(updateTreatmentAsJson);
	ResultMatcher status =status().isAccepted();
	ResultMatcher content =MockMvcResultMatchers.content().json(updateTreatmentAsJson);
	
	this.mvc.perform(req).andExpect(status).andExpect(content);
	
	}
}
