/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.safemoney.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "member_user_type_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MemberUserTypeMapDTO.findAll", query = "SELECT m FROM MemberUserTypeMapDTO m"),
    @NamedQuery(name = "MemberUserTypeMapDTO.findByUserTypeMapId", query = "SELECT m FROM MemberUserTypeMapDTO m WHERE m.userTypeMapId = :userTypeMapId"),
    @NamedQuery(name = "MemberUserTypeMapDTO.findByCreatedBy", query = "SELECT m FROM MemberUserTypeMapDTO m WHERE m.createdBy = :createdBy"),
    @NamedQuery(name = "MemberUserTypeMapDTO.findByCreatedDate", query = "SELECT m FROM MemberUserTypeMapDTO m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "MemberUserTypeMapDTO.findByExpiryDate", query = "SELECT m FROM MemberUserTypeMapDTO m WHERE m.expiryDate = :expiryDate"),
    @NamedQuery(name = "MemberUserTypeMapDTO.findByIsActive", query = "SELECT m FROM MemberUserTypeMapDTO m WHERE m.isActive = :isActive")})
public class MemberUserTypeMapDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_type_map_id")
    private Integer userTypeMapId;
    @Basic(optional = false)
    @Column(name = "created_by")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Basic(optional = false)
    @Column(name = "is_active")
    private String isActive;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)
    private UserDTO memberId;
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
    @ManyToOne(optional = false)
    private UserTypeDTO userTypeId;
    public MemberUserTypeMapDTO() {
    }

    public MemberUserTypeMapDTO(Integer userTypeMapId) {
        this.userTypeMapId = userTypeMapId;
    }

    public MemberUserTypeMapDTO(Integer userTypeMapId, String createdBy, Date createdDate, Date expiryDate, String isActive) {
        this.userTypeMapId = userTypeMapId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
    }

    public Integer getUserTypeMapId() {
        return userTypeMapId;
    }

    public void setUserTypeMapId(Integer userTypeMapId) {
        this.userTypeMapId = userTypeMapId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public UserDTO getMemberId() {
        return memberId;
    }

    public void setMemberId(UserDTO memberId) {
        this.memberId = memberId;
    }

    public UserTypeDTO getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserTypeDTO userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userTypeMapId != null ? userTypeMapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberUserTypeMapDTO)) {
            return false;
        }
        MemberUserTypeMapDTO other = (MemberUserTypeMapDTO) object;
        if ((this.userTypeMapId == null && other.userTypeMapId != null) || (this.userTypeMapId != null && !this.userTypeMapId.equals(other.userTypeMapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage1.MemberUserTypeMapDTO[ userTypeMapId=" + userTypeMapId + " ]";
    }
    
}
