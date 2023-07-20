package models;

import Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class User {
    private Long userId;
    private String userName;

    //private String password;
}
