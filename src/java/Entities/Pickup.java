/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 775224
 */
@Entity
@Table(name = "pickup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pickup.findAll", query = "SELECT p FROM Pickup p")
    , @NamedQuery(name = "Pickup.findByPickupId", query = "SELECT p FROM Pickup p WHERE p.pickupId = :pickupId")
    , @NamedQuery(name = "Pickup.findByPickupName", query = "SELECT p FROM Pickup p WHERE p.pickupName = :pickupName")
    , @NamedQuery(name = "Pickup.findByPickupAddress", query = "SELECT p FROM Pickup p WHERE p.pickupAddress = :pickupAddress")})
public class Pickup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pickup_id")
    private Integer pickupId;
    @Basic(optional = false)
    @Column(name = "pickup_name")
    private String pickupName;
    @Basic(optional = false)
    @Column(name = "pickup_address")
    private String pickupAddress;

    public Pickup() {
    }

    public Pickup(Integer pickupId) {
        this.pickupId = pickupId;
    }

    public Pickup(Integer pickupId, String pickupName, String pickupAddress) {
        this.pickupId = pickupId;
        this.pickupName = pickupName;
        this.pickupAddress = pickupAddress;
    }

    public Integer getPickupId() {
        return pickupId;
    }

    public void setPickupId(Integer pickupId) {
        this.pickupId = pickupId;
    }

    public String getPickupName() {
        return pickupName;
    }

    public void setPickupName(String pickupName) {
        this.pickupName = pickupName;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pickupId != null ? pickupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pickup)) {
            return false;
        }
        Pickup other = (Pickup) object;
        if ((this.pickupId == null && other.pickupId != null) || (this.pickupId != null && !this.pickupId.equals(other.pickupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Pickup[ pickupId=" + pickupId + " ]";
    }
    
}
