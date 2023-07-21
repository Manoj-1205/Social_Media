package models;

import Repository.FeedRepository;
import Repository.SessionRepository;
import Repository.UserRepository;
import Repository.VotingTracker;
import generators.PostIdGenerator;
import lombok.AllArgsConstructor;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class SocialNetwork {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private FeedRepository feedRepository;
    private VotingTracker votingTracker;
    public void signup(User user){
        if(userRepository.getUserList().contains(user)){
            System.out.println("User already exist.");
            return;
        }
        System.out.println("Signed in successfully");
        userRepository.save(user);
    }

    public void login(User user){
        if(sessionRepository.getSessionStatus(user)){
            System.out.println("Exiting Previous session..");
        }
        System.out.println(user.getUserName()+" Logged in successfully");
        sessionRepository.setSessionStatusActive(user);
    }

    public void logout(User user){
        System.out.println(user.getUserName()+ " User Logged out");
        sessionRepository.setSessionStatusInactive(user);
    }

    public void postFeed(Post post){
        post.setPostId(PostIdGenerator.nextId());
        feedRepository.save(post);
        System.out.println("Posted feed with id "+post.getPostId());
    }

    public void showNewsFeed(){
        System.out.println("NEWS FEED..");
        Map<Long,Post> postList = feedRepository.getPostList();
        postList.forEach((id, post) -> System.out.println(post.getPostId()+" "+post.getContent()+" \nUpvotes "+ post.getUpvotes()+" \nDownvotes "
                + post.getDownvotes()+" \nCreated by "+post.getUser().getUserName()+ " "+post.postCreatedTime(LocalDateTime.now(), post.getCreatedTime())+"\n"));
    }

    //one user can only upvote/downvote one time
    public void upvotePost(Long postId, User user){
        votingTracker.upvote(postId, user, feedRepository);

    }

    public void downvotePost(Long postId, User user){
        votingTracker.downvote(postId, user, feedRepository);
    }

    public void follow(User user, User target){
        userRepository.follow(user, target);
    }


}
