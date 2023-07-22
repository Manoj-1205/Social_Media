package Repository;

import models.Comments;
import models.Post;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedRepository {

    private static Map<Long, Post> postMap = new HashMap<>();
    private static Map<Long, Comments> commentsMap=new HashMap<>();

    public void save(Post post){
        postMap.put(post.getPostId(), post);
    }

    public Map<Long, Post> getPostList(){
        return postMap;
    }

    public void addComment(User user, Long postId, Comments comment){
//        if(postMap.get(postId).getCommentList()==null){
//            postMap.
//        }
        postMap.get(postId).getCommentList().add(comment);
        commentsMap.put(comment.getCommentId(), comment);
    }

    public void replyToComment(User user, Long commentId, Comments comment){
//        if(postMap.get(postId).getCommentList()==null){
//            postMap.
//        }
        commentsMap.get(commentId).getReplyList().add(comment);
    }
}
