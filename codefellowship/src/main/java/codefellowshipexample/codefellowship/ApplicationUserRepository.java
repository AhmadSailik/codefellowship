package codefellowshipexample.codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
//    Iterable<ApplicationUser>findByUsernames(String username);
    public ApplicationUser findByUsername(String username);
}
