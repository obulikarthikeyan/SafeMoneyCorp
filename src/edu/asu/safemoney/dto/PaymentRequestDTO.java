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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "payment_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentRequestDTO.findAll", query = "SELECT p FROM PaymentRequestDTO p"),
    @NamedQuery(name = "PaymentRequestDTO.findByPaymentId", query = "SELECT p FROM PaymentRequestDTO p WHERE p.paymentId = :paymentId"),
    @NamedQuery(name = "PaymentRequestDTO.findByMerchantAccountId", query = "SELECT p FROM PaymentRequestDTO p WHERE p.merchantAccountId = :merchantAccountId"),
    @NamedQuery(name = "PaymentRequestDTO.findByDate", query = "SELECT p FROM PaymentRequestDTO p WHERE p.date = :date"),
    @NamedQuery(name = "PaymentRequestDTO.findByAmount", query = "SELECT p FROM PaymentRequestDTO p WHERE p.amount = :amount"),
    @NamedQuery(name = "PaymentRequestDTO.findByMerchantFirstName", query = "SELECT p FROM PaymentRequestDTO p WHERE p.merchantFirstName = :merchantFirstName"),
    @NamedQuery(name = "PaymentRequestDTO.findByMerchantLastName", query = "SELECT p FROM PaymentRequestDTO p WHERE p.merchantLastName = :merchantLastName"),
    @NamedQuery(name = "PaymentRequestDTO.findByAuthorizerMemberId", query = "SELECT p FROM PaymentRequestDTO p WHERE p.authorizerMemberId = :authorizerMemberId"),
    @NamedQuery(name = "PaymentRequestDTO.findByAuthorizerAccountId", query = "SELECT p FROM PaymentRequestDTO p WHERE p.authorizerAccountId = :authorizerAccountId"),
    @NamedQuery(name = "PaymentRequestDTO.findByStatus", query = "SELECT p FROM PaymentRequestDTO p WHERE p.status = :status")})
public class PaymentRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "merchant_account_id", nullable = false)
    private long merchantAccountId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount", nullable = false)
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "merchant_first_name", nullable = false, length = 25)
    private String merchantFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "merchant_last_name", nullable = false, length = 25)
    private String merchantLastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "authorizer_member_id", nullable = false)
    private int authorizerMemberId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "authorizer_account_id", nullable = false)
    private long authorizerAccountId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "status", nullable = false, length = 15)
    private String status;
    @JoinColumn(name = "merchant_member_id", referencedColumnName = "member_id", nullable = false)
    @ManyToOne(optional = false)
    private UserDTO merchantMemberId;

    public PaymentRequestDTO() {
    }

    public PaymentRequestDTO(Long paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentRequestDTO(Long paymentId, long merchantAccountId, Date date, double amount, String merchantFirstName, String merchantLastName, int authorizerMemberId, long authorizerAccountId, String status) {
        this.paymentId = paymentId;
        this.merchantAccountId = merchantAccountId;
        this.date = date;
        this.amount = amount;
        this.merchantFirstName = merchantFirstName;
        this.merchantLastName = merchantLastName;
        this.authorizerMemberId = authorizerMemberId;
        this.authorizerAccountId = authorizerAccountId;
        this.status = status;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public long getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(long merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMerchantFirstName() {
        return merchantFirstName;
    }

    public void setMerchantFirstName(String merchantFirstName) {
        this.merchantFirstName = merchantFirstName;
    }

    public String getMerchantLastName() {
        return merchantLastName;
    }

    public void setMerchantLastName(String merchantLastName) {
        this.merchantLastName = merchantLastName;
    }

    public int getAuthorizerMemberId() {
        return authorizerMemberId;
    }

    public void setAuthorizerMemberId(int authorizerMemberId) {
        this.authorizerMemberId = authorizerMemberId;
    }

    public long getAuthorizerAccountId() {
        return authorizerAccountId;
    }

    public void setAuthorizerAccountId(long authorizerAccountId) {
        this.authorizerAccountId = authorizerAccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(UserDTO merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentRequestDTO)) {
            return false;
        }
        PaymentRequestDTO other = (PaymentRequestDTO) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.PaymentRequestDTO[ paymentId=" + paymentId + " ]";
    }
    
}
