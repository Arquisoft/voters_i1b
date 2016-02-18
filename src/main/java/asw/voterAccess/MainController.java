package asw.voterAccess;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import asw.dbManagement.VoterRepository;
import asw.model.Voter;


@RestController
public class MainController {

    
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserInfo> getVoterInfo(@RequestBody UserInfo userInfo) {
            //If the user on the input is the same as the one in the db and credentials are ok
            //return user info and HTTP response
         //   if(userInfo.getPassword().equals(dbuser.password) && userInfo.getEmail().equals(dbuser.email)){
           // return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);}
    	
            return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
       // return this.vService.findByEmailAndPassword(userInfo.getEmail(),userInfo.getPassword());
    }

    @RequestMapping("/")
    public String landing() {
        return "User Management Service";
    }
    
    
    @Autowired
    public VoterRepository voterRep ;   
    
    @RequestMapping("/probarDB")
    public String probardb() {
        
    	try{
    		Voter v = new Voter("Daniel Cuesta Suarez","1234","dani@example.com","123456","71776420K");
    		System.out.println(voterRep.save(v));
    	}catch(Exception e){e.printStackTrace();}
    	
    	return "Done";
    }
}