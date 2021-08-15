package codefellowshipexample.codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Integer> {
    Iterable<Post>findAllByaddingPostId(Integer addinPostId);

}
