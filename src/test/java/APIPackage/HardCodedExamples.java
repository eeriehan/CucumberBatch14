package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//To change the order of execution we use fix method order(or it will execute top to bottom)
//this method sorters will execute my method in ascending order(alphabet)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    //base URI  - base URL
    //end then using when keyword, we will send the end point

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //we need to perform CRUD operations
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJpYXQiOjE2NzQwODg4NjIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDEzMjA2MiwidXNlcklkIjoiNDc1NSJ9." +
            "AYPzYgiqLWpFQKUbiwxTPP25KoszA6ZD1HjfHeYOxKM";
    static String employee_id;

    @Test
    public void b_getOneEmployee() {
        // prepare the request
        // to prepare the request, we use request specification
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", employee_id);

        // to hit the end point / to make the request which will return response
        Response response = request.when().get("/getOneEmployee.php");

        //System.out.println(response.asString());
        response.prettyPrint();

        // verifying the status code
        response.then().assertThat().statusCode(200);

        //using jsonPath() method, we are extracting the value from the response body
        String firstName = response.jsonPath().getString("employee.emp_firstname");
        System.out.println(firstName);

        //first way of assertion
        Assert.assertEquals(firstName, "Fudas");

        //second way of assertion to verify the value in response body using Hamcrest matchers
        response.then().assertThat().body("employee.emp_firstname", equalTo("Fudas"));
    }


    @Test
    public void a_createEmployee(){
        RequestSpecification request = given().header("Authorization", token)
            .header("Content-Type", "application/json").
            body("{\n" +
                    "  \"emp_firstname\": \"Fudas\",\n" +
                    "  \"emp_lastname\": \"Asadif\",\n" +
                    "  \"emp_middle_name\": \"Miko\",\n" +
                    "  \"emp_gender\": \"M\",\n" +
                    "  \"emp_birthday\": \"2000-01-14\",\n" +
                    "  \"emp_status\": \"confirmed\",\n" +
                    "  \"emp_job_title\": \"QA Engineer\"\n" +
                    "}");

    Response response = request.when().post("/createEmployee.php");
    response.prettyPrint();
    //verifying the status code which is 201
    response.then().assertThat().statusCode(201);
    //getting the employee id from the response and use it as static one
    employee_id = response.jsonPath().getString("Employee.employee_id");
    System.out.println(employee_id);
    response.then().assertThat().body("Employee.emp_lastname", equalTo("Asadif"));
    response.then().assertThat().body("Employee.emp_middle_name", equalTo("Miko"));
    //verify console header
    response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

}
    @Test
    public void c_updateEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json").
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"HanJoon\",\n" +
                        "  \"emp_lastname\": \"Cho\",\n" +
                        "  \"emp_middle_name\": \"eerie\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1986-10-10\",\n" +
                        "  \"emp_status\": \"probation\",\n" +
                        "  \"emp_job_title\": \"manager\"\n" +
                        "}");

        Response response = request.when().put("/updateEmployee.php");

        //verification
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));

    }

    @Test
    public void d_getUpdatedEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", employee_id);

        //to hit the end point/ to make the request which will return response
        Response response = request.when().get("/getOneEmployee.php");

        // System.out.println(response.asString());
        response.prettyPrint();
        //verifying the status code
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("employee.emp_job_title", equalTo("Manager"));
    }




}
