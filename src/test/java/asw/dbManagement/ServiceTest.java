package asw.dbManagement;

import org.junit.Test;

import asw.model.Voter;

/**
 * Created by Carlos on 15/2/16.
 */
public class ServiceTest {


    @Test
    public void testModel(){
        String email = "pepe@uniovi.es";
        String pass = "pepe123";
        String name = "Pepe";
        String nif = "70100200";
        Voter voter1 = new Voter(name,pass,email,nif,"123");



    }

}
