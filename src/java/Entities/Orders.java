/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 775224
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findByOrderNo", query = "SELECT o FROM Orders o WHERE o.orderNo = :orderNo")
    , @NamedQuery(name = "Orders.findByOrderDatetime", query = "SELECT o FROM Orders o WHERE o.orderDatetime = :orderDatetime")
    , @NamedQuery(name = "Orders.findByDueDatetime", query = "SELECT o FROM Orders o WHERE o.dueDatetime = :dueDatetime")
    , @NamedQuery(name = "Orders.findByOrderItems", query = "SELECT o FROM Orders o WHERE o.orderItems = :orderItems")
    , @NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "order_no")
    private Integer orderNo;
    @Basic(optional = false)
    @Column(name = "order_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDatetime;
    @Basic(optional = false)
    @Column(name = "due_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDatetime;
    @Basic(optional = false)
    @Column(name = "order_items")
    private String orderItems;
    @Basic(optional = false)
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToMany(mappedBy = "ordersCollection")
    private Collection<Cake> cakeCollection;
    @JoinColumn(name = "delivery_no", referencedColumnName = "delivery_no")
    @ManyToOne(optional = false)
    private Delivery deliveryNo;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public Orders() {
    }

    public Orders(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Orders(Integer orderNo, Date orderDatetime, Date dueDatetime, String orderItems, double totalPrice) {
        this.orderNo = orderNo;
        this.orderDatetime = orderDatetime;
        this.dueDatetime = dueDatetime;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Date orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public Date getDueDatetime() {
        return dueDatetime;
    }

    public void setDueDatetime(Date dueDatetime) {
        this.dueDatetime = dueDatetime;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @XmlTransient
    public Collection<Cake> getCakeCollection() {
        return cakeCollection;
    }

    public void setCakeCollection(Collection<Cake> cakeCollection) {
        this.cakeCollection = cakeCollection;
    }

    public Delivery getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(Delivery deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Orders[ orderNo=" + orderNo + " ]";
    }
    
}
