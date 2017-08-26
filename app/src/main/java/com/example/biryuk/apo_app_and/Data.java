package com.example.biryuk.apo_app_and;

/**
 * Created by Biryuk on 17.08.17.
 */

public final class Data {

    private static String token;

    public static String GetToken(){
        return token;
    }

    public static void SetToken(String Token) {
        token = Token;
    }

    private static Teacher teacher;

    public static Teacher GetTeacher(){
        return teacher;
    }

    public static void SetTeacher(Teacher Teacher) {
        teacher = Teacher;
    }

    private static String role;

    public static String GetRole(){
        return role;
    }

    public static void SetRole(String Role) {
        role = Role;
    }
}
