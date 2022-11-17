package sof306.ph18485.repositories;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import sof306.ph18485.beans.StudentMap;
import sof306.ph18485.beans.Student;
import sof306.ph18485.rest.Rest;

public class StudentRepository {
    
    private ObjectMapper mapper = new ObjectMapper();
    
    public StudentMap findAll() {
        JsonNode resp = Rest.get("/students");
        return mapper.convertValue(resp, StudentMap.class);
    }
    
    public Student findByKey(String key) {
        JsonNode resp = Rest.get("/students/" + key);
        return mapper.convertValue(resp, Student.class);
    }
    
    public String create(Student student) {
        JsonNode resp = Rest.post("/students", student);
        return resp.get("name").asText();
    }
    
    public Student update(String key, Student student) {
        JsonNode resp = Rest.put("/students/" + key, student);
        return mapper.convertValue(resp, Student.class);
    }
    
    public void delete(String key) {
        Rest.delete("/students/" + key);
    }
}
