/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 775224
 */
@Entity
@Table(name = "cake")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cake.findAll", query = "SELECT c FROM Cake c")
    , @NamedQuery(name = "Cake.findByCakeId", query = "SELECT c FROM Cake c WHERE c.cakeId = :cakeId")
    , @NamedQuery(name = "Cake.findByName", query = "SELECT c FROM Cake c WHERE c.name = :name")
    , @NamedQuery(name = "Cake.findBySize", query = "SELECT c FROM Cake c WHERE c.size = :size")
    , @NamedQuery(name = "Cake.findByPrice", query = "SELECT c FROM Cake c WHERE c.price = :price")
    , @NamedQuery(name = "Cake.findByDescription", query = "SELECT c FROM Cake c WHERE c.description = :description")
    , @NamedQuery(name = "Cake.findByImage", query = "SELECT c FROM Cake c WHERE c.image = :image")})
public class Cake implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cake_id")
    private Integer cakeId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "size")
    private Integer size;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @JoinTable(name = "cakeorder", joinColumns = {
        @JoinColumn(name = "cake_id", referencedColumnName = "cake_id")}, inverseJoinColumns = {
        @JoinColumn(name = "order_no", referencedColumnName = "order_no")})
    @ManyToMany
    private Collection<Order> order1Collection;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private Cakecategory categoryId;

    public Cake() {
    }

    public Cake(Integer cakeId) {
        this.cakeId = cakeId;
    }

    public Cake(Integer cakeId, String name, double price, String description, String image) {
        this.cakeId = cakeId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Integer getCakeId() {
        return cakeId;
    }

    public void setCakeId(Integer cakeId) {
        this.cakeId = cakeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public Collection<Order> getOrder1Collection() {
        return order1Collection;
    }

    public void setOrder1Collection(Collection<Order> order1Collection) {
        this.order1Collection = order1Collection;
    }

    public Cakecategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Cakecategory categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cakeId != null ? cakeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cake)) {
            return false;
        }
        Cake other = (Cake) object;
        if ((this.cakeId == null && other.cakeId != null) || (this.cakeId != null && !this.cakeId.equals(other.cakeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cake[ cakeId=" + cakeId + " ]";
    }
    
}
