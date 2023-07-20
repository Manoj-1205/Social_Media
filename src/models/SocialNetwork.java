package models;

import Repository.SessionRepository;
import Repository.UserRepository;
import lombok.AllArgsConstructor;

import javax.sound.midi.Soundbank;

@AllArgsConstructor
public class SocialNetwork {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    public void signup(User user){
        if(UserRepository.getUserList().contains(user)){
            System.out.println("User already exist.");
            return;
        }
        System.out.println("Signed in successfully");
        UserRepository.save(user);
    }

    public void login(User user){
        if(sessionRepository.getSessionStatus()){
            System.out.println("Exiting Previous session..");
        }
        System.out.println("Logged in successfully");
        sessionRepository.setSessionStatusActive();
    }

    public void logout(User user){
        System.out.println("User Logged out");
        sessionRepository.setSessionStatusInactive();
    }
}
