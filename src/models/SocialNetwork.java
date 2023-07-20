package models;

import Repository.UserRepository;

public class SocialNetwork {
    private UserRepository userRepository;
    public void signup(User user){
        if(userRepository.getUserList().contains(user)){
            System.out.println("User already exist.");
            return;
        }
        System.out.println("Signed in successfully");
        userRepository.save(user);
    }

    public void login(User user){

    }
}
