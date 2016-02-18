package asw.voterAccess;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import asw.dbManagement.VoterRepository;
import asw.model.Voter;


@RestController
public class MainController {


    @Autowired
    private VoterRepository voterRep;
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    
    @RequestMapping(value = "/user",consumes = { MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Voter> getVoterInfo(@RequestBody String json) {
            //If the user on the input is the same as the one in the db and credentials are ok
            //return user info and HTTP response
    	Voter voter;
    	try {
            voter = JSON_MAPPER.readValue(json, Voter.class);
            Voter dbVoter = voterRep.findByEmail(voter.getEmail());
            if(voter.validate(dbVoter)){
         	   return new ResponseEntity<Voter>(dbVoter, HttpStatus.OK);}
     	
        } catch (Exception e) {
        	return new ResponseEntity<Voter>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<Voter>(HttpStatus.NOT_FOUND);
       // return this.vService.findByEmailAndPassword(userInfo.getEmail(),userInfo.getPassword());
    }

    @RequestMapping("/")
    public String landing() {
        return "User Management Service";
    }
    
    
    
    @RequestMapping("/probarDB")
    public String probardb() {
        
    	try{
    		Voter v = new Voter("Daniel Cuesta Suarez","1234","dani@example.com","123456","71776420K");
    		System.out.println(voterRep.save(v));
    	}catch(Exception e){e.printStackTrace();}
    	
    	return "Done";
    }
}