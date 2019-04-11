/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobinesh.helidon.examples.mp;

import javax.ws.rs.FormParam;

/**
 * DepartmentFormBean used as @BeanParam in DepartmentService class
 *
 * @author Jobinesh, AORCL
 */
public class DepartmentFormBean {

    @FormParam("departmentId")
    private String departmentId;

    @FormParam("trainingBudget")
    private Short trainingBudget;

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the trainingBudget
     */
    public Short getTrainingBudget() {
        return trainingBudget;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setTrainingBudget(Short trainingBudget) {
        this.trainingBudget = trainingBudget;
    }

}
