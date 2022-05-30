package individualprojectpartb.entities;

import java.time.LocalDate;
import java.util.Objects;


public class Student {
    
    private int StId;
    private String firstName;
    private String lastName;
    private int tuitionFees;
    private LocalDate dateOfBirth;

    //all the basic methods for the entity student
    public Student(int StId, String firstName, String lastName, int tuitionFees, LocalDate dateOfBirth) {
        this.StId = StId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFees = tuitionFees;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(String firstName, String lastName, int tuitionFees, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFees = tuitionFees;
        this.dateOfBirth = dateOfBirth;
    }

    public int getStId() {
        return StId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setStId(int StId) {
        this.StId = StId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.StId;
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + this.tuitionFees;
        hash = 97 * hash + Objects.hashCode(this.dateOfBirth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.StId != other.StId) {
            return false;
        }
        if (this.tuitionFees != other.tuitionFees) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "StId=" + StId + ", firstName=" + firstName + ", lastName=" + lastName + ", tuitionFees=" + tuitionFees + ", dateOfBirth=" + dateOfBirth + '}';
    }
    
    
    
}
