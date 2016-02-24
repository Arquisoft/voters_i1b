package asw.voterAccess;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import asw.dbManagement.VoterRepository;
import asw.model.Voter;
import asw.model.VoterNewPassword;


@RestController
public class MainRestController {


    @Autowired
    private VoterRepository voterRep;
    
    @RequestMapping(value = "/user",consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Voter> getVoterInfo(@RequestBody Voter voter) {    	
    	return authAndOperate(
    			(dbVoter)->{return new ResponseEntity<Voter>(dbVoter, HttpStatus.OK);}
    			,voter);
    	}
    
    @RequestMapping(value = "/changePass",consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Voter> changePass(@RequestBody VoterNewPassword voterPass){
    	return authAndOperate(
    		(dbVoter)->{
            		        dbVoter.setPassword(voterPass.getNewPassword());
            				voterRep.save(dbVoter);
            				return new ResponseEntity<Voter>(HttpStatus.OK);
         	  			},
    			new Voter(voterPass.getEmail(), voterPass.getPassword())); 
    }
    
    private ResponseEntity<Voter> authAndOperate(Function<Voter,ResponseEntity<Voter>> f, Voter voter)
    {
    	try {            
            Voter dbVoter = voterRep.findByEmail(voter.getEmail());
            if(voter.validate(dbVoter))
            {
            	System.out.println("POST gotten");
            	return f.apply(dbVoter);
            }
          } catch (Exception e) {
            	System.out.println("POST Failed");
            	e.printStackTrace();        	
            	return new ResponseEntity<Voter>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
                return new ResponseEntity<Voter>(HttpStatus.NOT_FOUND);
    	
    }

    @RequestMapping("/")
    public String landing() {
       return "<!DOCTYPE html>"+
"<html lang=\"en\">"+
"	<head>"+
"		<link rel=\"stylesheet\" href=\"http://yui.yahooapis.com/pure/0.6.0/pure-min.css\">"+
"		<meta charset=\"UTF-8\">"+
"		<title>Voting System</title>"+
"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>		"+
"	</head>"+
"	<body></br>"+
"		<form id=form class=\"pure-form\">"+
"			<input id= \"_user_email\" type=\"text\" name=\"email\"  placeholder=\"Email\">"+
"			<input id=\"_user_pass\" type=\"password\" name=\"password\" placeholder=\"Password\">"+
"			<input type=\"button\" value=\"Submit\" onclick=\"submitform()\" class=\"pure-button pure-button-primary\"/>"+
"		</form>"+
"		<div id=code></div>"+
"		"+
"		"+
"		<script type=\"text/javascript\">	"+
"		"+
"		$.fn.serializeObject = function()"+
"		{"+
"		   var o = {};"+
"		   var a = this.serializeArray();"+
"		   $.each(a, function() {"+
"		       if (o[this.name]) {"+
"		           if (!o[this.name].push) {"+
"		               o[this.name] = [o[this.name]];"+
"		           }"+
"		           o[this.name].push(this.value || '');"+
"		       } else {"+
"		           o[this.name] = this.value || '';"+
"		       }"+
"		   });"+
"		   return o;"+
"		};"+
"					"+
"    		function submitform(){"+
"    			var frm = $(\"form\");"+
"    			var data = JSON.stringify(frm.serializeObject());"+
"        		var xhttp = new XMLHttpRequest();"+
"        		xhttp.onreadystatechange = function() {"+
"        		    if (xhttp.readyState == 4 && xhttp.status == 200) {"+
"        		      document.getElementById(\"code\").innerHTML = xhttp.responseText;"+
"        		    }"+
"        		  };"+
"        		xhttp.open(\"POST\", \"user\", true);"+
"        		xhttp.setRequestHeader(\"Content-Type\", \"application/json\");"+
"        		xhttp.send(data);"+
"        		}"+
"        </script>"+
"	</body>"+
"</html>";

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