/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 775224
 */
@Entity
@Table(name = "cakecategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cakecategory.findAll", query = "SELECT c FROM Cakecategory c")
    , @NamedQuery(name = "Cakecategory.findByCategoryId", query = "SELECT c FROM Cakecategory c WHERE c.categoryId = :categoryId")
    , @NamedQuery(name = "Cakecategory.findByName", query = "SELECT c FROM Cakecategory c WHERE c.name = :name")
    , @NamedQuery(name = "Cakecategory.findByDescription", query = "SELECT c FROM Cakecategory c WHERE c.description = :description")})
public class Cakecategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<Cake> cakeCollection;

    public Cakecategory() {
    }

    public Cakecategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Cakecategory(Integer categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Cake> getCakeCollection() {
        return cakeCollection;
    }

    public void setCakeCollection(Collection<Cake> cakeCollection) {
        this.cakeCollection = cakeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cakecategory)) {
            return false;
        }
        Cakecategory other = (Cakecategory) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cakecategory[ categoryId=" + categoryId + " ]";
    }
    
}
