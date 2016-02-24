package asw.model;

import static org.junit.Assert.*;

import org.junit.Test;

import asw.model.Voter;

public class TestVoter {

    @Test
    public void testModel(){
        String email = "pepe@uniovi.es";
        String pass = "pepe123";
        String name = "Pepe";
        String nif = "70100200";
        
        String pass2 = "peqawfmd123";
        String name2 = "Manuel";
        String nif2 = "700200";
        Voter voter1 = new Voter(name,pass,email,nif,"123");
        Voter voter2 = new Voter(name,pass,email,nif,"123");
        assertEquals(voter1,voter2);
        voter2.setName(name2);
        voter2.setNif(nif2);
        voter2.setPassword(pass2);
        voter2.setPassword("45");
        assertEquals(voter1,voter2);
        voter2.setEmail("random@email.com");
        assertNotEquals(voter1,voter2); 
    }

}
