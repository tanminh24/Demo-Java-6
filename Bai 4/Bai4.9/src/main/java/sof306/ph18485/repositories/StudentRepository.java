package sof306.ph18485.repositories;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import sof306.ph18485.beans.StudentMap;
import sof306.ph18485.beans.Student;

@Repository
public class StudentRepository {

	private RestTemplate rest = new RestTemplate();
	private String url = "https://rest-api-20613-default-rtdb.asia-southeast1.firebasedatabase.app/students.json";

	private String getUrl(String key) {
		return url.replace(".json", "/" + key + ".json");
	}

	public StudentMap findAll() {
		return rest.getForObject(url, StudentMap.class);
	}

	public Student findByKey(String key) {
		return rest.getForObject(this.getUrl(key), Student.class);
	}

	public String create(Student student) {
		HttpEntity<Student> entity = new HttpEntity<>(student);
		JsonNode resp = rest.postForObject(url, entity, JsonNode.class);
		return resp.get("name").asText();
	}

	public Student update(String key, Student student) {
		HttpEntity<Student> entity = new HttpEntity<>(student);
		rest.put(this.getUrl(key), entity);
		return student;
	}

	public void delete(String key) {
		rest.delete(this.getUrl(key));
	}
}
