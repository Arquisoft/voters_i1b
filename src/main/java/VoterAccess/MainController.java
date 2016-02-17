package VoterAccess;


import DBManagement.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MainController {


    @Autowired
    private VoterService vService;
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    //connect to db and try to find user
    private String hostname = "digital-skill.com:3306/andrei_voters";
    private String user = "andrei_vot";
    private String pass = "votersgonnavote";
    
    private void connToDb() {
    	Connection con;
    	try {
    		con = DriverManager.getConnection(hostname, user, pass);
    		System.out.println("Connected");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
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
}