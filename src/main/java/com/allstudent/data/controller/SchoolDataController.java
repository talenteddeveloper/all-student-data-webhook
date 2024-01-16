package com.allstudent.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.allstudent.data.model.SchoolData;
import com.allstudent.data.model.Student;
import com.allstudent.data.model.WebhookDetails;
import com.allstudent.data.repository.SchoolDataRepository;

@Controller
@RequestMapping(path = "/demo")
public class SchoolDataController {

	@Autowired
	private SchoolDataRepository schoolDataRepository;

	@PostMapping(path = "/addNewSchool") // Map ONLY POST Requests
	public @ResponseBody SchoolData addNewSchool(@RequestParam String schoolName) {
		SchoolData schoolData = new SchoolData();
		schoolData.setSchoolName(schoolName);
		schoolDataRepository.save(schoolData);
		return schoolData;
	}
	
	@PostMapping(path = "/addWebhookEvent/{schoolId}") // Map ONLY POST Requests
	public @ResponseBody String addWebhookEvent(@PathVariable Integer schoolId, @RequestBody WebhookDetails webhookDetails) {
		Optional<SchoolData> schoolData = schoolDataRepository.findById(schoolId);
		
		List<WebhookDetails> webhooks = new ArrayList<WebhookDetails>();
		WebhookDetails details = new WebhookDetails();
		details.setEventName(webhookDetails.getEventName());
		details.setEndPointUrl(webhookDetails.getEndPointUrl());
		
		details.setSchoolData(schoolData.get());
		webhooks.add(details);
		
		schoolData.get().setWebbhookDetails(webhooks);
		schoolDataRepository.save(schoolData.get());
		return "Webhook Added";
		
	}
	
	@PostMapping(path = "/addStudent/{schoolId}") // Map ONLY POST Requests
	public @ResponseBody String addStudent(@PathVariable Integer schoolId, @RequestBody Student reqData) {
		Optional<SchoolData> schoolData = schoolDataRepository.findById(schoolId);
		SchoolData schoolData2 = schoolData.get();
		
		List<Student> students = new ArrayList<Student>();
		Student student = new Student();
		student.setAge(reqData.getAge());
		student.setName(reqData.getName());
		student.setSchoolData(schoolData2);
		students.add(student);
		
		schoolData2.setStudents(students);
		schoolDataRepository.save(schoolData2);
		// send as webhook
		WebhookDetails webhookDetails= schoolData2.getWebbhookDetails().
				stream().filter(eventData -> (eventData.getEventName().equals("add")))
				.findFirst()
				.orElse(null);
		if(webhookDetails !=null && webhookDetails.getEndPointUrl() !=null) {
			String url = webhookDetails.getEndPointUrl();//localhost:9000/
			url+="/"+reqData.getName();
			RestTemplate restTemplate = new RestTemplate();
			String result= restTemplate.getForObject(url, String.class);
			System.out.println("result: "+result);
		}
		return "Student Added";
		
	}
}
