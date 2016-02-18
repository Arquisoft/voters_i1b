package DBManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import DBManagement.util.Jpa;
import model.Voter;

@Repository
@Transactional
public class VoteRepositoryImpl implements VoterRepository {

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Voter arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Voter> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Voter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Voter> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voter findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override @Transactional
	public <S extends Voter> S save(S entity){
		EntityManager em = Jpa.createEntityManager();
		if (entity.getId() == 0) {
		      em.persist(entity);
		      return entity;
		    } else {
		      return em.merge(entity);
		    }
		
	}

	@Override
	public <S extends Voter> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voter findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
