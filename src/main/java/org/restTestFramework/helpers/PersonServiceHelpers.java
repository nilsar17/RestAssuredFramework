package org.restTestFramework.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import io.qameta.allure.testng.TestInstanceParameter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.restTestFramework.constants.EndPoints;
import org.restTestFramework.model.Person;
import org.restTestFramework.utils.ConfigManagers;
import org.testng.Assert;

import java.lang.reflect.Type;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class PersonServiceHelpers {
    //Fetch data from end points making requests GET/PST/GET Single/Post/Patch

    public static final String BASE_URL = ConfigManagers.getInstance().getProp("baseUrl");
    public static final String PORT = ConfigManagers.getInstance().getProp("port");
    public List<Person> personList;

    public PersonServiceHelpers(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = Integer.parseInt(PORT);
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<Person> getAllPersons(){

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get(EndPoints.GET_ALL_PERSON)
                .andReturn();

        if (response.getStatusCode() == HttpStatus.SC_OK){
            Type type = new TypeReference<List<Person>>(){}.getType();
            personList = response.as(type);
            assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"ok");
        }
        else{
            assertEquals(response.getStatusCode(),HttpStatus.SC_OK,"Not ok");
        }
        return personList;
    }

    public Response postPerson(){

        Person person = new Person();
        person.setId(7);
        person.setFirstName("TestPostToDelAgain");
        person.setLastName("PostDoneToDelAgain");
        person.setAddress("Chicago IL USA");
        person.setAge(28);
        person.setPhoneNumbers("345-428-4558");


        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(person).log().all()
                .post(EndPoints.CREATE_PERSON)
                .andReturn();

        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED,"Person Created");

        return response;
    }

    public Response patchPerson(Integer id){

        Person person = new Person();
        person.setFirstName("TestPatchOnceAgain");
        person.setLastName("LastPatchOnceAgain");
        person.setAge(27);
        person.setAddress("Chicago IL USA");
        person.setPhoneNumbers("346-457-7898");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParams("id",id)
                .when()
                .body(person)
                .patch(EndPoints.UPDATE_PERSON)
                .andReturn();

        assertTrue(response.getStatusCode() ==HttpStatus.SC_OK);
        return response;
    }

    public void deletePerson(Integer id){

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParams("id",id)
                .when()
                .delete(EndPoints.GET_SINGLE_PERSON)
                .andReturn();

        assertTrue(response.getStatusCode() == HttpStatus.SC_OK);

    }
}


