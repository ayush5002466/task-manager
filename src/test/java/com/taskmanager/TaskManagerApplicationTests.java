package com.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskManagerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTask_shouldReturnCreatedTask() throws Exception {

        mockMvc.perform(
                post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Test task",
                                    "completed": false
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test task"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void getTasks_shouldReturnTasks() throws Exception {

        mockMvc.perform(
                get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createTask_withBlankTitle_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(
                post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "",
                                    "completed": false
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Title is required"));
    }

    @Test
    void getTask_withUnknownId_shouldReturnNotFound() throws Exception {

        mockMvc.perform(
                get("/tasks/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value(
                        "Task not found with id: 999999"));
    }

    @Test
    void updateTask_shouldReturnUpdatedTask() throws Exception {

        String response = mockMvc.perform(
                post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Original task",
                                    "completed": false
                                }
                                """))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = response.replaceAll(
                ".*\"id\":(\\d+).*",
                "$1");

        mockMvc.perform(
                put("/tasks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Updated task",
                                    "completed": true
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated task"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void deleteTask_shouldDeleteTask() throws Exception {

        String response = mockMvc.perform(
                post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Task to delete",
                                    "completed": false
                                }
                                """))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = response.replaceAll(
                ".*\"id\":(\\d+).*",
                "$1");

        mockMvc.perform(
                delete("/tasks/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(
                get("/tasks/" + id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value(
                        "Task not found with id: " + id));
    }
}