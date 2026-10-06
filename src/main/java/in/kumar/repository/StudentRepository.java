package in.kumar.repository;

import in.kumar.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    String url="jdbc:mysql://localhost:3306/student_db";
    String username="root";
    String password="Ritesh@7070";

    private JdbcTemplate jdbcTemplate;
//    private StudentRowMapper studentRowMapper = new StudentRowMapper();

    private RowMapper<Student> studentRowMapper =
            new BeanPropertyRowMapper<>(Student.class);

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStudent(Student student){
        String sql = """
                        INSERT into students(name,email,age)
                        VALUES ( ? , ? , ?)
                        """;
        int rowAffected = jdbcTemplate.update(sql,
                student.getName(),student.getEmail(),student.getAge());

        if (rowAffected == 1){
            System.out.println("Created Successful");
        }else {
            System.out.println("Creation failed");
        }

    }

    public void updateStudent(Student student,long id){

        String sql = """
                    UPDATE students 
                    SET name = ?,
                        email = ?,
                        age = ?
                    WHERE id = ?
                    """;
        int rowAffected = jdbcTemplate.update(sql,student.getName(),student.getEmail(),student.getAge(),id);

        if (rowAffected == 1){
            System.out.println("Update Successful");
        }else {
            System.out.println("Updation failed");
        }
    }

    public void deleteStudent(Long id) {
        String sql = " DELETE from " +
                "students where id = ?";

        int rowAffected = jdbcTemplate.update(sql,id);

        if (rowAffected == 1) {
            System.out.println("Deleted Successful");
        } else {
            System.out.println("Deletion failed");
        }
    }
    public Student getStudentById(Long id){

        String sql = "SELECT id,name,email,age " +
                "from students WHERE id = ?";

        return jdbcTemplate.queryForObject(sql,
                studentRowMapper,id);

    }
    public List<Student> getAllStudent(){
        String sql = """
                        SELECT id,name,email,age from students
                        """;
    List<Student> students = jdbcTemplate.query(sql,
            studentRowMapper);

    return students;
    }

}
