package Repository;

import lombok.Getter;
import models.Comments;
import models.Post;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FeedRepository {

    private static Map<Long, Post> postMap = new HashMap<>();
    private  Map<Long, Comments> commentsMap=new HashMap<>();

    public void save(Post post){
        postMap.put(post.getPostId(), post);
    }

    public Map<Long, Post> getPostList(){
        return postMap;
    }

    public void addComment(User user, Long postId, Comments comment){
        postMap.get(postId).getCommentList().add(comment);
        commentsMap.put(comment.getCommentId(), comment);
    }

    public void replyToComment(User user, Long commentId, Comments comment){
        commentsMap.get(commentId).getReplyList().add(comment);
    }

    public void upvoteComment(long commentId, User user){
        Comments comment = commentsMap.get(commentId);
//        comment.getUpvote()
    }
}
