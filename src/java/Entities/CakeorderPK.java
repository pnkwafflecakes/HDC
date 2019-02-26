/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 775224
 */
@Embeddable
public class CakeorderPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "order_no")
    private int orderNo;
    @Basic(optional = false)
    @Column(name = "cake_id")
    private int cakeId;

    public CakeorderPK() {
    }

    public CakeorderPK(int orderNo, int cakeId) {
        this.orderNo = orderNo;
        this.cakeId = cakeId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderNo;
        hash += (int) cakeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CakeorderPK)) {
            return false;
        }
        CakeorderPK other = (CakeorderPK) object;
        if (this.orderNo != other.orderNo) {
            return false;
        }
        if (this.cakeId != other.cakeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.CakeorderPK[ orderNo=" + orderNo + ", cakeId=" + cakeId + " ]";
    }
    
}
