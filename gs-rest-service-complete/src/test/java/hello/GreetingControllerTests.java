/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import hello.com.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class GreetingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void errorTestAccountParameter() throws Exception {

    	LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    	requestParams.add("account", "test1");
    	requestParams.add("type", "small");
    	requestParams.add("id", "1");

    	this.mockMvc.perform(get("/greeting")
    	.params(requestParams))
    	.andExpect(jsonPath("$.errorMessage").value("Validation error: Account parameter invalid."))
    	.andExpect(status().isPreconditionFailed());
    	//.andDo(print());
    }

    @Test
    public void errorTestTypeParameter() throws Exception {

    	LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    	requestParams.add("account", "personal");
    	requestParams.add("type", "test2");
    	requestParams.add("id", "1");

    	this.mockMvc.perform(get("/greeting")
    	.params(requestParams))
    	.andExpect(status().isPreconditionFailed())
    	.andExpect(jsonPath("$.errorMessage").value("Validation error: Type parameter invalid."));
    	//.andDo(print());
    }

    @Test
    public void errorTestIdParameter() throws Exception {

    	LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    	requestParams.add("account", "personal");
    	requestParams.add("type", null);
    	requestParams.add("id", null);

    	this.mockMvc.perform(get("/greeting")
    	.params(requestParams))
    	.andExpect(jsonPath("$.errorMessage").value("Validation error: type and id null."))
    	.andExpect(status().isPreconditionFailed());
    	//.andDo(print());
    }

    @Test
    public void errorTestBusinessSmall() throws Exception {

    	LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    	requestParams.add("account", "business");
    	requestParams.add("type", "small");
    	requestParams.add("id", null);

    	this.mockMvc.perform(get("/greeting")
    	.params(requestParams))
    	.andExpect(jsonPath("$.errorMessage").value("Path not implemented."))
    	.andExpect(status().isInternalServerError());
    	//.andDo(print());
    }

    @Test
    public void messagesTest() throws Exception {
    	LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
    	requestParams.add("account", "personal");
    	requestParams.add("type", "small");
    	requestParams.add("id", "123");

    	this.mockMvc.perform(get("/greeting")
    	.params(requestParams))
    	.andExpect(status().isOk())
    	.andExpect(content().string("Hi, userId 123"));
    	//.andDo(print());
    }

}
