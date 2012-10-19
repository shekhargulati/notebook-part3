package com.openshift.notebook.web;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.openshift.notebook.core.domain.Notebook;
import com.openshift.notebook.core.domain.NotebookBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebContextLoader.class, locations = { "classpath:test-webmvc-config.xml" })
@ActiveProfiles("in-memory")
public class NotebookControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Test
	public void testCRUDNotebook() throws Exception {

		String[] tags = { "test" };
		Notebook notebook = NotebookBuilder.notebook()
				.withAuthor("test_author").withDescription("test notebook")
				.withName("test").withTags(tags).build();
		String id = UUID.randomUUID().toString();
		notebook.setId(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		MockMvc mvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
		
		String json = notebook.toJson();;
		mvc.perform(post("/notebooks").
						headers(headers)
						.body(json.getBytes()))
						.andExpect(status().isCreated());
		
		mvc.perform(get("/notebooks/{id}",id).
						headers(headers)).
						andExpect(status().isOk()).
						andExpect(content().type("application/json; charset=utf-8")).
						andExpect(content().string(json));
		
		notebook.setDescription("test notebook updated");
		
		String updatedNotebookJson = notebook.toJson();
		mvc.perform(put("/notebooks/{id}",id).headers(headers).body(updatedNotebookJson.getBytes())).
							andExpect(status().isOk()).
							andExpect(content().type("application/json")).
							andExpect(content().string(updatedNotebookJson));
		
		mvc.perform(delete("/notebooks/{id}",id).headers(headers)).andExpect(status().isOk());
		
		mvc.perform(get("/notebooks/{id}",id).
						headers(headers)).
						andExpect(status().isNotFound());

	}
}
