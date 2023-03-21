package com.jincomp.jintest.web.jin.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class UserVO implements Serializable {

   public static final long serialVersionUID = 1L;
//   private String empNo;
//   private String birthDate;
//   private String firstName;
//   private String lastName;
//   private String gender;
//   private String hireDate;
   
   private String empNo;
	private String birthDate;
	private String name;
	private String gender;
	private String hireDate;
   
   
   
   
//	   
//	public String getEmpNo() {
//		return empNo;
//	}
//	public void setEmpNo(String empNo) {
//		this.empNo = empNo;
//	}
//	public String getBirthDate() {
//		return birthDate;
//	}
//	public void setBirthDate(String birthDate) {
//		this.birthDate = birthDate;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getHireDate() {
//		return hireDate;
//	}
//	public void setHireDate(String hireDate) {
//		this.hireDate = hireDate;
//	}
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
    

}