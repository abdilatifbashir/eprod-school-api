package com.eprod.school.controller;


import com.eprod.school.requestWrappers.StreamsWrapper;
import com.eprod.school.requestWrappers.StudentStreamWrapper;
import com.eprod.school.requestWrappers.StudentWrapper;
import com.eprod.school.requestWrappers.UniversalResponse;
import com.eprod.school.service.StreamService;
import com.eprod.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    @Autowired
    StudentService studentService;



    @PostMapping("/student")
    /**
     *auhor:abdilatif
     * create student
     */
    public ResponseEntity<UniversalResponse> createStudent(@RequestBody StudentWrapper studentWrapper){
        return studentService.createStudent(studentWrapper);

    }

    @GetMapping(value = "/stduent/list")
    public ResponseEntity<UniversalResponse> getStudentList(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        if(!page.isPresent() || !size.isPresent())
            return UniversalResponse.responseFormatter(200,400,"please include page and size",null);
        return studentService.getStudentList(page.get(),size.get());
    }

    @GetMapping(value = "/student/{id}")
    public  ResponseEntity<UniversalResponse> getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @PostMapping(value = "/student/edit/{id}")
    public  ResponseEntity<UniversalResponse> deleteStudent(@PathVariable int id){
        return studentService. deleteStudent(id);
    }

    /**
     *
     * @param id
     * @param studentWrapper
     * @return
     */
    @PatchMapping(value = "/student/delete/{id}")
    public  ResponseEntity<UniversalResponse> editStudent(@PathVariable Long id,@RequestBody StudentWrapper studentWrapper){
        return studentService.editStudent(id,studentWrapper);
    }

    @PostMapping(value = "/stream/add-student")
    public ResponseEntity<UniversalResponse> addStudentToStream(@RequestBody StudentStreamWrapper studentStreamWrapper){
        return studentService.addStudentToStream(studentStreamWrapper);

    }

    @GetMapping(value = "/stream/get-student/{streamId}")
    public ResponseEntity<UniversalResponse> getStudentStreamList(@PathVariable("streamId") int streamId,@RequestParam Optional<Integer> page,
                                                              @RequestParam Optional<Integer> size){
        return studentService.getStudentStreamList( streamId,page.get(),size.get());
    }


}
