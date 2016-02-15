package DBManagement;

import model.Voter;

/**
 * Created by Carlos on 15/2/16.
 */
public interface VoterService {


    Voter findByEmailAndPassword(String email, String password);

    Voter updateVoterPassword(String email,String password);

}
