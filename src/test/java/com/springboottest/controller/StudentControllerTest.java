package com.springboottest.controller;

import com.springboottest.controller.StudentController;
import com.springboottest.model.Course;
import com.springboottest.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

//SpringRunner is short hand for SpringJUnit4ClassRunner which extends BlockJUnit4ClassRunner providing the functionality to launch a Spring TestContext Framework.
@RunWith(SpringJUnit4ClassRunner.class)
//WebMvcTest annotation is used for unit testing Spring MVC application. This can be used when a test focuses only Spring MVC components.
// In this test, we want to launch only StudentController. All other controllers and mappings will not be launched when this unit test is executed.
@WebMvcTest(value = StudentController.class)
@WithMockUser
public class StudentControllerTest {

	@Autowired
	//MockMvc is the main entry point for server-side Spring MVC test support. It allows us to execute requests against the test context.
	private MockMvc mockMvc;

	@MockBean
	//MockBean is used to add mocks to a Spring ApplicationContext. A mock of studentService is created and auto-wired into the StudentController.
	private StudentService studentService;

	Course mockCourse = new Course("Course1", "Spring", "10Steps",
			Arrays.asList("Learn Maven", "Import Project", "First Example",
					"Second Example"));

	String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		//Mocking the method retrieveCourse to return the specific mockCourse when invoked
		Mockito.when(
				studentService.retrieveCourse(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);

		//Creating a Request builder to be able to execute a get request to uri
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students/Student1/courses/Course1").accept(
				MediaType.APPLICATION_JSON);

		//mockMvc is used to perform the request and return the response back.
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		//Expected JSON.
		String expected = "{id:Course1,name:Spring,description:10Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		//We are using org.skyscreamer.jsonassert.JSONAssert. This allows us to do partial asserts against a JSON String.
		// We are passing strict as false since we do not want to check for all fields in the response.
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	//A POST Service should return a status of created (201) when the resource creation is successful.
	public void createStudentCourse() throws Exception {
		Course mockCourse = new Course("1", "Smallest Number", "1",
				Arrays.asList("1", "2", "3", "4"));

		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				studentService.addCourse(Mockito.anyString(),
						Mockito.any(Course.class))).thenReturn(mockCourse);

		//Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students/Student1/courses")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/students/Student1/courses/1",
				response.getHeader(HttpHeaders.LOCATION));

	}

}
