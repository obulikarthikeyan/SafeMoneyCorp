/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.safemoney.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestDTO.findAll", query = "SELECT r FROM RequestDTO r"),
    @NamedQuery(name = "RequestDTO.findByRequestId", query = "SELECT r FROM RequestDTO r WHERE r.requestId = :requestId"),
    @NamedQuery(name = "RequestDTO.findByRequestType", query = "SELECT r FROM RequestDTO r WHERE r.requestType = :requestType"),
    @NamedQuery(name = "RequestDTO.findByAuthorizingMemberId", query = "SELECT r FROM RequestDTO r WHERE r.authorizingMemberId = :authorizingMemberId"),
    @NamedQuery(name = "RequestDTO.findByStatus", query = "SELECT r FROM RequestDTO r WHERE r.status = :status")})
public class RequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "request_id")
    private Long requestId;
    @Size(max = 45)
    @Column(name = "request_type")
    private String requestType;
    @Column(name = "authorizing_member_id")
    private Integer authorizingMemberId;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne(optional = false)
    private UserDTO memberId;

    public RequestDTO() {
    }

    public RequestDTO(Long requestId) {
        this.requestId = requestId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getAuthorizingMemberId() {
        return authorizingMemberId;
    }

    public void setAuthorizingMemberId(Integer authorizingMemberId) {
        this.authorizingMemberId = authorizingMemberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getMemberId() {
        return memberId;
    }

    public void setMemberId(UserDTO memberId) {
        this.memberId = memberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequestDTO)) {
            return false;
        }
        RequestDTO other = (RequestDTO) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.RequestDTO[ requestId=" + requestId + " ]";
    }
    
}
