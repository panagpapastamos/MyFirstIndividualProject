package individualprojectpartb.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {

    private int AsId;
    private String type;
    private String description;
    private LocalDate subDate;
    private int oralMark;
    private int totalMark;

    //all the basic methods for the entity assignment
    public Assignment(int AsId, String type, String description, LocalDate subDate, int oralMark, int totalMark) {
        this.AsId = AsId;
        this.type = type;
        this.description = description;
        this.subDate = subDate;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public Assignment(String type, String description, LocalDate subDateTime, int oralMark, int totalMark) {
        this.type = type;
        this.description = description;
        this.subDate = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public int getAsId() {
        return AsId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getSubDate() {
        return subDate;
    }

    public int getOralMark() {
        return oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setAsId(int AsId) {
        this.AsId = AsId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubDate(LocalDate subDate) {
        this.subDate = subDate;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.AsId;
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.subDate);
        hash = 97 * hash + this.oralMark;
        hash = 97 * hash + this.totalMark;
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
        final Assignment other = (Assignment) obj;
        if (this.AsId != other.AsId) {
            return false;
        }
        if (this.oralMark != other.oralMark) {
            return false;
        }
        if (this.totalMark != other.totalMark) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.subDate, other.subDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assignment{" + "AsId=" + AsId + ", type=" + type + ", description=" + description + ", subDateTime=" + subDate + ", oralMark=" + oralMark + ", totalMark=" + totalMark + '}';
    }

}
