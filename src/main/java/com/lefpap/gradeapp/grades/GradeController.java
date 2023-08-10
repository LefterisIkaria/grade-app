package com.lefpap.gradeapp.grades;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class GradeController {

    GradeRepository gradeRepository;
    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        List<Grade> allGrades = gradeRepository.findAll();
        model.addAttribute("grades", allGrades);
        return "grades";
    }

    @PostMapping("/grades")
    public String saveOrEditGrade(@Valid Grade grade, BindingResult result, @RequestParam(required = false) Long id) {
        if (result.hasErrors()) {
            return "form";
        }

        Optional<Grade> gradeOptional = Optional.of(grade);
        if (Objects.nonNull(id)) {
            gradeOptional = gradeRepository.findById(id);
        }
        gradeOptional.ifPresent(foundGrade -> gradeRepository.save(grade));
        return "redirect:/grades";
    }

    @GetMapping("/grades/form")
    public String getGradeForm(Model model, @RequestParam(required = false) Long id) {
        Optional<Grade> gradeOptional = Optional.empty();
        if (Objects.nonNull(id)) {
            gradeOptional = gradeRepository.findById(id);
        }
        model.addAttribute("grade", gradeOptional.orElse(new Grade()));
        return "form";
    }
}
