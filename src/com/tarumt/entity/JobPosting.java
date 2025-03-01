
import com.tarumt.entity.Qualification;
import com.tarumt.adt.DoublyLinkedList;
import java.io.Serializable;
import java.util.Date;

class JobPosting implements Serializable{

    private String jobId;
    private JobTitle title;
    private Company company;
    private double salaryMin;
    private double salaryMax;
    private String description;
    private DoublyLinkedList<Qualification> qualifications;
    private Date postingDate;
    private Date closingDate;
    private Status status;

    public enum Status {   
        OPEN(1, "Open"),
        CLOSED(2, "Closed"),
        FILLED(3, "Filled");

        private final int value;
        private final String displayName;

        Status(int value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }
        
        public int getValue() {
            return value;
        }
        
        @Override
        public String toString() {
            return displayName;
        }
    }
    public enum JobTitle {
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

        JobTitle(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
    
    

    
}
