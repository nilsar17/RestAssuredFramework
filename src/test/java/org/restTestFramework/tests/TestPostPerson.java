package org.restTestFramework.tests;

import io.restassured.response.Response;
import org.restTestFramework.helpers.PersonServiceHelpers;
import org.restTestFramework.model.Person;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class TestPostPerson {

    public PersonServiceHelpers personServiceHelpers;
    public Person person;

    @BeforeClass
    void init(){
        personServiceHelpers = new PersonServiceHelpers();
    }


    @Test
    public void testPostPerson(){
        Response personResp = personServiceHelpers.postPerson();
        assertEquals(personResp.jsonPath().getString("id"),"7");
        person = personResp.as(Person.class);
        System.out.println(person.toString());

    }
}
