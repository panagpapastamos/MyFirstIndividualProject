package individualprojectpartb.entities;

import java.util.Objects;

public class StudentCourse {
    
    private int STCOID;
    private Student student;
    private Course course;

    
    //all the basic methods for the entity StudentCourse
    public StudentCourse(int STCOID, Student student, Course course) {
        this.STCOID = STCOID;
        this.student = student;
        this.course = course;
    }

    public StudentCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public int getSTCOID() {
        return STCOID;
    }

    public void setSTCOID(int STCOID) {
        this.STCOID = STCOID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.STCOID;
        hash = 17 * hash + Objects.hashCode(this.student);
        hash = 17 * hash + Objects.hashCode(this.course);
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
        final StudentCourse other = (StudentCourse) obj;
        if (this.STCOID != other.STCOID) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("STCO_ID=").append(getSTCOID()).append(" Course :")
                .append("COID=").append(course.getCoId())
                .append(", title=").append(course.getTitle())
                .append(", stream=").append(course.getStream())
                .append(", type=").append(course.getType()).
                append(" Student :").append("STID=").append(student.getStId()).
                append(", first name=").append(student.getFirstName()).append(", last name=").append(student.getLastName())
                .append('}');
        return builder.toString();
    }

    
    
    
}
