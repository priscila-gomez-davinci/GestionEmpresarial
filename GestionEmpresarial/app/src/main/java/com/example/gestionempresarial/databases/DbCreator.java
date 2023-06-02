package com.example.gestionempresarial.databases;

import static com.example.gestionempresarial.utils.Constants.AUTH;
import static com.example.gestionempresarial.utils.Constants.DATABASE_NAME;
import static com.example.gestionempresarial.utils.Constants.DB_VERSION;
import static com.example.gestionempresarial.utils.Constants.EMPLOYEES;
import static com.example.gestionempresarial.utils.Constants.PASSWORD;
import static com.example.gestionempresarial.utils.Constants.USER;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gestionempresarial.pojos.Auth;
import com.example.gestionempresarial.pojos.Employee;

import java.util.ArrayList;
import java.util.List;

public class DbCreator extends SQLiteOpenHelper {

    private static DbCreator instance;

    public DbCreator(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }
    public static synchronized DbCreator getInstance(Context context) {
        if (instance == null) {
            instance = new DbCreator(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryToCreateUserTable = createUserTable();
        sqLiteDatabase.execSQL(queryToCreateUserTable);

        String queryToCreateEmployeesTable = createTableEmployees();
        sqLiteDatabase.execSQL(queryToCreateEmployeesTable);

        String testUser = testUser();
        sqLiteDatabase.execSQL(testUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**Tables creation**/
    private String createTableEmployees(){

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE employees (");
        queryBuilder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        queryBuilder.append("name VARCHAR, ");
        queryBuilder.append("lastname VARCHAR, ");
        queryBuilder.append("email VARCHAR, ");
        queryBuilder.append("telephone VARCHAR, ");
        queryBuilder.append("file_number VARCHAR, ");
        queryBuilder.append("street VARCHAR, ");
        queryBuilder.append("number VARCHAR, ");
        queryBuilder.append("city VARCHAR, ");
        queryBuilder.append("country VARCHAR, ");
        queryBuilder.append("is_active VARCHAR ");
        queryBuilder.append(");");

        return queryBuilder.toString();
    }
    private String createUserTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE auth (");
        sb.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append("name VARCHAR, ");
        sb.append("lastname VARCHAR, ");
        sb.append("user VARCHAR, ");
        sb.append("password VARCHAR, ");
        sb.append("is_active VARCHAR");
        sb.append(");");

        return sb.toString();
    }

    /**Tables insert**/
    private String queryInsertEmployee(String name, String lastname, String email, String telephone, String filenumber, String isActive, String street, String number, String city, String country, String lat, String lon ) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT OR IGNORE INTO employees");
        sb.append("(");
        sb.append("name");
        sb.append(" , ");
        sb.append("lastname");
        sb.append(" , ");
        sb.append("email");
        sb.append(" , ");
        sb.append("telephone");
        sb.append(" , ");
        sb.append("file_number");
        sb.append(" , ");
        sb.append("is_active");
        sb.append(" , ");
        sb.append("street");
        sb.append(" , ");
        sb.append("number");
        sb.append(" , ");
        sb.append("city");
        sb.append(" , ");
        sb.append("country");
        sb.append(" , ");
        sb.append("latitud");
        sb.append(" , ");
        sb.append("longitud");
        sb.append(")");
        sb.append(" VALUES ");
        sb.append("( ");
        sb.append(" ' ");
        sb.append(name);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(lastname);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(email);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(telephone);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(filenumber);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(isActive);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(street);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(number);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(city);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(country);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(lat);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(lon);
        sb.append(" ' ");
        sb.append(")");

        return sb.toString();
    }

    private String queryInsertAuth(String name, String lastname, String user, String password, String isActive) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT OR IGNORE INTO auth");
        sb.append("(");
        sb.append("name");
        sb.append(" , ");
        sb.append("lastname");
        sb.append(" , ");
        sb.append("user");
        sb.append(" , ");
        sb.append("password");
        sb.append(" , ");
        sb.append("is_active");
        sb.append(")");
        sb.append(" VALUES ");
        sb.append("( ");
        sb.append(" ' ");
        sb.append(name);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(lastname);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(user);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(password);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append(" ' ");
        sb.append(isActive);
        sb.append(" ' ");

        sb.append(")");

        return sb.toString();
    }

    /**Tables update**/
    private String queryUpdateAuth(int id, String name, String lastname, String user, String password, String isActive){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE auth SET");
        sb.append("(");
        sb.append("name = ");
        sb.append(" ' ");
        sb.append(name);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("lastname = ");
        sb.append(" ' ");
        sb.append(lastname);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("user = ");
        sb.append(" ' ");
        sb.append(user);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("password = ");
        sb.append(" ' ");
        sb.append(password);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("is_active = ");
        sb.append(" ' ");
        sb.append(isActive);
        sb.append(" ' ");
        sb.append("WHERE id = ");
        sb.append(id);
        sb.append(";");
        return sb.toString();
    }

    private String queryUpdateEmployee(int id, String name, String lastname, String email, String telephone, String filenumber, String isActive, String street, String number, String city, String country, String lat, String lon ){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE employees SET");
        sb.append("(");
        sb.append("name = ");
        sb.append(" ' ");
        sb.append(name);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("lastname = ");
        sb.append(" ' ");
        sb.append(lastname);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("email = ");
        sb.append(" ' ");
        sb.append(email);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("telephone = ");
        sb.append(" ' ");
        sb.append(telephone);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("file_number = ");
        sb.append(" ' ");
        sb.append(filenumber);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("is_active = ");
        sb.append(" ' ");
        sb.append(isActive);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("street = ");
        sb.append(" ' ");
        sb.append(street);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("number = ");
        sb.append(" ' ");
        sb.append(number);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("city = ");
        sb.append(" ' ");
        sb.append(city);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("country = ");
        sb.append(" ' ");
        sb.append(country);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("latitud = ");
        sb.append(" ' ");
        sb.append(lat);
        sb.append(" ' ");
        sb.append(" , ");
        sb.append("longitud = ");
        sb.append(" ' ");
        sb.append(lon);
        sb.append(" ' ");
        sb.append("WHERE id = ");
        sb.append(id);
        sb.append(";");
        return sb.toString();
    }

    /**Tables delete**/
    private String queryDeleteAuth(int id){
        StringBuilder deleteQueryBuilder = new StringBuilder();
        deleteQueryBuilder.append("DELETE FROM auth WHERE id = ");
        deleteQueryBuilder.append(id);
        deleteQueryBuilder.append(";");

        return deleteQueryBuilder.toString();
    }

    private String queryDeleteEmployee(int id){
        StringBuilder deleteQueryBuilder = new StringBuilder();
        deleteQueryBuilder.append("DELETE FROM employees WHERE id = ");
        deleteQueryBuilder.append(id);
        deleteQueryBuilder.append(";");
        return deleteQueryBuilder.toString();

    }

    /**Selects * from tables**/
    public List<Employee> getEmployees(){
        String selectQuery = "SELECT  * FROM " + EMPLOYEES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Employee> employeesList = new ArrayList<Employee>();
        if (cursor.moveToFirst()) {
            do {
                Employee list = new Employee();
                list.setId(cursor.getInt(0));
                list.setName(cursor.getString(1));
                list.setLastname(cursor.getString(2));
                list.setEmail(cursor.getString(3));
                list.setTelephone(cursor.getString(4));
                list.setFileNumber(cursor.getString(5));
                list.setActive(cursor.getString(6));
                list.setStreet(cursor.getString(7));
                list.setNumber(cursor.getString(8));
                list.setCity(cursor.getString(8));
                list.setCountry(cursor.getString(8));
                list.setLat(cursor.getString(8));
                list.setLon(cursor.getString(8));

                employeesList.add(list);
            } while (cursor.moveToNext());
        }
        return employeesList;
    }

    public Auth getUser(String user, String password){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  * FROM ");
        sb.append(AUTH);
        sb.append(" WHERE ");
        sb.append(USER);
        sb.append(" = ");
        sb.append(" ' ");
        sb.append(user);
        sb.append(" ' ");
        sb.append(" AND ");
        sb.append(PASSWORD);
        sb.append(" = ");
        sb.append(" ' ");
        sb.append(password);
        sb.append(" ' ");
        String selectQuery = sb.toString();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Auth usuario = null;
        if (cursor.moveToFirst()) {
            do {
                usuario.setId(cursor.getInt(0));
                usuario.setName(cursor.getString(1));
                usuario.setLastname(cursor.getString(2));
                usuario.setUser(cursor.getString(3));
                usuario.setPassword(cursor.getString(4));
                usuario.setActive(cursor.getString(5));

            } while (cursor.moveToNext());
        }
        return usuario;
    }

    public String testUser(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT OR IGNORE INTO auth");
        sb.append("(");
        sb.append("name");
        sb.append(",");
        sb.append("lastname");
        sb.append(",");
        sb.append("user");
        sb.append(",");
        sb.append("password");
        sb.append(",");
        sb.append("is_active");
        sb.append(")");
        sb.append(" VALUES ");
        sb.append("( ");
        sb.append("'");
        sb.append("Priscila");
        sb.append("'");
        sb.append(",");
        sb.append("'");
        sb.append("Gomez");
        sb.append("'");
        sb.append(",");
        sb.append("'");
        sb.append("user");
        sb.append("'");
        sb.append(",");
        sb.append("'");
        sb.append("password");
        sb.append("'");
        sb.append(",");
        sb.append("'");
        sb.append("Si");
        sb.append("'");
        sb.append(")");

        return sb.toString();
    }

}