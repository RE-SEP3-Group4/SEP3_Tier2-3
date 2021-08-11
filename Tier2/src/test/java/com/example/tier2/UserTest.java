package com.example.tier2;

import com.example.tier2.service.dto.RegisterUserDTO;
import com.example.tier2.service.dto.UpdateUserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.tier3.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testRegister() throws Exception {
        RegisterUserDTO dto = new RegisterUserDTO("a", "b", 2);
        User resp = mapper.readValue(mockMvc.perform(
                post("/users/register")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), User.class);

        assertNotNull(resp.getId());
        assertEquals(dto.getUsername(), resp.getUsername());
        assertEquals(dto.getPassword(), resp.getPassword());
        assertEquals(dto.getSecurityLevel(), resp.getSecurityLevel());
    }

    @Test
    void updateUSer() throws Exception {
        UpdateUserDTO updateDto = new UpdateUserDTO("b", "c");
        RegisterUserDTO registerDto = new RegisterUserDTO("a", "b", 2);
        User resp = mapper.readValue(mockMvc.perform(
                post("/users/register")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), User.class);

        User resp2 = mapper.readValue(mockMvc.perform(
                put("/users/{id}", resp.getId() )
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), User.class);

        assertNotNull(resp.getId());
        assertEquals(updateDto.getUsername(), resp2.getUsername());
        assertEquals(updateDto.getPassword(), resp2.getPassword());
    }
}
