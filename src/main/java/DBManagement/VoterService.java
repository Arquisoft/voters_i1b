package DBManagement;

import model.Voter;

/**
 * Created by Carlos on 15/2/16.
 */
public interface VoterService {
	@org.springframework.beans.factory.annotation.Autowired(required=true)

    Voter findByEmailAndPassword(String email, String password);

    Voter updateVoterPassword(String email,String password);

}
