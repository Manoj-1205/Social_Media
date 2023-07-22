package Repository;

import lombok.Builder;
import models.Post;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VotingTracker {

    private static  Map<User, List<Long>> userUpvotes = new HashMap<>();
    private static Map<User, List<Long>> userDownvotes = new HashMap<>();

//    private static FeedRepository feedRepository;

    public void upvote(Long postId, User user, FeedRepository feedRepository){
        if(!userUpvotes.containsKey(user)){
            userUpvotes.put(user, new ArrayList<>());
        }
        if(userUpvotes.get(user).contains(postId)){
            return;
        }
        if(userDownvotes.get(user)!=null && userDownvotes.get(user).contains(postId)){
            userDownvotes.get(user).remove(postId);
            Post downvotePost = feedRepository.getPostList().get(postId);
            downvotePost.setDownvotes(downvotePost.getDownvotes()-1);
        }
        userUpvotes.get(user).add(postId);
        Post upvotePost = feedRepository.getPostList().get(postId);
        upvotePost.setUpvotes(upvotePost.getUpvotes()+1);

    }



    public void downvote(Long postId, User user, FeedRepository feedRepository){
        if(!userDownvotes.containsKey(user)){
            userDownvotes.put(user, new ArrayList<>());
        }
        if(userDownvotes.get(user).contains(postId)){
            return;
        }
        if(userUpvotes.get(user)!=null && userUpvotes.get(user).contains(postId)){
            System.out.println("DOWNVOTING");
            userUpvotes.get(user).remove(postId);
            Post upvotePost = feedRepository.getPostList().get(postId);
            upvotePost.setUpvotes(upvotePost.getUpvotes()-1);
        }
        userDownvotes.get(user).add(postId);
        Post downvotePost = feedRepository.getPostList().get(postId);
        downvotePost.setDownvotes(downvotePost.getDownvotes()+1);



    }
}
