package com.example;

public class MakeRequest {

  public void main() {

    String email = "smedeiros.flavio@gmail.com";
    String password = "123456";

    LoginApi loginApi = new LoginApi();

    UserEntity user = loginApi.login(email, password);
  }

}