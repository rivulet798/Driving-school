package entity.ext;

import entity.AbstractEntity;

public class Group extends AbstractEntity{
    private int groupNumber;
    private String category;
    private int maxNumberOfStudents;
    private String dateOfBeginning;
    private String dateOfEnding;
    private int idTeacher;

    public Group() {
    }

    public Group(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Group(int groupNumber, String category, int maxNumberOfStudents, String dateOfBeginning, String dateOfEnding, int idTeacher) {
        this.groupNumber = groupNumber;
        this.category = category;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.dateOfBeginning = dateOfBeginning;
        this.dateOfEnding = dateOfEnding;
        this.idTeacher = idTeacher;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public String getDateOfBeginning() {
        return dateOfBeginning;
    }

    public void setDateOfBeginning(String dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
    }

    public String getDateOfEnding() {
        return dateOfEnding;
    }

    public void setDateOfEnding(String dateOfEnding) {
        this.dateOfEnding = dateOfEnding;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupNumber=" + groupNumber +
                ", category='" + category + '\'' +
                ", maxNumberOfStudents=" + maxNumberOfStudents +
                ", dateOfBeginning='" + dateOfBeginning + '\'' +
                ", dateOfEnding='" + dateOfEnding + '\'' +
                ", idTeacher=" + idTeacher +
                '}';
    }
}
