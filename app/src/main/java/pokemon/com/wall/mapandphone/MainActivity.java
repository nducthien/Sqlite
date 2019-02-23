package pokemon.com.wall.mapandphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private StudentDAO studentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDAO = new StudentDAO(MainActivity.this);

        Students students = new Students();
        students.student_id = "1";
        students.student_name = "Thien";

        long result = studentDAO.insertStudents(students);
        if (result > 0){
            // them thanh cong
        }else {
            // add faild
        }

        studentDAO.deleteStudents("1");

        studentDAO.updateStudents(students);

        List<Students> students1 = studentDAO.getAllStudent();
    }
}
