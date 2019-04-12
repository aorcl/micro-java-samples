/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobinesh.helidon.examples.mp;

import com.jobinesh.helidon.examples.mp.model.Departments;
import com.jobinesh.helidon.examples.mp.model.TrainingUsers;
import com.jobinesh.helidon.examples.mp.model.PersistenceManager;
//import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jobinesh, AORCL
 */
@Path("/BudgetApi")
//@RequestScoped
public class BudgetApiResource {

    //private final DataSource dataSource = null;
    private static final Logger logger = Logger.getLogger(BudgetApiResource.class.getName());

    @GET
    @Path("/departments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Departments> getAllDepartments() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Departments.class));
        List<Departments> departments = em.createQuery(cq).getResultList();
        System.out.println("departments:" + departments);
        return departments;
    }

    /**
     * Creates Departments entity
     *
     * @param entity
     */
    @POST
    @Path("/departments")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createDepartment(Departments entity) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        commitTxn(em);
    }

    /**
     * Get total departments count
     *
     * @return
     */
    @GET
    @Path("departments/count")
    @Produces("text/plain")
    public int countREST() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Departments> rt = cq.from(Departments.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

    /**
     * Creates a department. This method demonstrates @FormParam
     *
     * @param departmentId
     * @param trainingBudget
     */
    @POST
    @Path("departments/form")
    public void createDepartment(
            @FormParam("departmentId") String departmentId,
            @FormParam("trainingBudget") Short trainingBudget) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        Departments entity = new Departments();
        entity.setDepartmentId(departmentId);
        entity.setTrainingBudget(trainingBudget);
        em.getTransaction().begin();
        em.persist(entity);
        commitTxn(em);
    }

    /**
     * Creates a department This method demonstrates @BeanParam
     *
     * @param deptBean
     */
    @POST
    @Path("departments/form/bean")
    public void createDepartment(@BeanParam DepartmentFormBean deptBean) {
        createDepartment(deptBean.getDepartmentId(),
                deptBean.getTrainingBudget());
    }

    /**
     * Modifies department
     *
     * @param id
     * @param entity
     */
    @PUT
    @Path("departments/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editDepartment(@PathParam("id") String id, Departments entity) {
        logger.log(Level.INFO, "Departments: " + entity.toString());

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        commitTxn(em);
    }

    /**
     * Deletes a department
     *
     * @param id
     */
    @DELETE
    @Path("departments/{id}")
    public void removeDepartment(@PathParam("id") String id) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        Departments entity = em.find(Departments.class, id);

        em.getTransaction().begin();
        em.remove(em.merge(entity));
        commitTxn(em);

    }

    /**
     * Finds a department by name
     *
     * @param name
     * @return
     */
    @GET
    @Path("departments/{name: [a-zA-Z][a-zA-Z_0-9]}")
    @Produces(MediaType.APPLICATION_JSON)
    public Departments findDepartmentByName(@PathParam("name") String name) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        Query queryDepartmentsByName = em.createNamedQuery("Departments.findByDepartmentName");
        queryDepartmentsByName.setParameter("departmentName", name);
        Departments department = (Departments) queryDepartmentsByName.getSingleResult();
        return department;
    }

    /**
     * Finds a department by id
     *
     * @param id
     * @return
     */
    @GET
    @Path("departments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Departments findDepartment(@PathParam("id") String id) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        return em.find(Departments.class, id);
    }

    /**
     * Returns list of departments
     *
     * @param name
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("departments/query")
    public List<Departments> findAllDepartmentsWithQueryParam(@QueryParam("name") String name) {
        //Find all departments from the data store
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        logger.log(Level.INFO, "findAllDepartmentsWithQueryParam name:" + name);
        Query queryDepartmentsByName = em.createNamedQuery("Departments.findByDepartmentName");
        queryDepartmentsByName.setParameter("departmentName", name);
        List<Departments> departments = queryDepartmentsByName.getResultList();
        logger.log(Level.INFO, departments.toString());
        return departments;
    }

    /**
     * Returns list of departments. This method demonstrates @MatrixParam
     *
     * @param name
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("departments/matrix")
    public List<Departments> findAllDepartmentsWithMatrixParam(@MatrixParam("name") String name) {
        //Find all departments from the data store
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        Query queryDepartmentsByName = em.createNamedQuery("Departments.findByDepartmentName");
        queryDepartmentsByName.setParameter("departmentName", "%" + name + "%");
        List<Departments> departments = queryDepartmentsByName.getResultList();

        logger.log(Level.INFO, departments.toString());

        return departments;
    }


    // TrainingUsers API

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrainingUsers> getAllTrainingUsers() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(TrainingUsers.class));
        List<TrainingUsers> TrainingUsers = em.createQuery(cq).getResultList();
        System.out.println("users:" + TrainingUsers);
        return TrainingUsers;
    }

    /**
     * Creates TrainingUsers entity
     *
     * @param entity
     */
    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTrainingUser(TrainingUsers entity) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        commitTxn(em);
    }

    /**
     * Get total users count
     *
     * @return
     */
    @GET
    @Path("users/count")
    @Produces("text/plain")
    public int countTrainingUsersREST() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<TrainingUsers> rt = cq.from(TrainingUsers.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

    /**
     * Creates a user. This method demonstrates @FormParam
     *
     * @param userId
     * @param departmentID
     */
    @POST
    @Path("users/form")
    public void createTrainingUser(
            @FormParam("userId") String userId,
            @FormParam("departmentID") String departmentID) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        TrainingUsers entity = new TrainingUsers();
        entity.setUserId(userId);
        entity.setDepartmentId(departmentID);
        em.getTransaction().begin();
        em.persist(entity);
        commitTxn(em);
    }

    /**
     * Creates a user This method demonstrates @BeanParam
     *
     * @param userBean
     */
    @POST
    @Path("users/form/bean")
    public void createTrainingUser(@BeanParam TrainingUserFormBean userBean) {
        createTrainingUser(userBean.getUserId(),
        userBean.getUserId());
    }

    /**
     * Modifies user
     *
     * @param id
     * @param entity
     */
    @PUT
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editTrainingUser(@PathParam("id") String id, TrainingUsers entity) {
        logger.log(Level.INFO, "TrainingUsers: " + entity.toString());

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        commitTxn(em);
    }


    /**
     * Finds a user by id
     *
     * @param id
     * @return
     */
    @GET
    @Path("users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TrainingUsers findTrainingUser(@PathParam("id") String id) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        return em.find(TrainingUsers.class, id);
    }



    private void commitTxn(EntityManager em) {
        em.getTransaction().commit();
        em.close();

    }

}
