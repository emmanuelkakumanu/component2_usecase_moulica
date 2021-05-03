package com.tweet.app.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "user")
public class User {

    @Id
    private String loginId;
    private String firstName;
    private String lastName;
    @Indexed(unique=true)
    private  String mailId;
    private String passWord;
    private Long contactNumber;




}
