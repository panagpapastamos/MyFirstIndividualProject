package individualprojectpartb.entities;

import java.util.Objects;

public class CourseAssignment {
    private int COASID;
    private Course course;
    private Assignment assignment;

    //all the basic methods for the entity Courseassignment
    public CourseAssignment(int COASID, Course course, Assignment assignment) {
        this.COASID = COASID;
        this.course = course;
        this.assignment = assignment;
    }

    public CourseAssignment(Course course, Assignment assignment) {
        this.course = course;
        this.assignment = assignment;
    }

    
    public int getCOASID() {
        return COASID;
    }

    public Course getCourse() {
        return course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setCOASID(int COASID) {
        this.COASID = COASID;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.COASID;
        hash = 29 * hash + Objects.hashCode(this.course);
        hash = 29 * hash + Objects.hashCode(this.assignment);
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
        final CourseAssignment other = (CourseAssignment) obj;
        if (this.COASID != other.COASID) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
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
        builder.append("COAS_ID=").append(getCOASID()).append(" "
                + "Course :")
                .append("COID=").append(course.getCoId())
                .append(", title=").append(course.getTitle())
                .append(", stream=").append(course.getStream())
                .append(", type=").append(course.getType()).
                append(" Assignment :").append("ASID=").append(assignment.getAsId()).
                append(", type=").append(assignment.getType()).append(", description=").append(assignment.getDescription())
                .append('}');
        return builder.toString();
    }
    
    
    
}
