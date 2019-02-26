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
@Table(name = "accounttype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounttype.findAll", query = "SELECT a FROM Accounttype a")
    , @NamedQuery(name = "Accounttype.findByAccountType", query = "SELECT a FROM Accounttype a WHERE a.accountType = :accountType")
    , @NamedQuery(name = "Accounttype.findByName", query = "SELECT a FROM Accounttype a WHERE a.name = :name")})
public class Accounttype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "account_type")
    private Integer accountType;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountType")
    private Collection<User> userCollection;

    public Accounttype() {
    }

    public Accounttype(Integer accountType) {
        this.accountType = accountType;
    }

    public Accounttype(Integer accountType, String name) {
        this.accountType = accountType;
        this.name = name;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountType != null ? accountType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounttype)) {
            return false;
        }
        Accounttype other = (Accounttype) object;
        if ((this.accountType == null && other.accountType != null) || (this.accountType != null && !this.accountType.equals(other.accountType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Accounttype[ accountType=" + accountType + " ]";
    }
    
}
