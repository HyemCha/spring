package mvc.resnerrorcoach.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Student implements Comparable<Student> {

    private String name;
    private int grade;

    @Override
    public int compareTo(Student o) {
        return this.getGrade() - o.getGrade();
    }
}
