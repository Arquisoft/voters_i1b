package asw.model;

import static org.junit.Assert.*;

import org.junit.Test;

import asw.model.Voter;

public class TestVoterNewPass {

    @Test
    public void testModel(){
        String email = "pepe@uniovi.es";
        String pass = "pepe123";
        
        String pass2 = "peqawfmd123";
        
        VoterNewPassword voter1 = new VoterNewPassword();
        VoterNewPassword voter2 = new VoterNewPassword();
        voter2.setPassword(pass2);
        voter1.setEmail("dani@example.com");
        voter2.setEmail("dani@example.com");
        assertEquals(voter1,voter2);
        voter2.setPassword("45");
        assertEquals(voter1,voter2);
        voter2.setEmail("random@email.com");
        assertNotEquals(voter1,voter2); 
    }

}
