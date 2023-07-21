package Repository;

import models.Post;

import java.util.ArrayList;
import java.util.List;

public class FeedRepository {
    private static List<Post> postList = new ArrayList<>();

    public void save(Post post){
        postList.add(post);
    }

    public List<Post> getPostList(){
        return postList;
    }
}
