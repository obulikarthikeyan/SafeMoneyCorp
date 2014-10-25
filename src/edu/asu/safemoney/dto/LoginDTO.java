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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginDTO.findAll", query = "SELECT l FROM LoginDTO l"),
    @NamedQuery(name = "LoginDTO.findByMemberId", query = "SELECT l FROM LoginDTO l WHERE l.memberId = :memberId"),
    @NamedQuery(name = "LoginDTO.findByUserName", query = "SELECT l FROM LoginDTO l WHERE l.userName = :userName"),
    @NamedQuery(name = "LoginDTO.findByPassword", query = "SELECT l FROM LoginDTO l WHERE l.password = :password"),
    @NamedQuery(name = "LoginDTO.findBySiteKey", query = "SELECT l FROM LoginDTO l WHERE l.siteKey = :siteKey")})
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "member_id")
    private Integer memberId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "site_key")
    private String siteKey;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private UserDTO userDTO;

    public LoginDTO() {
    }

    public LoginDTO(Integer memberId) {
        this.memberId = memberId;
    }

    public LoginDTO(Integer memberId, String userName, String password, String siteKey) {
        this.memberId = memberId;
        this.userName = userName;
        this.password = password;
        this.siteKey = siteKey;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
        if (!(object instanceof LoginDTO)) {
            return false;
        }
        LoginDTO other = (LoginDTO) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.LoginDTO[ memberId=" + memberId + " ]";
    }
    
}
