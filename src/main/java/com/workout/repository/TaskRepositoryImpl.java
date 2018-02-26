package com.workout.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.workout.model.Task;
import com.workout.model.User;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
	 @PersistenceContext
	    private EntityManager entityManager;
	@Override
	@Transactional
	public void persist(final Task task) {
		entityManager.persist(task);
	}
	@Override
	public List<Task> getAllTasks()
		
		{
	        return entityManager.createQuery("select task from Task task").getResultList();
	    }
	
	 @Override
	 @Transactional
	    public void updateTask(Task task)
	    {
	        entityManager.merge(task);
	    }
	/* @Override
	  @Transactional
	    public void deleteUser(int user_ID)
	    {
		  User user = entityManager.find(User.class,user_ID);
	        entityManager.remove(user);
	    }
*/
}
	 
