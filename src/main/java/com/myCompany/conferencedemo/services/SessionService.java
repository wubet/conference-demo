package com.myCompany.conferencedemo.services;

import com.myCompany.conferencedemo.Exception.ApplicationNotFoundException;
import com.myCompany.conferencedemo.models.Session;
import com.myCompany.conferencedemo.repositories.SessionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

  @Autowired
  private SessionRepository sessionRepository;

  public List<Session> listAllSessions(){
    List<Session> optionalSessions = null;
    try{
      optionalSessions = sessionRepository.findAll();
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return optionalSessions;
  }

  public Session findSession(Long id){
    Session session = null;
    try{
      Optional<Session> optionalSession = sessionRepository.findById(id);
      if(optionalSession.isPresent()){
        session = optionalSession.get();
      }
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return session;
  }

  public Session createSession(Session session){
    Session newSession = null;
    try{
      if(session != null){
        newSession = sessionRepository.saveAndFlush(session);
      }
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return newSession;
  }

  public Session updateSession(Session session, Long id){
    Session newSession = null;
    try{
      Session existingSession =  sessionRepository.getOne(id);
      BeanUtils.copyProperties(session, existingSession, "session_id");
      newSession = sessionRepository.saveAndFlush(existingSession);
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return newSession;
  }

  public void deleteSession(Long id){
    try{
      sessionRepository.deleteById(id);
    }
    catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
  }

}
