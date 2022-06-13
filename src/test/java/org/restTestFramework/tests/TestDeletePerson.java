package org.restTestFramework.tests;

import org.restTestFramework.helpers.PersonServiceHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDeletePerson {

    public PersonServiceHelpers personServiceHelpers;

    @BeforeClass
    public void init(){
        personServiceHelpers = new PersonServiceHelpers();
    }

    @Test
    public void testDeletePerson(){

        personServiceHelpers.deletePerson(7);

    }
}

