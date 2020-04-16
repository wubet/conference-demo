package com.myCompany.conferencedemo.controllers;

import com.myCompany.conferencedemo.Exception.ApplicationNotFoundException;
import com.myCompany.conferencedemo.models.Session;
import com.myCompany.conferencedemo.models.Speaker;
import com.myCompany.conferencedemo.repositories.SpeakerRepository;
import com.myCompany.conferencedemo.services.SpeakerService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakersController {


  @Autowired
  private SpeakerService speakerService;

  @GetMapping
  public ResponseEntity<List<Speaker>> list(){
    try{
      List<Speaker> list = speakerService.listAllSpeakers();
      return new ResponseEntity<List<Speaker>>(list, HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @GetMapping
  @RequestMapping("{id}")
  public ResponseEntity<Speaker> get(@PathVariable("id") Long id){
    try{
      return new ResponseEntity<Speaker>(speakerService.findSpeaker(id), HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Speaker> Create(@RequestBody final Speaker speaker){
    try{
      Speaker newSpeaker = speakerService.createSpeaker(speaker);
      return new ResponseEntity<Speaker>(newSpeaker, HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity delete(@PathVariable Long id){
    try{
      speakerService.deleteSpeaker(id);
      return new ResponseEntity( HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public ResponseEntity<Speaker> update(@PathVariable Long id, @RequestBody final Speaker speaker){
    try{
      Speaker newSpeaker = speakerService.updateSpeaker(speaker, id);
      return new ResponseEntity<Speaker>(newSpeaker, HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

}
