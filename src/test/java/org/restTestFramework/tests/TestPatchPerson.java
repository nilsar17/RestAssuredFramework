package org.restTestFramework.tests;

import io.restassured.response.Response;
import org.restTestFramework.helpers.PersonServiceHelpers;
import org.restTestFramework.model.Person;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestPatchPerson {

    public PersonServiceHelpers personServiceHelpers;
    public Person person;
    @BeforeClass
    public void init(){
        personServiceHelpers = new PersonServiceHelpers();
    }

    @Test
    public void testPatchPerson(){
        Response personResp = personServiceHelpers.patchPerson(4);
        person = personResp.as(Person.class);
        assertEquals(person.getId(),4);
        System.out.println(person.toString());
    }

}
