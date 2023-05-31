package com.example.gestionempresarial.databases;

import static com.example.gestionempresarial.utils.Constants.DATABASE_NAME;
import static com.example.gestionempresarial.utils.Constants.DB_VERSION;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**Tables creation**/
    private String createTableEmployees(){

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE employees (");
        queryBuilder.append("name VARCHAR, ");
        queryBuilder.append("lastname VARCHAR, ");
        queryBuilder.append("email VARCHAR, ");
        queryBuilder.append("telephone VARCHAR, ");
        queryBuilder.append("file_number VARCHAR, ");
        queryBuilder.append("is_active INTEGER, ");
        queryBuilder.append("street VARCHAR, ");
        queryBuilder.append("number VARCHAR, ");
        queryBuilder.append("city VARCHAR, ");
        queryBuilder.append("country VARCHAR");
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
        sb.append("password VARCHAR");
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
}
