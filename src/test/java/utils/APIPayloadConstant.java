package utils;

import org.json.JSONObject;

public class APIPayloadConstant {

    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Fudas\",\n" +
                "  \"emp_lastname\": \"Asadif\",\n" +
                "  \"emp_middle_name\": \"Miko\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2000-01-14\",\n" +
                "  \"emp_status\": \"confirmed\",\n" +
                "  \"emp_job_title\": \"QA Engineer\"\n" +
                "}";

        return createEmployeePayload;
    }

    public static String createEmployeeJsonBody() {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Fudas");
        obj.put("emp_lastname", "Asadif");
        obj.put("emp_middle_name", "Miko");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2000-01-14");
        obj.put("emp_status", "confirmed");
        obj.put("emp_job_title", "QA Engineer");

        return obj.toString();
    }

    public static String adminPayload() {
        String adminPayload = "{\n" +
                "  \"email\": \"batch14HanJo@test.com\",\n" +
                "  \"password\": \"Test@123\"\n" +
                "}";

        return adminPayload;
    }
}
