package jock.demo.controller;

import jock.demo.model.Maillist;
import jock.demo.service.MaillistService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/mail")
public class MaillistController {

    @Resource
    private MaillistService maillistService;


    /**
     *
     * Add a person to the mailing list
     * <pre>
     * ENDPOINT: http://localhost:8080/mail
     * RequestMethod: POST
     *
     * <p> {
     * "email":"jock@email.com"
     * } </p>
     * </pre>
     * @param person
     * @return
     */
    @RequestMapping(value = "", method = POST, produces = "application/json")
    public MyReponseBody addPerson(@RequestBody Maillist person) {
        if(person.getEmail()==null||"".equals(person.getEmail())){
           return MyReponseBody.failed("Email address cannot be empty!");
        }
        person.setId(null);
        person.setCreateDate(new Date());

        maillistService.addMailAddress(person.getEmail());
        return MyReponseBody.ok(person);
    }


    /**
     * Remove a person from the mailing list
     * <pre>
     * ENDPOINT: http://localhost:8080/mail/{email}
     * RequestMethod: delete
     * </pre>
     * @param email
     * @return
     */
    @RequestMapping(value = "/{email}", method = DELETE)
    public MyReponseBody removePerson(@PathVariable String email) {
        if(email==null||"".equals(email)){
            return MyReponseBody.failed("Email address cannot be empty!");
        }
        maillistService.deleteMailAddress(email);
        return MyReponseBody.ok();
    }

}
