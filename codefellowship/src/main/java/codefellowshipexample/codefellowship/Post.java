package codefellowshipexample.codefellowship;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    @ManyToOne
    private ApplicationUser addingPost;

    public Post(){

    }

    public Post(String body, ApplicationUser addingPost) {
        this.body = body;
        this.addingPost = addingPost;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ApplicationUser getAddingPost() {
        return addingPost;
    }

    public void setAddingPost(ApplicationUser addingPost) {
        this.addingPost = addingPost;
    }
}
