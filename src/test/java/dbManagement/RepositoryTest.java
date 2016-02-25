package dbManagement;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import asw.Application;
import asw.dbManagement.VoterRepository;
import asw.model.Voter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class RepositoryTest {

	private String email;
	private String pass;
	private String name;
	private String nif;
	
	 @Autowired
	    private VoterRepository voterRep;
	    
	@Before
	public void init()
	{
		email = "pepe@uniovi.es";
		pass = "pepe123";
		name = "Pepe";
		nif = "000";
		try {
	    	Voter voter1 = voterRep.findByEmail(email);
	        voterRep.delete(voter1);
		} catch (Exception e) {
		}
		
	}
	 
    @Test 
    public void testRepoAdd(){
    	Voter voter1 = new Voter(name,pass,email,"0",nif); 
    	voter1 = voterRep.save(voter1);
    	try
    	{
    		assertEquals(voterRep.findByEmail(voter1.getEmail()), voter1);
    	}catch(Exception e){}
    	voterRep.delete(voter1);
        
    }
    
    @Test
    public void testRepoDel(){
    
    	Voter voter1 = new Voter(name,pass,email,"0",nif); 
    	voter1= voterRep.save(voter1);
    	voterRep.delete(voter1);
    	try
    	{
    		assertEquals(voterRep.findByEmail(voter1.getEmail()), null);
    	}catch(Exception e){}
        
    }
    

}
