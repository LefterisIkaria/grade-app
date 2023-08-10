package com.lefpap.gradeapp;

import com.lefpap.gradeapp.grades.Grade;
import com.lefpap.gradeapp.grades.GradeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GradeAppApplication {
	public static void main(String[] args) {

		SpringApplication.run(GradeAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(GradeRepository gradeRepository) {
		List<Grade> studentGrades = new ArrayList<>();
		studentGrades.add(new Grade("Ron Weasley", "Charms", "B"));
		studentGrades.add(new Grade("Hermione Greinger", "History of Magic", "A+"));
		studentGrades.add(new Grade("Harry Potter", "Potions", "C"));
		studentGrades.add(new Grade("John Riddle", "Dark Arts", "A+"));

		return args -> gradeRepository.saveAll(studentGrades);
	}
}
