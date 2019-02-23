package pokemon.com.wall.mapandphone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static pokemon.com.wall.mapandphone.SqliteHelper.SV_BIRTHDAY;
import static pokemon.com.wall.mapandphone.SqliteHelper.SV_CLASS;
import static pokemon.com.wall.mapandphone.SqliteHelper.SV_HOME;
import static pokemon.com.wall.mapandphone.SqliteHelper.SV_ID;
import static pokemon.com.wall.mapandphone.SqliteHelper.SV_NAME;
import static pokemon.com.wall.mapandphone.SqliteHelper.SV_TABLE;

public class StudentDAO {

    private SqliteHelper sqliteHelper;

    public StudentDAO(Context context){
        sqliteHelper = new SqliteHelper(context);
    }

    public List<Students> getAllStudent() {
        String query_all = "SELECT * FROM " + SV_TABLE ;
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getReadableDatabase();// kiem tra bo nho truoc khi ghi

        // doc du lieu va luu vao bien con tro Cursor
        //rawQuery giup truy van ro rang hon vi du nhu where
        Cursor cursor = sqLiteDatabase.rawQuery(query_all, null);

        List<Students> array_students = new ArrayList<>();

        // trong lap trinh kiem tra bien co du lieu khong

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                // if read finish data will out while
                while (!cursor.isAfterLast()) {

                    String student_id = cursor.getString(cursor.getColumnIndex(SV_ID));

                    String student_name = cursor.getString(cursor.getColumnIndex(SV_NAME));
                    String student_hometown = cursor.getString(cursor.getColumnIndex(SV_HOME));
                    String student_birthday = cursor.getString(cursor.getColumnIndex(SV_BIRTHDAY));
                    String student_class_id = cursor.getString(cursor.getColumnIndex(SV_CLASS));

                    Students students = new Students();
                    students.student_id = student_id;
                    students.student_name = student_name;
                    students.student_hometown = student_hometown;
                    students.student_birthday = student_birthday;
                    students.student_class_id = student_class_id;


                    array_students.add(students);

                    // di chuyen toi vi tri tiep theo
                    cursor.moveToNext();
                }
            }
        }

        cursor.close();
        sqLiteDatabase.close();

        return array_students;
    }

    public long insertStudents(Students students) {
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(SV_ID, students.student_id);
        values.put(SV_NAME, students.student_name);
        values.put(SV_HOME, students.student_hometown);
        values.put(SV_BIRTHDAY, students.student_birthday);
        values.put(SV_CLASS, students.student_class_id);

        // them gia tri vao bang
        long result = sqLiteDatabase.insert(SV_TABLE,null,values);
        sqLiteDatabase.close();

        return result;

    }

    public int updateStudents(Students students){
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(SV_ID, students.student_id);
        values.put(SV_NAME, students.student_name);
        values.put(SV_HOME, students.student_hometown);
        values.put(SV_BIRTHDAY, students.student_birthday);
        values.put(SV_CLASS, students.student_class_id);

        int result = sqLiteDatabase.update(SV_TABLE, values, SV_ID + "=?", new String[]{students.student_id});
        sqLiteDatabase.close();

        return  result;
    }

    public int deleteStudents(String student_id){
        SQLiteDatabase sqLiteDatabase = sqliteHelper.getReadableDatabase();

        int result =
                sqLiteDatabase.delete(SV_TABLE, SV_ID + "=?", new String[]{student_id});

        return result;

    }

}
