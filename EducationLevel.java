package com.tarumt.entity.qualification;

public class EducationLevel extends Qualification {

    private DegreeLevel degreeLevel;
    private FieldOfStudy fieldOfStudy;
    private double cgpa;

    public enum DegreeLevel {
        PhD("Doctor of Philosophy"),
        MD("Master Degree"),
        BD("Bachelor Degree"),
        D("Diploma"),
        SC("Secondary School");

        private final String displayName;

        DegreeLevel(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public enum FieldOfStudy {
        CS("Computer Science"),
        IT("Information Technology"),
        DS("Data Science"),
        SE("Software Engineering"),
        CY("Cybersecurity"),
        AI("Artificial Intelligence"),
        CE("Computer Engineering"),
        IS("Information Systems"),
        EE("Electrical Engineering"),
        ME("Mechanical Engineering"),
        BBA("Business Administration"),
        ACC("Accounting"),
        FIN("Finance"),
        ECON("Economics"),
        MKT("Marketing"),
        HRM("Human Resource Management"),
        LAW("Law"),
        PSY("Psychology"),
        EDU("Education"),
        MED("Medicine"),
        NUR("Nursing"),
        PHAR("Pharmacy"),
        BIO("Biology"),
        CHEM("Chemistry"),
        PHY("Physics"),
        MATH("Mathematics"),
        ENV("Environmental Science"),
        GEO("Geography"),
        POL("Political Science"),
        SOC("Sociology"),
        HIS("History"),
        LANG("Linguistics"),
        PHIL("Philosophy"),
        ART("Fine Arts"),
        MUSIC("Music"),
        ARCH("Architecture"),
        DSN("Design"),
        COM("Communication"),
        JOUR("Journalism"),
        FILM("Film Studies"),
        SPORTS("Sports Science"),
        HOSP("Hospitality Management"),
        TOUR("Tourism Management");

        private final String displayName;

        FieldOfStudy(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public EducationLevel(DegreeLevel degreeLevel, FieldOfStudy fieldOfStudy, double cgpa, boolean optional, Importance importance) {
        this.degreeLevel = degreeLevel;
        this.fieldOfStudy = fieldOfStudy;
        this.cgpa = cgpa;
        setOptional(optional);
        setImportance(importance);
    } 

    public DegreeLevel getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(DegreeLevel degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public double score() {
        // Example: Higher degree and CGPA give a higher score
        double baseScore;
        switch (degreeLevel) {
            case PhD:
                baseScore = 5.0;
                break;
            case MD:
                baseScore = 4.0;
                break;
            case BD:
                baseScore = 3.0;
                break;
            case D:
                baseScore = 2.0;
                break;
            case SC:
                baseScore = 1.0;
                break;
            default:
                baseScore = 0.0;
        }
        return baseScore + (cgpa / 4.0); // Normalizing CGPA (assuming max CGPA = 4.0)
    }

    @Override
    public String toString() {
        return "EducationLevel{"
                + "degree='" + degreeLevel + '\''
                + ", fieldOfStudy='" + fieldOfStudy + '\''
                + ", cgpa=" + cgpa
                + ", optional=" + isOptional()
                + ", importance=" + getImportance()
                + '}';
    }
}
