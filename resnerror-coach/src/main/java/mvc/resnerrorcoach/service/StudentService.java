package mvc.resnerrorcoach.service;

import lombok.RequiredArgsConstructor;
import mvc.resnerrorcoach.entity.Student;
import mvc.resnerrorcoach.repository.StudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student addStudent(String name, int grade) {
        Student student = new Student(name, grade);
        studentRepository.add(student);

        return student;
    }

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    public List<Student> get(int grade) {
        return studentRepository.get(grade);
    }
}
