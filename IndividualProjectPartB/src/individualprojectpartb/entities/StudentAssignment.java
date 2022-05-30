package individualprojectpartb.entities;

import java.util.Objects;

public class StudentAssignment {
    
    private int STASID;
    private Student student;
    private Assignment assignment;

    
    //all the basic methods for the entity StudentAssignment
    public StudentAssignment(int STASID, Student student, Assignment assignment) {
        this.STASID = STASID;
        this.student = student;
        this.assignment = assignment;
    }

    public StudentAssignment(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
    }

  

    public int getSTASID() {
        return STASID;
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setSTASID(int STASID) {
        this.STASID = STASID;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.STASID;
        hash = 61 * hash + Objects.hashCode(this.student);
        hash = 61 * hash + Objects.hashCode(this.assignment);
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
        final StudentAssignment other = (StudentAssignment) obj;
        if (this.STASID != other.STASID) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("STAS_ID=").append(getSTASID()).
                append(" Student :").append("STID=").append(student.getStId()).
                append(", first name=").append(student.getFirstName()).append(", last name=").append(student.getLastName()).
                append(" Assignment :").append("ASID=").append(assignment.getAsId()).
                append(", type=").append(assignment.getType()).append(", description=").append(assignment.getDescription())
                .append('}');
        return builder.toString();
    }
    
    
}
