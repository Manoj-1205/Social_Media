package generators;

import java.util.concurrent.atomic.AtomicLong;

public class CommentIdGenerator {
    private static AtomicLong idCounter=new AtomicLong(1);
    public static Long nextId(){
        return idCounter.getAndIncrement();
    }
}
