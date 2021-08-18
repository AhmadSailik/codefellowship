package codefellowshipexample.codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface FeedRepository extends CrudRepository<Feed,Integer> {
    Iterable<Feed>findAllByMyName(String MyName);
}
