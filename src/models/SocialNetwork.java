package models;

import Repository.FeedRepository;
import Repository.SessionRepository;
import Repository.UserRepository;
import Repository.VotingTracker;
import generators.CommentIdGenerator;
import generators.PostIdGenerator;
import lombok.AllArgsConstructor;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            System.out.println("User already exist. Try a different username");
            return;
        }
        System.out.println("Signed un successfully. Please log in.");
        userRepository.save(user);
    }

    public void login(User user){

        if(!userRepository.getUserList().contains(user)){
            System.out.println("Account not found!. Please sign up first.");
            return;
        }

        if(sessionRepository.getSessionStatus(user)){
            System.out.println("Exiting Previous session..");
        }

        System.out.println(user.getUserName()+" Logged in successfully");
        sessionRepository.setSessionStatusActive(user);
    }

    public void logout(User user){
        if(!sessionRepository.isLoggedIn(user)){
            System.out.println("Failed Operation : User not logged in");
            return;
        }
        System.out.println(user.getUserName()+ " User Logged out");
        sessionRepository.setSessionStatusInactive(user);
    }

    public void postFeed(Post post){
        if(!isUserLoggedIn(post.getUser())) {
            System.out.println("User not logged in");
            return;
        }
        post.setPostId(PostIdGenerator.nextId());
        feedRepository.save(post);
        System.out.println("Posted feed with id "  +post.getPostId()+" by "+post.getUser().getUserName());
    }

    public void showNewsFeed(User user){
        if(!isUserLoggedIn(user)) return;

        System.out.println("\n\nNEWS FEED..");
        Map<Long,Post> postList = feedRepository.getPostList();
        List<Post> posts = new ArrayList<>(postList.values());
        if(posts.size()==0){
            System.out.println("No posts were created.");
        }
        posts.sort(new NewsFeedComparator(user));
        posts.forEach((post) -> {
            System.out.println(post.getPostId() + " " + post.getContent() + "\nUpvotes " + post.getUpvotes() +
                    "\nDownvotes " + post.getDownvotes() + "\nCreated by " + post.getUser().getUserName() +
                    " " + post.postCreatedTime(LocalDateTime.now(), post.getCreatedTime())+"\n");

            if(post.getCommentList().size()!=0){
                System.out.println("COMMENTS:");
                post.getCommentList().forEach(comments -> {
                            System.out.println(comments.getCommentId() + " " +
                                    comments.getAuthor().getUserName() + ": " + comments.getContent()
                                    + " " + comments.commentCreatedTime(LocalDateTime.now(), comments.getTimeStamp())
                                    +"\nUpvotes: "+comments.getUpvote()+" \nDownvotes: "+comments.getDownvote());
                            comments.getReplyList().forEach(reply -> System.out.println("\t"+reply.getCommentId() + " " +
                                    reply.getAuthor().getUserName() + ": " + reply.getContent()
                                    + " " + reply.commentCreatedTime(LocalDateTime.now(), reply.getTimeStamp())
                                    +"\n\tUpvotes: "+comments.getUpvote()+" \n\tDownvotes: "+comments.getDownvote()));
                        }
                );
            }
            System.out.println("\n------------------------------------------");

        });
    }

    //one user can only upvote/downvote one time
    public void upvotePost(Long postId, User user){
        if(!isUserLoggedIn(user)) return;
        votingTracker.upvote(postId, user, feedRepository);
        System.out.println("Upvoted Post "+postId);

    }

    public void downvotePost(Long postId, User user){
        if(!isUserLoggedIn(user)) return;
        votingTracker.downvote(postId, user, feedRepository);
        System.out.println("DownVoted Post "+postId);
    }

    public void follow(User user, User target){
        if(!isUserLoggedIn(user)) return;
        userRepository.follow(user, target);
    }

    public void comment(User user, Long postId, String content){
        if(!isUserLoggedIn(user)) return;
        Comments comment = Comments.builder()
                                   .commentId(CommentIdGenerator.nextId())
                                   .author(user)
                                   .content(content)
                                   .build();
        feedRepository.addComment(user, postId, comment);
        System.out.println("Comment added successfully");
    }

    public void reply(User user, Long commentId, String content){
        if(!isUserLoggedIn(user)) return;
        Comments comment = Comments.builder()
                .commentId(CommentIdGenerator.nextId())
                .author(user)
                .content(content)
                .build();
        feedRepository.replyToComment(user, commentId, comment);

    }

    public void upvoteComment(Integer CommentId, User user){
        if(!isUserLoggedIn(user)) return;
    }
    public void downvoteComment(Integer CommentId, User user){
        if(!isUserLoggedIn(user)) return;
    }

    public Boolean isUserLoggedIn(User user){
            return sessionRepository.isLoggedIn(user);
    }


}
