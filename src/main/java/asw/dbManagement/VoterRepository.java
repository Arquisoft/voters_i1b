package asw.dbManagement;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.model.Voter;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Long> {

    Voter findByEmail(String email);

}
