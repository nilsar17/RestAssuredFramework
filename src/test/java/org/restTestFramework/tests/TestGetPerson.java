package org.restTestFramework.tests;

import org.restTestFramework.helpers.PersonServiceHelpers;
import org.restTestFramework.model.Person;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;

public class TestGetPerson {

    public PersonServiceHelpers personServiceHelpers;

    @BeforeClass
    public void init(){
        personServiceHelpers = new PersonServiceHelpers();
    }

    @Test
    public void testGetAllPerson(){

        List<Person> personList = personServiceHelpers.getAllPersons();
        assertNotNull(personList,"Person List Not Empty");
        for (Person p : personList) {
            System.out.println(p.toString());
        }
    }

}
