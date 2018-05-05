package trizic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trizic.model.Advisor;
import trizic.repo.AdvisorRepo;
import trizic.repo.AdvisorRepoImpl;

import java.util.List;

/**
 * Created by AngelQian on 5/4/18.
 */
@RestController
@RequestMapping("/trizic")
public class AdvisorController {
    private AdvisorRepo repository = new AdvisorRepoImpl();

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public ResponseEntity<Advisor> addOne(@RequestBody Advisor newUser) {
        if(newUser == null)
            return new ResponseEntity<Advisor>(HttpStatus.NO_CONTENT);

        try {
            Advisor user = repository.save(newUser);
            return new ResponseEntity<Advisor>(user, HttpStatus.OK);
        }catch (Exception exp){
            return new ResponseEntity<Advisor>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Advisor>> getAdvisors(
            @RequestParam(value="advisorid", defaultValue = "") String aid,
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="size", defaultValue = "0") int size) throws Exception {
        List<Advisor> list = repository.find(aid, page, size);
        return new ResponseEntity<List<Advisor>>(list ,HttpStatus.OK);
    }
}
