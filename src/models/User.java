package models;

import Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class User {
    private Long userId;
    private String userName;
    List<User> followers=new ArrayList<>();
    List<User> following=new ArrayList<>();

    //private String password;
}
