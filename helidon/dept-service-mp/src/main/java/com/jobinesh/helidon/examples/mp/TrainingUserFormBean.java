/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobinesh.helidon.examples.mp;

import javax.ws.rs.FormParam;

/**
 * TrainingUserFormBean used as @BeanParam in TrainingUserService class
 *
 * @author Jobinesh, AORCL
 */
public class TrainingUserFormBean {

    @FormParam("userId")
    private String userId;

    @FormParam("departmentId")
    private String departmentId;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

}
