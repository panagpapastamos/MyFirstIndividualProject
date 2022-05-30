package individualprojectpartb.entities;

import java.util.Objects;


public class CourseTrainer {
    
    private int COTRID;
    private Course course;
    private Trainer trainer;

    //all the basic methods for the entity CourseTrainer
    public CourseTrainer(int COTRID, Course course, Trainer trainer) {
        this.COTRID = COTRID;
        this.course = course;
        this.trainer = trainer;
    }

    public CourseTrainer(Course course, Trainer trainer) {
        this.course = course;
        this.trainer = trainer;
    }

    public int getCOTRID() {
        return COTRID;
    }

    public Course getCourse() {
        return course;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setCOTRID(int COTRID) {
        this.COTRID = COTRID;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.COTRID;
        hash = 41 * hash + Objects.hashCode(this.course);
        hash = 41 * hash + Objects.hashCode(this.trainer);
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
        final CourseTrainer other = (CourseTrainer) obj;
        if (this.COTRID != other.COTRID) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.trainer, other.trainer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("COTR_ID=").append(getCOTRID()).append(" Course :")
                .append("COID=").append(course.getCoId())
                .append(", title=").append(course.getTitle())
                .append(", stream=").append(course.getStream())
                .append(", type=").append(course.getType()).
                append(" Trainer :").append("TRID=").append(trainer.getTrId()).
                append(", first name=").append(trainer.getFirstName()).append(", las name=").append(trainer.getLastName())
                .append('}');
        return builder.toString();
    }
    
    
    
}
