/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.safemoney.dto;

import java.io.Serializable;
import java.util.List;
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
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDTO.findAll", query = "SELECT u FROM UserDTO u"),
    @NamedQuery(name = "UserDTO.findByMemberId", query = "SELECT u FROM UserDTO u WHERE u.memberId = :memberId"),
    @NamedQuery(name = "UserDTO.findByFirstName", query = "SELECT u FROM UserDTO u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UserDTO.findByLastName", query = "SELECT u FROM UserDTO u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UserDTO.findByEmailId", query = "SELECT u FROM UserDTO u WHERE u.emailId = :emailId")})
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "member_id")
    private Integer memberId;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "email_id")
    private String emailId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private List<MemberUserTypeMapDTO> memberUserTypeMapDTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<LoginDTO> loginDTOList;

    public UserDTO() {
    }

    public UserDTO(Integer memberId) {
        this.memberId = memberId;
    }

    public UserDTO(Integer memberId, String firstName, String lastName, String emailId) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @XmlTransient
    public List<MemberUserTypeMapDTO> getMemberUserTypeMapDTOList() {
        return memberUserTypeMapDTOList;
    }

    public void setMemberUserTypeMapDTOList(List<MemberUserTypeMapDTO> memberUserTypeMapDTOList) {
        this.memberUserTypeMapDTOList = memberUserTypeMapDTOList;
    }

    @XmlTransient
    public List<LoginDTO> getLoginDTOList() {
        return loginDTOList;
    }

    public void setLoginDTOList(List<LoginDTO> loginDTOList) {
        this.loginDTOList = loginDTOList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage1.UserDTO[ memberId=" + memberId + " ]";
    }
    
}
