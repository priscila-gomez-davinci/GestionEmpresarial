package com.example.gestionempresarial.databases;

import static com.example.gestionempresarial.utils.Constants.AUTH;
import static com.example.gestionempresarial.utils.Constants.DATABASE_NAME;
import static com.example.gestionempresarial.utils.Constants.DB_VERSION;
import static com.example.gestionempresarial.utils.Constants.ID;
import static com.example.gestionempresarial.utils.Constants.NAME;
import static com.example.gestionempresarial.utils.Constants.PASSWORD;
import static com.example.gestionempresarial.utils.Constants.USER;

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

    private String createTableEmployees(){

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE employees (");
        queryBuilder.append("name VARCHAR, ");
        queryBuilder.append("lastname VARCHAR, ");
        queryBuilder.append("email VARCHAR, ");
        queryBuilder.append("telephone VARCHAR, ");
        queryBuilder.append("fileNumber VARCHAR, ");
        queryBuilder.append("isActive INTEGER, ");
        queryBuilder.append("street VARCHAR, ");
        queryBuilder.append("number VARCHAR, ");
        queryBuilder.append("city VARCHAR, ");
        queryBuilder.append("country VARCHAR");
        queryBuilder.append(");");

        return queryBuilder.toString();
    }


    private String queryInsertAuth(){
        StringBuilder insertQueryBuilder = new StringBuilder();
        insertQueryBuilder.append("INSERT INTO auth (name, lastname, user, password) VALUES (");
        insertQueryBuilder.append("'John', ");
        insertQueryBuilder.append("'Doe', ");
        insertQueryBuilder.append("'johndoe', ");
        insertQueryBuilder.append("'password123'");
        insertQueryBuilder.append(");");

        return insertQueryBuilder.toString();

    }
    private String queryUpdateAuth(){
        StringBuilder updateQueryBuilder = new StringBuilder();
        updateQueryBuilder.append("UPDATE auth SET ");
        updateQueryBuilder.append("name = 'Jane', ");
        updateQueryBuilder.append("lastname = 'Smith', ");
        updateQueryBuilder.append("user = 'janesmith', ");
        updateQueryBuilder.append("password = 'newpassword'");
        updateQueryBuilder.append(" WHERE id = 1;");

        return updateQueryBuilder.toString();
    }

    private String queryDeleteAuth(){
        StringBuilder deleteQueryBuilder = new StringBuilder();
        deleteQueryBuilder.append("DELETE FROM auth WHERE id = 1;");

        return deleteQueryBuilder.toString();
    }


    private String queryInsertEmployee(){
        StringBuilder insertQueryBuilder = new StringBuilder();
        insertQueryBuilder.append("INSERT INTO auth (name, lastname, email, telephone, fileNumber, isActive, street, number, city, country) VALUES (");
        insertQueryBuilder.append("'John', ");
        insertQueryBuilder.append("'Doe', ");
        insertQueryBuilder.append("'johndoe@example.com', ");
        insertQueryBuilder.append("'123456789', ");
        insertQueryBuilder.append("'12345', ");
        insertQueryBuilder.append("1, ");
        insertQueryBuilder.append("'Main Street', ");
        insertQueryBuilder.append("'123', ");
        insertQueryBuilder.append("'City', ");
        insertQueryBuilder.append("'Country'");
        insertQueryBuilder.append(");");

        return insertQueryBuilder.toString();

    }

    private String queryUpdateEmployee(){
        StringBuilder updateQueryBuilder = new StringBuilder();
        updateQueryBuilder.append("UPDATE auth SET ");
        updateQueryBuilder.append("name = 'Jane', ");
        updateQueryBuilder.append("lastname = 'Smith', ");
        updateQueryBuilder.append("email = 'janesmith@example.com', ");
        updateQueryBuilder.append("telephone = '987654321', ");
        updateQueryBuilder.append("fileNumber = '54321', ");
        updateQueryBuilder.append("isActive = 0, ");
        updateQueryBuilder.append("street = 'New Street', ");
        updateQueryBuilder.append("number = '456', ");
        updateQueryBuilder.append("city = 'New City', ");
        updateQueryBuilder.append("country = 'New Country'");
        updateQueryBuilder.append(" WHERE id = 1;");

        return updateQueryBuilder.toString();


    }


    private String queryDeleteEmployee(){
        StringBuilder deleteQueryBuilder = new StringBuilder();
        deleteQueryBuilder.append("DELETE FROM auth WHERE id = 1;");

        return deleteQueryBuilder.toString();

    }


}
