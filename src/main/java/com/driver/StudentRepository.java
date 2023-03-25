package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentMap;
    HashMap<String, Teacher> teacherMap;
    HashMap<String, List<String>> teacherStudentPairMap;

    public StudentRepository(){
        this.studentMap= new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentPairMap = new HashMap<String, List<String>>();
    }

    public void addStudent(Student student) {
        studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> allStudent = new ArrayList<>();
        if(teacherStudentPairMap.containsKey(teacher)){
            allStudent = teacherStudentPairMap.get(teacher);
            allStudent.add(student);
            teacherStudentPairMap.put(teacher, allStudent);
        }else{
            allStudent.add(student);
            teacherStudentPairMap.put(teacher, allStudent);
        }
    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByname(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentByTeacherName(String teacher) {
        List<String> studentList = new ArrayList<>();
        if(teacherStudentPairMap.containsKey(teacher)){
            studentList = teacherStudentPairMap.get(teacher);
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        List<String> studentList = new ArrayList<>();
        if(teacherStudentPairMap.containsKey(teacher)){
            studentList = teacherStudentPairMap.get(teacher);
        }
        for(String student : studentList){
            studentMap.remove(student);
        }
        if(teacherMap.containsKey(teacher))
            teacherMap.remove(teacher);

        if(teacherStudentPairMap.containsKey(teacher))
            teacherStudentPairMap.remove(teacher);
    }

    public void deleteAllTeacher() {
        HashSet<String> studentSet = new HashSet<>();
        for(String teacher : teacherStudentPairMap.keySet()){
            studentSet.addAll(teacherStudentPairMap.get(teacher));
        }
        teacherMap = new HashMap<>();
        for(String student : studentSet){
            studentMap.remove(student);
        }
        teacherStudentPairMap = new HashMap<>();
    }
}
