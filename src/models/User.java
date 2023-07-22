package models;

import Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder

public class User {
    private Long userId;
    private String userName;
    @Builder.Default
    List<User> followers=new ArrayList<>();
    @Builder.Default
    List<User> following=new ArrayList<>();

    //private String password;
}
