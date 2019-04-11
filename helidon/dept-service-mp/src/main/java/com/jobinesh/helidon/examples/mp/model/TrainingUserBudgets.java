/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobinesh.helidon.examples.mp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Users JPA entity
 * @author AORCL
 */
@Entity
@Table(name = "TrainingUserBudgets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainingUserBudgets.findAll", query = "SELECT u FROM TrainingUserBudgets u"),
    @NamedQuery(name = "TrainingUserBudgets.findByUserId", query = "SELECT u FROM TrainingUserBudgets u WHERE u.userId = :userId")})
public class TrainingUserBudgets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "userId")
    private String userId;
    @Column(name = "trainingBudget")
    private Short trainingBudget;

    public TrainingUserBudgets() {
    }

    public TrainingUserBudgets(String userId) {
        this.userId = userId;
    }

    public TrainingUserBudgets(String userId, Short trainingBudget) {
        this.userId = userId;
        this.trainingBudget = trainingBudget;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Short getTrainingBudget() {
        return trainingBudget;
    }

    public void setTrainingBudget(Short trainingBudget) {
        this.trainingBudget = trainingBudget;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departments)) {
            return false;
        }
        TrainingUserBudgets other = (TrainingUserBudgets) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrainingUserBudgets{" + "userId=" + userId + ", trainingBudget=" + trainingBudget + '}';
    }

   
    
}