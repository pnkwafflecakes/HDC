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
@Table(name = "delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d")
    , @NamedQuery(name = "Delivery.findByDeliveryNo", query = "SELECT d FROM Delivery d WHERE d.deliveryNo = :deliveryNo")
    , @NamedQuery(name = "Delivery.findByMethod", query = "SELECT d FROM Delivery d WHERE d.method = :method")
    , @NamedQuery(name = "Delivery.findByAddress", query = "SELECT d FROM Delivery d WHERE d.address = :address")
    , @NamedQuery(name = "Delivery.findByPhoneNo", query = "SELECT d FROM Delivery d WHERE d.phoneNo = :phoneNo")
    , @NamedQuery(name = "Delivery.findByNotes", query = "SELECT d FROM Delivery d WHERE d.notes = :notes")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "delivery_no")
    private Integer deliveryNo;
    @Basic(optional = false)
    @Column(name = "method")
    private String method;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "phone_no")
    private String phoneNo;
    @Basic(optional = false)
    @Column(name = "notes")
    private String notes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryNo")
    private Collection<Orders> ordersCollection;

    public Delivery() {
    }

    public Delivery(Integer deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Delivery(Integer deliveryNo, String method, String address, String phoneNo, String notes) {
        this.deliveryNo = deliveryNo;
        this.method = method;
        this.address = address;
        this.phoneNo = phoneNo;
        this.notes = notes;
    }

    public Integer getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(Integer deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryNo != null ? deliveryNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.deliveryNo == null && other.deliveryNo != null) || (this.deliveryNo != null && !this.deliveryNo.equals(other.deliveryNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Delivery[ deliveryNo=" + deliveryNo + " ]";
    }
    
}
