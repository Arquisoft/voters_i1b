package DBManagement;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Voter;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Long> {


    Voter findByEmail(String email);

    //Voter updatePassword(String email,String password);

}
