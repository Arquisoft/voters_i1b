package DBManagement.impl;

import DBManagement.VoterService;
import VoterAccess.MainController;
import model.Voter;

/**
 * Created by Carlos on 15/2/16.
 */
public class VoterServiceImpl implements VoterService {

    public Voter findByEmailAndPassword(String email, String password) {
        return MainController.findByEmail(email, password);
    }
    
    public Voter updateVoterPassword(String email, String password) {
        return MainController.updatePassword(email, password);
    }
}
