package stockman;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import stockman.controller.SupplyController;

@RunWith(MockitoJUnitRunner.class)
public class SupplyControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	private SupplyController controller;

	@Mock
	private FavoriteService service;
	
	@Before
	public void setup() {
		controller = new SupplyController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void shouldGetAllSupplies() throws Exception{
		mockMvc.perform(get("/api/supply")).andExpect(MockMvcResultMatchers.content());
	}
	
	@Test
	public void 
}
