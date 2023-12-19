package mvc.resnerrorcoach.repository;

import mvc.resnerrorcoach.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    Set<Student> store = new HashSet<>();

    public void add(Student student) {
        store.add(student);
    }

    public List<Student> getAll() {
        return store.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Student> get(int grade) {
        return store.stream()
                .filter(student -> student.getGrade() == grade)
                .collect(Collectors.toList());
    }

}
