package org.example.model;

import java.util.Objects;

public class CustomerDetails {

    private String fistName;
    private String lastName;
    private String dateOfBirth;


    public CustomerDetails() {
        super();
        this.fistName = fistName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    public String getFistName() {
        return fistName;
    }
    public void setFistName(String fistName) {
        this.fistName = fistName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, fistName, lastName);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerDetails other = (CustomerDetails) obj;
        return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(fistName, other.fistName)
                && Objects.equals(lastName, other.lastName);
    }
}
