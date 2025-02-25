
import com.tarumt.entity.Qualification;
import java.util.List;

class Job {

    private String id;
    private Company company;
    private String title;
    private double salaryMin;
    private double salaryMax;
    private String description;
    private Type type;
    private List<Qualification> qualifications;

    public enum Type {
        ACCOUNTING("Accounting"),
        ADMIN("Admin"),
        BANKING("Banking"),
        CONSTRUCTION("Construction"),
        EDUCATION("Education"),
        ENGINEERING("Engineering"),
        HEALTHCARE("Healthcare"),
        HOSPITALITY("Hospitality"),
        IT("IT"),
        MANAGEMENT("Management"),
        MARKETING("Marketing"),
        RETAIL("Retail"),
        SALES("Sales"),
        TRADES("Trades");

        private final String displayName;

        Type(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public Job(String id, Company company, String title, double salaryMin, double salaryMax,
            String description, Type type, List<Qualification> qualifications) {
        this.id = id;
        this.company = company;
        this.title = title;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.description = description;
        this.type = type;
        this.qualifications = qualifications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
}
