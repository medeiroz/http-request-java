package com.example;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {

    public int id;
    public String email;
    public String name;
    public Date birthDate;
    public int age;

}
