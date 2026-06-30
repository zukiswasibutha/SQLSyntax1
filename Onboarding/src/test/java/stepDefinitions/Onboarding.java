package stepDefinitions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEquals;
public class Onboarding {

    RequestSpecification httpRequest;
    Response response;
    int edit;
    int statusCode;
    int record;
    int description;


    @Given("I retrieve the employees records entity")
    public void i_retrieve_the_employees_records_entity() {

        // API Base URL
        RestAssured.baseURI = "https://erp-uat.sandbox.api.ezra360.com";

    }

    @When("I request for an individual employee record")
    public void i_request_for_an_individual_employee_record() {

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("entityName", "Employee");
        requestBody.put("requestName", "GetEmployeeDetails");
        requestBody.put("recordId", "c2542273-6ba8-4c36-ac30-7dd18b3263d2");

        httpRequest = RestAssured.given();
        response = httpRequest
                .header("api-key", "9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .contentType("application/json")
                .body(requestBody)
                .post("/api/v1.0/entities/ExecuteRequest");

    }

    @Then("I receive the record with status")
    public void i_receive_the_record_with_status() {

        record  = response.getStatusCode();

        System.out.println("See the response body");
        System.out.println(response.getBody().asString());
        assertEquals(record, 200);
        System.out.println("Response Code: " + record);
        response.then()
                .statusCode(200)
                .body("isSuccess", equalTo(true));

    }

    @When("I add an individual employee record")
    public void iAddAnIndividualEmployeeRecord() throws IOException {

        // Main request variables
        String entityName = "Employee";
        String requestName = "UpdateEmployeeProfile";
        String recordId = "c2542273-6ba8-4c36-ac30-7dd18b3263d2";
        int profileStep = 7;


        // Document variables
        int documentTypeId = 1105;
        String documentName = "New.jpg";
        String fileExtension = "jpg";
        String versionNumber = "1.0.0";


        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\ZukiswaSibutha\\OneDrive - Xiquel Group\\Pictures\\Screenshots\\EzraMe\\2026 Prod\\New.jpg"));
        String fileContent = Base64.getEncoder().encodeToString(fileBytes);




// Document object
        Map<String, Object> document = new HashMap<>();
        document.put("documentTypeId", documentTypeId);
        document.put("name", documentName);
        document.put("FileExtension", fileExtension);
        document.put("versionNumber", versionNumber);
        document.put("fileContent", fileContent);

// documents array
        List<Map<String, Object>> documents = new ArrayList<>();
        documents.add(document);

// Add to request body

// inputParameters object
        Map<String, Object> inputParamters = new HashMap<>();
        inputParamters.put("profileStep", profileStep);
        inputParamters.put("documents", documents);

// Main request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("entityName", entityName);
        requestBody.put("requestName", requestName);
        requestBody.put("recordId", recordId);
        requestBody.put("inputParamters", inputParamters);

        response = RestAssured.given()
                .header("api-key", "9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1.0/entities/ExecuteRequest");

    }

    @Then("I check whether I receive the record with status")
    public void iCheckWhetherIReceiveTheRecordWithStatus() {


        statusCode = response.getStatusCode();

        System.out.println("See the response body");
        System.out.println(response.getBody().asString());
        assertEquals(statusCode, 200);
        System.out.println("Response Code: " + statusCode);

    }

    @When("I update an individual employee record")
    public void iUpdateAnIndividualEmployeeRecord() throws IOException {

        // inputParameters
        Map<String, Object> inputParamters = new HashMap<>();
        inputParamters.put("profileStep",7);

        // Main request body
        Map<String, Object> patchBody = new HashMap<>();
        patchBody.put("entityName","Employee");
        patchBody.put("requestName", "GetOnboardingDetails");
        patchBody.put("recordId", "c2542273-6ba8-4c36-ac30-7dd18b3263d2");
        patchBody.put("inputParamters", inputParamters);


        response = RestAssured.given()
                .header("api-key", "9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .contentType("application/json")
                .body(patchBody)
                .when()
                .post("/api/v1.0/entities/ExecuteRequest");

        System.out.println(response.getBody().asPrettyString());
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
                .header("api-key","9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .patch("/api/v1.0/entities/ExecuteRequest/c2542273-6ba8-4c36-ac30-7dd18b3263d2");


        System.out.println(response.getBody().asString());
        System.out.println("See the response body");

    }

    @Then("I delete to receive the record with status")
    public void iDeleteToReceiveTheRecordWithStatus() {

        description = response.getStatusCode();
        assertEquals(description , 200);
        System.out.println("Response Code: " + description);

    }

    @When("I request for a wrong individual employee record")
    public void iRequestForAWrongIndividualEmployeeRecord() {

        Map<String,Object> requestBody = new HashMap<>();
            requestBody.put("entityName", "Employee");
            requestBody.put("requestName", "GetEmployeeDetails");
            requestBody.put("recordId", "c2542273-6ba8-4c36-ac30-7dd18b3263d0");

        response = RestAssured.given()
                .header("api-key", "9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1.0/entities/ExecuteRequest");


    }

    @When("I update an individual employee document")
    public void iUpdateAnIndividualEmployeeDocument() throws IOException {

        // Main request variables
        String entityName = "Employee";
        String requestName = "UpdateEmployeeProfile";
        String recordId = "c2542273-6ba8-4c36-ac30-7dd18b3263d2";
        int profileStep = 7;


        // Document variables
        int documentTypeId = 1105;
        String documentName = "New.pdf";
        String fileExtension = "pdf";
        String versionNumber = "1.0.0";


        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\ZukiswaSibutha\\OneDrive - Xiquel Group\\Pictures\\Screenshots\\EzraMe\\2026 Prod\\New.pdf"));
        String fileContent = Base64.getEncoder().encodeToString(fileBytes);




// Document object
        Map<String, Object> document = new HashMap<>();
        document.put("documentTypeId", documentTypeId);
        document.put("name", documentName);
        document.put("FileExtension", fileExtension);
        document.put("versionNumber", versionNumber);
        document.put("fileContent", fileContent);


// documents array
        List<Map<String, Object>> documents = new ArrayList<>();
        documents.add(document);

// Add to request body

// inputParameters object
        Map<String, Object> inputParameters = new HashMap<>();
        inputParameters.put("profileStep", profileStep);
        inputParameters.put("documents", documents);

// Main request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("entityName", entityName);
        requestBody.put("requestName", requestName);
        requestBody.put("recordId", recordId);
        requestBody.put("inputParameters", inputParameters);

        response = RestAssured.given()
                .header("api-key", "9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1.0/entities/ExecuteRequest");

    }

    @Then("I update a wrong individual employee record")
    public void iUpdateAWrongIndividualEmployeeRecord() {

        Map<String, Object> inputParamters = new HashMap<>();
        inputParamters.put("profileStep", 0);

        // Main request body
        Map<String, Object> patchBody = new HashMap<>();
        patchBody.put("entityName","Employee");
        patchBody.put("requestName", "GetOnboardingDetails");
        patchBody.put("recordId", "c2542273-6ba8-4c36-ac30-7dd18b3263d2");
        patchBody.put("inputParamters", inputParamters);


        response = RestAssured.given()
                .header("api-key", "9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .contentType("application/json")
                .body(patchBody)
                .when()
                .post("/api/v1.0/entities/ExecuteRequest");

        System.out.println(response.getBody().asPrettyString());
        System.out.println("See the response body");


    }

    @When("I update a wrong individual employee document")
    public void iUpdateAWrongIndividualEmployeeDocument() throws IOException {


        String entityName = "Employee";
        String requestName = "UpdateEmployeeProfile";
        String recordId = "c2542273-6ba8-4c36-ac30-7dd18b3263d2";
        int profileStep = 7;


        // Document variables
        int documentTypeId = 11100;
        String documentName = "New.pdf";
        String fileExtension = "pdf";
        String versionNumber = "1.0.0";


        byte[] fileBytes = Files.readAllBytes(Paths.get("C:\\Users\\ZukiswaSibutha\\OneDrive - Xiquel Group\\Pictures\\Screenshots\\EzraMe\\2026 Prod\\New.jpg"));
        String fileContent = Base64.getEncoder().encodeToString(fileBytes);




// Document object
        Map<String, Object> document = new HashMap<>();
        document.put("documentTypeId", documentTypeId);
        document.put("name", documentName);
        document.put("FileExtension", fileExtension);
        document.put("versionNumber", versionNumber);
        document.put("fileContent", fileContent);


// documents array
        List<Map<String, Object>> documents = new ArrayList<>();
        documents.add(document);

// Add to request body

// inputParameters object
        Map<String, Object> inputParameters = new HashMap<>();
        inputParameters.put("profileStep", profileStep);
        inputParameters.put("documents", documents);

// Main request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("entityName", entityName);
        requestBody.put("requestName", requestName);
        requestBody.put("recordId", recordId);
        requestBody.put("inputParameters", inputParameters);

        response = RestAssured.given()
                .header("api-key", "9KTEk.mpHfqw9vhj4StMf869lHgxcvs2rgb1yckiqqs9umr2h")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1.0/entities/ExecuteRequest");

    }
}
