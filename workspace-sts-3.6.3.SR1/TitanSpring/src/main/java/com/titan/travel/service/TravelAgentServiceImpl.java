package com.titan.travel.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.titan.travel.domain.Cabin;

@Service
public class TravelAgentServiceImpl implements TravelAgentService {

   @PersistenceContext
   private EntityManager entityManager;

   @Transactional
   public long createCabin(Cabin cabin) {
      entityManager.persist(cabin);
      entityManager.flush();
      return cabin.getId();
   }

   @Transactional(readOnly=true)
   public Cabin findCabin(long id) {
      Cabin cabin = entityManager.find(Cabin.class, id);
      return cabin;
   }
}
