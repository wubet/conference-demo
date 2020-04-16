package com.myCompany.conferencedemo.services;

import com.myCompany.conferencedemo.Exception.ApplicationNotFoundException;
import com.myCompany.conferencedemo.models.Session;
import com.myCompany.conferencedemo.models.Speaker;
import com.myCompany.conferencedemo.repositories.SpeakerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {

  @Autowired
  private SpeakerRepository speakerRepository;

  public List<Speaker> listAllSpeakers(){
    List<Speaker> optionalSpeaker = null;
    try{
      optionalSpeaker = speakerRepository.findAll();
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return optionalSpeaker;
  }

  public Speaker findSpeaker(Long id){
    Speaker speaker = null;
    try{
      Optional<Speaker> optionalSpeaker = speakerRepository.findById(id);
      if(optionalSpeaker.isPresent()){
        speaker = optionalSpeaker.get();
      }
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return speaker;
  }

  public Speaker createSpeaker(Speaker speaker){
    Speaker newSpeaker = null;
    try{
      if(speaker != null){
        newSpeaker = speakerRepository.saveAndFlush(speaker);
      }
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return newSpeaker;
  }

  public Speaker updateSpeaker(Speaker speaker, Long id){
    Speaker newSpeaker = null;
    try{
      Speaker existingSpeaker =  speakerRepository.getOne(id);
      BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
      newSpeaker = speakerRepository.saveAndFlush(existingSpeaker);
    }catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
    return newSpeaker;
  }

  public void deleteSpeaker(Long id){
    try{
      speakerRepository.deleteById(id);
    }
    catch(Exception ex){
      throw new ApplicationNotFoundException(ex.getMessage());
    }
  }


}
