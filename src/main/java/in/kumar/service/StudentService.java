package in.kumar.service;

import in.kumar.model.Student;
import in.kumar.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){
        studentRepository.createStudent(student);
    }

    public void updateSudent(Student student,long id){
        studentRepository.updateStudent(student,id);
    }
    public void deleteStudent(long id){
        studentRepository.deleteStudent(id);
    }
    public Student getStudentById(long id){
        Student s1 = studentRepository.getStudentById(id);
        return s1;
    }
    public List<Student> getAllStudent(){
        List<Student> list = studentRepository.getAllStudent();
          return list;
    }
}
