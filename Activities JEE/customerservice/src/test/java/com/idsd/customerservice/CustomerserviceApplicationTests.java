package com.idsd.customerservice;
import com.idsd.customerservice.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomerserviceApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    private List<Long> customerIds = new ArrayList<>();
    private Long customerId;
    @BeforeEach
    @Transactional
    public void setUp() {
        String[][] customersData = {
                {"Nada Ch", "nadach@gmail.com"},
                {"Ali Ben", "aliben@gmail.com"},
                {"Sara M", "saram@gmail.com"}
        };
        for (String[] data : customersData) {
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .name(data[0])
                    .email(data[1])
                    .build();
            ResponseEntity<CustomerDTO> response = restTemplate.postForEntity("/api/customers", customerDTO, CustomerDTO.class);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertNotNull(response.getBody().getId(), "ID du client doit être non nul");
            customerIds.add(response.getBody().getId());
        }
        customerId = customerIds.get(0);
    }
    @Test
    public void testCreateCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("New Client")
                .email("newclient@gmail.com")
                .build();

        ResponseEntity<CustomerDTO> response = restTemplate.postForEntity("/api/customers", customerDTO, CustomerDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }

    @Test
    public void testGetCustomerById() {
        assertNotNull(customerId, "L'ID du client créé doit être non nul");

        ResponseEntity<CustomerDTO> response = restTemplate.getForEntity("/api/customers/" + customerId, CustomerDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "Le client récupéré ne doit pas être nul");
    }

    @Test
    public void testCreateMultipleCustomers() {
        String[][] customersData = {
                {"Amine F", "aminez@gmail.com"},
                {"Leila K", "leilak@gmail.com"},
                {"Rawen L", "rawen@gmail.com"}
        };

        for (String[] data : customersData) {
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .name(data[0])
                    .email(data[1])
                    .build();

            ResponseEntity<CustomerDTO> response = restTemplate.postForEntity("/api/customers", customerDTO, CustomerDTO.class);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertNotNull(response.getBody().getId(), "L'ID du client créé doit être non nul");
        }
    }

    @Test
    public void testGetAllCustomers() {
        ResponseEntity<CustomerDTO[]> response = restTemplate.getForEntity("/api/customers", CustomerDTO[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= customerIds.size(), "La liste des clients ne doit pas être vide");
    }
}
