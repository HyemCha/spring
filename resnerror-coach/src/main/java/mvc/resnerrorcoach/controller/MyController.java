package mvc.resnerrorcoach.controller;

import lombok.RequiredArgsConstructor;
import mvc.resnerrorcoach.common.ApiResponse;
import mvc.resnerrorcoach.common.ErrorCode;
import mvc.resnerrorcoach.entity.Student;
import mvc.resnerrorcoach.exception.CustomException;
import mvc.resnerrorcoach.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MyController extends BaseController {

    private final StudentService studentService;

    @GetMapping("/student/add")
    public ApiResponse<Student> add(
            @RequestParam("name") String name,
            @RequestParam("grade") int grade
    ) {

        if (grade > 5) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "grade는 6을 넘을 수 없습니다.", grade);
        }

        Student student = studentService.addStudent(name, grade);

        return makeResponse(student);
    }

    @GetMapping("/student/getAll")
    public ApiResponse<Student> getAll() {
        List<Student> students = studentService.getAll();

        return makeResponse(students);
    }

    @GetMapping("/student/get")
    public ApiResponse<Student> get(
            @RequestParam("grade") int grade
    ) {
        List<Student> students = studentService.get(grade);
        return makeResponse(students);
    }
}
