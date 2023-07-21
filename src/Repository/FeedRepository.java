package Repository;

import models.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedRepository {

    private static Map<Long, Post> postMap = new HashMap<>();

    public void save(Post post){
        postMap.put(post.getPostId(), post);
    }

    public Map<Long, Post> getPostList(){
        return postMap;
    }
}
