package com.tarumt.entity.qualification;

import java.util.Objects;

/**
 * @author Choo Zhen Hao
 */
public class EducationLevel extends Qualification {

    private DegreeLevel degreeLevel;
    private FieldOfStudy fieldOfStudy;
    private University university;
    private double cgpa;

    public enum University {
        UM("Universiti Malaya"),
        UKM("Universiti Kebangsaan Malaysia"),
        USM("Universiti Sains Malaysia"),
        UPM("Universiti Putra Malaysia"),
        UTM("Universiti Teknologi Malaysia"),
        UIAM("Universiti Islam Antarabangsa Malaysia"),
        UiTM("Universiti Teknologi MARA"),
        UUM("Universiti Utara Malaysia"),
        UNIMAS("Universiti Malaysia Sarawak"),
        UMS("Universiti Malaysia Sabah"),
        UniMAP("Universiti Malaysia Perlis"),
        UMT("Universiti Malaysia Terengganu"),
        UTHM("Universiti Tun Hussein Onn Malaysia"),
        UTeM("Universiti Teknikal Malaysia Melaka"),
        UniSZA("Universiti Sultan Zainal Abidin"),
        UPSI("Universiti Pendidikan Sultan Idris"),
        UniKL("Universiti Kuala Lumpur"),
        UPNM("Universiti Pertahanan Nasional Malaysia"),
        UMK("Universiti Malaysia Kelantan"),
        UMP("Universiti Malaysia Pahang"),
        // Private Universities
        MMU("Multimedia University"),
        UNITEN("Universiti Tenaga Nasional"),
        APU("Asia Pacific University of Technology & Innovation"),
        MSU("Management & Science University"),
        CITY_U("City University Malaysia"),
        WOU("Wawasan Open University"),
        MUST("Malaysia University of Science and Technology"),
        LUCT("Limkokwing University of Creative Technology"),
        MAHSA("Mahsa University"),
        SEGI("SEGi University"),
        UCSI("UCSI University"),
        INTI("INTI International University"),
        HELP("HELP University"),
        TAYLORS("Taylor's University"),
        SUNWAY("Sunway University"),
        // Foreign Branch Campuses in Malaysia
        MONASH_MY("Monash University Malaysia"),
        HWU_MY("Heriot-Watt University Malaysia"),
        NOTTINGHAM_MY("University of Nottingham Malaysia"),
        READING_MY("University of Reading Malaysia"),
        NEWCASTLE_MY("Newcastle University Medicine Malaysia"),
        CURTIN_MY("Curtin University Malaysia"),
        SWINBURNE_MY("Swinburne University of Technology Sarawak"),
        XIAMEN_MY("Xiamen University Malaysia"),
        PERDANA("Perdana University"),
        // Polytechnic & Others
        POLITEKNIK_MALAYSIA("Politeknik Malaysia"),
        IPGM("Institut Pendidikan Guru Malaysia"),
        INSPEN("Institut Penilaian Negara"),
        OTHER("Other");

        private final String displayName;

        University(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

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

    public EducationLevel(DegreeLevel degreeLevel, FieldOfStudy fieldOfStudy, University university, double cgpa, boolean optional, Importance importance) {
        super(optional, importance);
        this.degreeLevel = degreeLevel;
        this.fieldOfStudy = fieldOfStudy;
        this.cgpa = cgpa;
        this.university = university;
    }

    public EducationLevel(DegreeLevel degreeLevel, FieldOfStudy fieldOfStudy, University university, double cgpa) {
        super(true, Importance.LOW);
        this.degreeLevel = degreeLevel;
        this.fieldOfStudy = fieldOfStudy;
        this.cgpa = cgpa;
        this.university = university;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public double scoreMatch(EducationLevel other) {
        if (other == null) {
            return 0;
        }
        if (this.degreeLevel != other.degreeLevel) {
            return 0;
        }
        return Math.min(1.0, other.getCgpa() / this.getCgpa());
    }

    @Override
    public double score() {
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
        return baseScore + (cgpa / 4.0);
    }

    @Override
    public String toString() {
        return "EducationLevel{"
                + "degree='" + degreeLevel + '\''
                + ", fieldOfStudy='" + fieldOfStudy + '\''
                + ", university='" + university.getDisplayName() + '\''
                + ", cgpa=" + cgpa
                + ", optional=" + isOptional()
                + ", importance=" + getImportance()
                + '}';
    }

    @Override
    public String toShortString() {
        return degreeLevel + " in " + fieldOfStudy + " (CGPA: " + cgpa + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EducationLevel that = (EducationLevel) o;
        return Double.compare(cgpa, that.cgpa) == 0 && degreeLevel == that.degreeLevel && fieldOfStudy == that.fieldOfStudy && university == that.university;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(degreeLevel);
        result = 31 * result + Objects.hashCode(fieldOfStudy);
        result = 31 * result + Objects.hashCode(university);
        result = 31 * result + Double.hashCode(cgpa);
        return result;
    }
}
