package com.workout.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.workout.model.Project;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
	 @PersistenceContext
	    private EntityManager entityManager;
	@Override
	@Transactional
	public void persist(final Project project) {
		entityManager.persist(project);
	}
	@Override
	 @Transactional
	public void updateProject(Project project) {
	 entityManager.merge(project);
		
	}
	@Override
	 @Transactional
	public void suspendProject(int project_ID) {
		Query query = entityManager.createQuery("UPDATE Task t SET t.status = ?1 WHERE t.project_ID = ?2");

		query.setParameter(1, "suspend");
		query.setParameter(2, project_ID);
		query.executeUpdate();
	
		
	}
	@Override
	public List getAllProjects() {
		 return entityManager.createQuery("select project from Project project").getResultList();
	}

}
