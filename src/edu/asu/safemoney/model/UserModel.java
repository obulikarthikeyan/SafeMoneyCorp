package edu.asu.safemoney.model;

import java.util.Date;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.RegEx;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class UserModel {

	@Nonnull
	@NotEmpty @Size(max=25)
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	String firstName;


	@Nonnull
	@NotEmpty @Size(max=25)
	@Pattern(regexp = "[a-z-A-Z]*", message = "Second name has invalid characters")	
	String lastName;
	
	
	@Nonnull
	@NotEmpty @Email
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")	
	String emailId;
	

	@Nonnull @Min(0) @Size(min=10)
	@Digits(integer = 10, fraction = 0)
	long contactNo;
	
	@Nonnull
	@NotEmpty @Size(max=50)
	@Pattern(regexp = "[a-z-A-Z]*", message = "Address has invalid characters")
	String address1;
	
	@Nonnull
	@NotEmpty @Size(max=50)
	@Pattern(regexp = "[a-z-A-Z]*", message = "Address has invalid characters")
	String address2;
	
	@Nonnull
	@NotEmpty @Size(max=15)
	@Pattern(regexp = "[a-z-A-Z]*", message = "City has invalid characters")
	String city;
	
	@Nonnull
	@NotEmpty @Size(min=2,max=2)
	@Pattern(regexp = "[a-z-A-Z]*", message = "State has invalid characters")
	String state;
	

	@Nonnull @Min(0) @Size(min=5)
	@Digits(integer = 5, fraction = 0)
	long zip;
	

	@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
	Date dateOfBirth;
	
	
	@Nonnull @Min(0) @Size(min=1, max=3)
	@Digits(integer = 3, fraction = 0)
	int age;

	@Nonnull @Min(0) @Size(min=10, max=10)
	@Digits(integer = 9, fraction = 0)
	long ssn;
	
	@Nonnull
	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z]*", message = "Last name has invalid characters")
	String userType;


	@Nonnull
	@NotEmpty @Size(min=5,max=15)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Username only allows numbers and alphabets")
	String userName;

	

	@Nonnull
	@NotEmpty @Size(max=15)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid characters")
	String password;


	@Nonnull
	@NotEmpty @Size(max=200)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security question")	
	String secQuestion1;


	@Nonnull
	@NotEmpty @Size(max=200)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security question")	
	String secQuestion2;


	@Nonnull
	@NotEmpty @Size(max=200)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security question")	
	String secQuestion3;
	
	@Nonnull
	@NotEmpty @Size(max=25)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security answer")	
	String secAnswer1;

	
	@Nonnull
	@NotEmpty @Size(max=25)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security answer")	
	String secAnswer2;

	@Nonnull
	@NotEmpty @Size(max=25)
	@Pattern(regexp = "[a-z-A-Z0-9]*", message = "Invalid Security answer")	
	String secAnswer3;
	
	
	@Nonnull
	@NotEmpty @Size(min=5,max=25)
	@Pattern(regexp = "[a-z-A-Z]*", message = "Invalid Security answer")
	String siteKey;
	
	
	int userTypeMapId;
	
	@Nonnull
	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z]*", message = "Last name has invalid characters")
	String createdBy;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past	
	Date createdDate;

	@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull 
	Date expiryDate;
	
	
	String isActive;
	
	int userTypeId;
	
	String isCustomer;
	
	boolean isEmployee;
	
	@Nonnull
	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z]*", message = "Invalid Designation")	
	String designation;

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(String isCustomer) {
		this.isCustomer = isCustomer;
	}

	public int getUserTypeMapId() {
		return userTypeMapId;
	}

	public void setUserTypeMapId(int userTypeMapId) {
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

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
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

	public String getSecQuestion1() {
		return secQuestion1;
	}

	public void setSecQuestion1(String secQuestion1) {
		this.secQuestion1 = secQuestion1;
	}

	public String getSecQuestion2() {
		return secQuestion2;
	}

	public void setSecQuestion2(String secQuestion2) {
		this.secQuestion2 = secQuestion2;
	}

	public String getSecQuestion3() {
		return secQuestion3;
	}

	public void setSecQuestion3(String secQuestion3) {
		this.secQuestion3 = secQuestion3;
	}

	public String getSecAnswer1() {
		return secAnswer1;
	}

	public void setSecAnswer1(String secAnswer1) {
		this.secAnswer1 = secAnswer1;
	}

	public String getSecAnswer2() {
		return secAnswer2;
	}

	public void setSecAnswer2(String secAnswer2) {
		this.secAnswer2 = secAnswer2;
	}

	public String getSecAnswer3() {
		return secAnswer3;
	}

	public void setSecAnswer3(String secAnswer3) {
		this.secAnswer3 = secAnswer3;
	}

	public String getSiteKey() {
		return siteKey;
	}

	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	
}
