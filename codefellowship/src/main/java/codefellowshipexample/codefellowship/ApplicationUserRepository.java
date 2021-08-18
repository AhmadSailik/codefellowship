package codefellowshipexample.codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
    Iterable<ApplicationUser>findAll();
    public ApplicationUser findAllById(Integer id);
    public ApplicationUser findByUsername(String username);
}
