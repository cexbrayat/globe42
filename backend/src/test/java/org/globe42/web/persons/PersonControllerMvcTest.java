package org.globe42.web.persons;

import static org.globe42.test.JsonTestUtil.OBJECT_MAPPER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.globe42.dao.PersonDao;
import org.globe42.domain.Person;
import org.globe42.test.GlobeMvcTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * MVC test for {@link PersonController}
 * @author JB Nizet
 */
@RunWith(SpringRunner.class)
@GlobeMvcTest(PersonController.class)
public class PersonControllerMvcTest {

    @MockBean
    private PersonDao mockPersonDao;

    @Autowired
    private MockMvc mvc;

    private Person person;

    @Before
    public void prepare() {
        person = new Person(1L);
        person.setAdherent(true);
        person.setEntryDate(LocalDate.of(2017, 5, 21));
    }

    @Test
    public void shouldList() throws Exception {
        when(mockPersonDao.findAll()).thenReturn(Collections.singletonList(person));

        mvc.perform(get("/api/persons"))
           .andExpect(jsonPath("$[0].id").value(1))
           .andExpect(jsonPath("$[0].entryDate").value("2017-05-21"))
           .andExpect(jsonPath("$[0].isAdherent").value(true));
    }

    @Test
    public void shouldGet() throws Exception {
        when(mockPersonDao.findById(person.getId())).thenReturn(Optional.of(person));

        mvc.perform(get("/api/persons/{personId}", person.getId()))
           .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void should404IfPersonNotFound() throws Exception {
        when(mockPersonDao.findById(person.getId())).thenReturn(Optional.empty());

        mvc.perform(get("/api/persons/{personId}", person.getId()))
           .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreate() throws Exception {
        when(mockPersonDao.save(any(Person.class))).thenReturn(person);

        mvc.perform(post("/api/persons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(OBJECT_MAPPER.writeValueAsBytes(PersonControllerTest.createCommand())))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldUpdate() throws Exception {
        when(mockPersonDao.findById(person.getId())).thenReturn(Optional.of(person));

        mvc.perform(put("/api/persons/{personId}", person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(PersonControllerTest.createCommand())))
           .andExpect(status().isNoContent());
    }
}
