package calculator;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VacationPayController.class)
public class VacationPayControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void calculate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/calculacte")
                        .param("salary", "50000")
                        .param("days", "40")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is(68259.38)));
    }

    @Test
    public void calculateWithDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/calculacte/dates")
                        .param("salary", "50000")
                        .param("date1", "2022-05-01")
                        .param("date2", "2022-05-10")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.content", is(10238.908)));
    }
}