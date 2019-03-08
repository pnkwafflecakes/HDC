/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 775224
 */
@Entity
@Table(name = "cakeorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cakeorder.findAll", query = "SELECT c FROM Cakeorder c")
    , @NamedQuery(name = "Cakeorder.findByOrderNo", query = "SELECT c FROM Cakeorder c WHERE c.cakeorderPK.orderNo = :orderNo")
    , @NamedQuery(name = "Cakeorder.findByCakeId", query = "SELECT c FROM Cakeorder c WHERE c.cakeorderPK.cakeId = :cakeId")
    , @NamedQuery(name = "Cakeorder.findByQuantity", query = "SELECT c FROM Cakeorder c WHERE c.quantity = :quantity")})
public class Cakeorder implements Serializable {

    @Column(name = "quantity")
    private Integer quantity;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CakeorderPK cakeorderPK;
    @JoinColumn(name = "cake_id", referencedColumnName = "cake_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cake cake;
    @JoinColumn(name = "order_no", referencedColumnName = "order_no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;

    public Cakeorder() {
    }

    public Cakeorder(CakeorderPK cakeorderPK) {
        this.cakeorderPK = cakeorderPK;
    }

    public Cakeorder(CakeorderPK cakeorderPK, int quantity) {
        this.cakeorderPK = cakeorderPK;
        this.quantity = quantity;
    }

    public Cakeorder(int orderNo, int cakeId) {
        this.cakeorderPK = new CakeorderPK(orderNo, cakeId);
    }

    public CakeorderPK getCakeorderPK() {
        return cakeorderPK;
    }

    public void setCakeorderPK(CakeorderPK cakeorderPK) {
        this.cakeorderPK = cakeorderPK;
    }


    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cakeorderPK != null ? cakeorderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cakeorder)) {
            return false;
        }
        Cakeorder other = (Cakeorder) object;
        if ((this.cakeorderPK == null && other.cakeorderPK != null) || (this.cakeorderPK != null && !this.cakeorderPK.equals(other.cakeorderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cakeorder[ cakeorderPK=" + cakeorderPK + " ]";
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
