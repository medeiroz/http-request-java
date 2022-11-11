package com.example;

import com.example.Http;
import com.example.LoginEntity;
import com.example.UserEntity;


public class LoginApi {
    public static UserEntity Login(String email, String password) throws Exception {
        String apiToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + apiToken)


        LoginEntity loginPayload = new LoginEntity(email, password);

        return (UserEntity) Http.Post("https://localhost/api/auth/login", loginPayload, UserEntity.class);
    }
}
