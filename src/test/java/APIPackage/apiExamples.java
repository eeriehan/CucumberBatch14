package APIPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static io.restassured.RestAssured.*;

public class apiExamples {

    //    initialise the Base URI
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQ2OTE0MTQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDczNDYxNCwidXNlcklkIjoiNDc1NSJ9.5Vo0XA9nMYdmpcSob2nMzKVye_5AXz-oVCCEY7g2NA0";

    @Test
    public void createEmployee() {
        //prepare the request
        RequestSpecification request = given().headers("Content-Type", "application/json").headers("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"MR\",\n" +
                        "  \"emp_lastname\": \"Jaay\",\n" +
                        "  \"emp_middle_name\": \"BEEE\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2023-01-20\",\n" +
                        "  \"emp_status\": \"Unemploy\",\n" +
                        "  \"emp_job_title\": \"noEmployee\"\n" +
                        "}");
//         send the request and get response
        Response resp = request.when().post("/createEmployee.php");
//         print the response
        resp.prettyPrint();
//extract the first name from the response

        String empFname = resp.jsonPath().getString("Employee.emp_firstname");
        System.out.println(empFname);

//         assert that the first name is MR
       // Assert.assertEquals(empFname, "MR");
    }

    //     write the request to get all the employeess and print the data on console
    @Test
    public void getAllEmployees() {
        RequestSpecification request =
                given().headers("Content-Type", "application/json").headers("Authorization", token);

        Response resp = request.when().get("/getAllEmployees.php");

        System.out.println(resp.asString());
    }

    @Test
    public void getJobTitle() {

        RequestSpecification request = given().headers("Content-Type", "application/json").headers("Authorization", token);

        Response response = request.when().get("/jobTitle.php");

        String responseBody = response.jsonPath().getString("Jobs[0].id");


//          response.prettyPrint();
//          print all the job titles only from the response
//          check the size of array
        String array = response.jsonPath().getString("Jobs");
//         homework
//          find the size of the json array
        JsonPath jsonPath = new JsonPath(response.asString());
        int size = jsonPath.getInt("Jobs.size");
        System.out.println(size);

/*        jobs=resp.jsonPath().getList("Jobs");

        for (int i = 0; i< jobs.size() ; i++) {
            String x = resp.jsonPath().getString("Jobs[" + i + "].job");
            System.out.println(x);
        }*/

        for (int i = 0; i < 50; i++) {
            String x = response.jsonPath().getString("Jobs[" + i + "].job");
            System.out.println(x);

        }
/*

        //declare the parser to convert the string body to the json object
        JsonObject jsonData= new JsonParser().parse(body).getAsJsonObject();

        JsonElement valueOfKeyJobs = jsonData.get("Jobs");

        JsonArray arrayData = valueOfKeyJobs.getAsJsonArray();

        System.out.println(arrayData.size());
        JsonElement data0 = arrayData.get(0);

        JsonObject dataOBJ = data0.getAsJsonObject();

        JsonElement id = dataOBJ.get("id");
        System.out.println(id);

    }
*/

    }
}
