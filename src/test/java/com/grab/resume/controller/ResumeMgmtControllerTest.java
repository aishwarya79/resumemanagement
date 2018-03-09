package com.grab.resume.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grab.resume.dto.response.ResumeMgmtResponse;
import com.grab.resume.spring.ResumeApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ResumeApplication.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ResumeMgmtControllerTest {

	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@After
	public void tearDown() {
	}

	protected ResultActions getResponseResultFor(String testUrl, String json) throws Exception {
		return this.mockMvc.perform(
				post(testUrl).content(json).contentType(MediaType.APPLICATION_JSON).accept("application/json"));
	}

	protected ResultActions getResponseResultForGetRequest(String testUrl) throws Exception {
		return this.mockMvc.perform(get(testUrl));
	}

	@Test
	public void testUploadResume() throws Exception {
		String json = "{\"name\": \"Alexander Lenon\",\"currentJobTitle\": \"VP of Marketing\",\"jobDesc\": \"APAC Branding and Marketing\",\"currentCompany\": \"PayPal\"}";
		ResultActions resultActions = uploadResumeDetails(json);
		resultActions.andExpect((status().isOk()));
		resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				String str = result.getResponse().getContentAsString();
				ResumeMgmtResponse response = OBJECT_MAPPER.readValue(str, ResumeMgmtResponse.class);
				assertNotNull(response);
				assertEquals(true, response.getSuccess());
			}
		});
	}

	@Test
	public void testUploadResumeNoName() throws Exception {

		String json = "{\"currentJobTitle\": \"VP of Marketing\",\"jobDesc\": \"APAC Branding and Marketing\",\"currentCompany\": \"PayPal\"}";
		ResultActions resultActions = uploadResumeDetails(json);
		resultActions.andExpect((status().isBadRequest()));
	}

	@Test
	public void testgetResumeById() throws Exception {
		String json = "{\"name\": \"Alexander Lenon\",\"currentJobTitle\": \"VP of Marketing\",\"jobDesc\": \"APAC Branding and Marketing\",\"currentCompany\": \"PayPal\"}";
		ResultActions resultActions = uploadResumeDetails(json);
		resultActions.andExpect((status().isOk()));
		resultActions.andDo(new ResultHandler() {
			@Override
			public void handle(MvcResult result) throws Exception {
				String str = result.getResponse().getContentAsString();
				ResumeMgmtResponse response = OBJECT_MAPPER.readValue(str, ResumeMgmtResponse.class);
				long resumeId = response.getResumeId();
				assertNotNull(response);
				assertEquals(true, response.getSuccess());

				String getResumeURI = "/resumemanagement/api/v1/getResume/" + resumeId;
				ResultActions resultActions2 = getResponseResultForGetRequest(getResumeURI);
				resultActions2.andExpect((status().isOk()));
				resultActions2.andDo(new ResultHandler() {
					@Override
					public void handle(MvcResult result2) throws Exception {
						String str = result2.getResponse().getContentAsString();
						ResumeMgmtResponse response = OBJECT_MAPPER.readValue(str, ResumeMgmtResponse.class);
						assertNotNull(response);
						assertEquals(true, response.getSuccess());
						System.out.println(response.getResume().getJobDesc());
					}
				});

			}
		});
	}

	@Test
	public void testGetResumeByNonExistingId() throws Exception {

		String getResumeURI = "/resumemanagement/api/v1/getResume/" + 11111;
		ResultActions resultActions2 = getResponseResultForGetRequest(getResumeURI);
		resultActions2.andExpect((status().is5xxServerError()));

	}

	private ResultActions uploadResumeDetails(String postData) throws Exception {
		String uploadResumeURI = "/resumemanagement/api/v1/uploadResumeDetails";
		ResultActions resultActions = getResponseResultFor(uploadResumeURI, postData);
		return resultActions;
	}

}
