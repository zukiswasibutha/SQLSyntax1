package stepDefinitions;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.testng.Assert.assertEquals;

public class Employees {

    RequestSpecification httpRequest;
    Response response;
    int edit;
    int modify;
    int statusCode;
    int record;


    @Given("I retrieve the employees records entity")
    public void i_retrieve_the_employees_records_entity() {

        // API Base URL
        RestAssured.baseURI = "http://localhost:3000/";
    }

    @When("I request for an individual employee record")
    public void i_request_for_an_individual_employee_record() {

        // Build request
        httpRequest = RestAssured.given();

        // Send GET request
        response = httpRequest.get("data/");
    }

    @Then("I receive the record with status")
    public void i_receive_the_record_with_status() {

        record  = response.getStatusCode();

        System.out.println("See the response body");
        System.out.println(response.getBody().asString());
        assertEquals(record, 200);
        System.out.println("Response Code: " + record);


    }

    @When("I add an individual employee record")
    public void i_add_an_individual_employee_record() {
        Map<String, Object> description = new LinkedHashMap<>();
        description.put("name", "Marcus Mthembhu");
        description.put("email", "marcusm@example.com");
        description.put("role", "Engineer");


        httpRequest = RestAssured.given();
        response = httpRequest
                .header("Content-Type", "application/json")
                .body(description)
                .post("data/");

        System.out.println("Response Body:" + " "+ description);
        System.out.println(response.asString());
    }

    @Then("I check whether I receive the record with status")
    public void iCheckWhetherIReceiveTheRecordWithStatus() {
         statusCode = response.getStatusCode();

        System.out.println("See the response body");
        System.out.println(response.getBody().asString());
        assertEquals(statusCode, 201);
        System.out.println("Response Code: " + statusCode);
    }

    @When("I update an individual employee record")
    public void iUpdateAnIndividualEmployeeRecord() {

        Map<String, Object>  output = new HashMap<>();
        output.put("name"," Edward Mathebula");
        output.put("id", 4);


        httpRequest = RestAssured.given();
        response = httpRequest
                .header("Content-Type", "application/json")
                .body(output)
                .put ("data/3cIpbE8dd6A");

        System.out.println(response.getBody().asString());
        System.out.println("See the response body");

    }

    @Then("I receive the record with what status")
    public void iReceiveTheRecordWithWhatStatus() {
        edit = response.getStatusCode();
        assertEquals(edit , 200);
        System.out.println("Response Code: " + edit);

    }

    @When("I delete an individual employee record")
    public void iDeleteAnIndividualEmployeeRecord() {

        httpRequest = RestAssured.given();
        response = httpRequest
                .header("Content-Type", "application/json")
                .delete("data/rQK5g83K70o");

        System.out.println(response.getBody().asString());
        System.out.println("See the response body");
    }

    @Then("I delete to receive the record with status")
    public void iDeleteToReceiveTheRecordWithStatus() {
        modify = response.getStatusCode();
        assertEquals(modify , 200);
        System.out.println("Response Code: " + modify);
    }
}