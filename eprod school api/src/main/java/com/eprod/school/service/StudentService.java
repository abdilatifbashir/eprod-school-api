package com.eprod.school.service;
import com.eprod.school.model.FormStreamEntity;
import com.eprod.school.model.StudentEntity;
import com.eprod.school.model.StudentStreamEntity;
import com.eprod.school.repository.FormStreamRepository;
import com.eprod.school.repository.StudentRepository;
import com.eprod.school.repository.StudentStreamRepository;
import com.eprod.school.requestWrappers.StudentStreamWrapper;
import com.eprod.school.requestWrappers.StudentWrapper;
import com.eprod.school.requestWrappers.UniversalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FormStreamRepository formStreamRepository;
    StudentStreamRepository studentStreamRepository;


    public ResponseEntity<UniversalResponse> createStudent(StudentWrapper studentWrapper) {
        Optional<StudentEntity> studentRegNo = studentRepository.findByRegNo(studentWrapper.getRegNo());
        if(studentRegNo.isPresent()){
            return UniversalResponse.responseFormatter(400, 400, "student alreay exist", studentRegNo);

        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(studentWrapper.getFirstName());
        studentEntity.setLastName(studentWrapper.getLastName());
        studentEntity.setDob(studentWrapper.getDob());
        studentEntity.setFormStream(studentWrapper.getFormStream());
        studentEntity.setRegNo(studentWrapper.getRegNo());

       StudentEntity student = studentRepository.save(studentEntity);
        return UniversalResponse.responseFormatter(200, 200, "student added", studentEntity);


    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    public ResponseEntity<UniversalResponse> getStudentList(int page,int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<StudentEntity> all = studentRepository.findAll(pageable);
        return UniversalResponse.responseFormatter(200, 200, "stream list", all);
    }

    /**
     *
     * @param id
     * @return
     */
    public ResponseEntity<UniversalResponse> getStudent(Long id){
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("student with that id not found"));
        return UniversalResponse.responseFormatter(200, 200, "get single student", student);

    }

    /**
     *
     * @param id
     * @return
     */
    public ResponseEntity<UniversalResponse> deleteStudent(int id){
        Long studentId = Long.valueOf(id);

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("student with that id not found"));
          studentRepository.deleteById(studentId);
        return UniversalResponse.responseFormatter(200, 200, "student deleted", student);

    }

    /**
     *
     * @param id
     * @param studentWrapper
     * @return
     */
    public ResponseEntity<UniversalResponse> editStudent(Long id,StudentWrapper studentWrapper){
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("student with that id not found"));
        student.setFirstName(studentWrapper.getFirstName());
        student.setLastName(studentWrapper.getLastName());
        student.setRegNo(studentWrapper.getRegNo());
        student.setFormStream(studentWrapper.getFormStream());
        student.setDob(studentWrapper.getDob());
        student = studentRepository.save(student);
        return UniversalResponse.responseFormatter(200, 200, "edit student", student);

    }


public ResponseEntity<UniversalResponse> addStudentToStream(StudentStreamWrapper studentStreamWraper){
    System.out.println("audince group wrapper iss: " + studentStreamWraper);
    FormStreamEntity formStream = formStreamRepository.findById(studentStreamWraper.getStreamId())
            .orElseThrow(() -> new EntityNotFoundException("strem with that id not found"));

    ArrayList<StudentStreamEntity> studentStreamList = new ArrayList<>();
    System.out.println("audience group list is :" + studentStreamList);
    studentStreamWraper.getStudentIds().forEach((studentId) -> {
        System.out.println("audience id is" + studentId);
        StudentStreamEntity studentStreamEntity = new StudentStreamEntity();
        List<StudentStreamEntity> studentStreamEntities = studentStreamRepository.findAllByStreamIdAndStudentId(studentStreamWraper.getStreamId(),studentId);
        if(!studentStreamEntities.isEmpty())
            return;
        studentStreamEntity.setStreamId(studentStreamWraper.getStreamId());
        studentStreamEntity.setStudentId(studentId);
        studentStreamList.add(studentStreamEntity);
    });
    List<StudentStreamEntity> studentStreamEntities = studentStreamRepository.saveAll(studentStreamList);


    return UniversalResponse.responseFormatter(200,400,"student stream",studentStreamEntities);



}
    public ResponseEntity<UniversalResponse> getStudentStreamList(int streamId,int page,int size){
        Pageable pageable = PageRequest.of(page,size,Sort.by("id").descending());
        FormStreamEntity formStream = formStreamRepository.findById(Long.valueOf(streamId))
                .orElseThrow(() -> new EntityNotFoundException("group with that id does not exist"));
        List<StudentEntity> audienceGroupList = studentRepository.findStreamById(formStream.getId());



        return UniversalResponse.responseFormatter(200,200,"group list",audienceGroupList);
    }


}
