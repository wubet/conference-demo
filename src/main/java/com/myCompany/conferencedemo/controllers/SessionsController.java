package com.myCompany.conferencedemo.controllers;

import com.myCompany.conferencedemo.Exception.ApplicationNotFoundException;
import com.myCompany.conferencedemo.models.Session;
import com.myCompany.conferencedemo.services.SessionService;
import java.util.List;
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
@RequestMapping("api/v1/sessions")
public class SessionsController {

  @Autowired
  private SessionService sessionService;

  @GetMapping
  public ResponseEntity<List<Session>> list(){
    try{
      List<Session> list = sessionService.listAllSessions();
      return new ResponseEntity<List<Session>>(list, HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @GetMapping
  @RequestMapping("{id}")
  public ResponseEntity<Session> get(@PathVariable("id") Long id){
    try{
      return new ResponseEntity<Session>(sessionService.findSession(id), HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Session> Create(@RequestBody final Session session){
    try{
      Session newSession = sessionService.createSession(session);
      return new ResponseEntity<Session>(newSession, HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity delete(@PathVariable Long id){
    try{
      sessionService.deleteSession(id);
      return new ResponseEntity( HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public ResponseEntity<Session> update(@PathVariable Long id, @RequestBody final Session session){
    try{
      Session newSession = sessionService.updateSession(session, id);
      return new ResponseEntity<Session>(newSession, HttpStatus.OK);
    } catch(ApplicationNotFoundException exception){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
  }

}
