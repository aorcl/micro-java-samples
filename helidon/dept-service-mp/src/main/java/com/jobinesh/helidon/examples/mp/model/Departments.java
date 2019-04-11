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
 * Department JPA entity
 * @author Jobinesh, AORCL
 */
@Entity
@Table(name = "TrainingDepartments")
@XmlRootElement

@NamedQueries({
    @NamedQuery(name = "Departments.findAll", query = "SELECT d FROM TrainingDepartments d"),
    @NamedQuery(name = "Departments.findByDepartmentId", query = "SELECT d FROM TrainingDepartments d WHERE d.departmentId = :departmentId")})
public class Departments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "departmentId")
    private String departmentId;
    @Column(name = "trainingBudget")
    private Short trainingBudget;

    public Departments() {
    }

    public Departments(String departmentId) {
        this.departmentId = departmentId;
    }

    public Departments(String departmentId, Short trainingBudget) {
        this.departmentId = departmentId;
        this.trainingBudget = trainingBudget;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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
        hash += (departmentId != null ? departmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departments)) {
            return false;
        }
        Departments other = (Departments) object;
        if ((this.departmentId == null && other.departmentId != null) || (this.departmentId != null && !this.departmentId.equals(other.departmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Departments{" + "departmentId=" + departmentId + ", trainingBudget=" + trainingBudget + '}';
    }

   
    
}