package com.tarumt.control;

import com.tarumt.boundary.ApplicantUI;
import com.tarumt.boundary.LocationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.Company;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.pretty.Chart;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.StringCondition;
import com.tarumt.utility.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList; //
import java.util.Arrays; //
import java.util.Iterator;

import java.util.LinkedList; //
import java.util.List; //

public class ApplicantService implements Service {

    private List<Applicant> applicants = new LinkedList<>();
    private List<JobApplication> jobApplications = new LinkedList<>(); 
    private final ApplicantUI applicantUI;
    private final LocationUI locationUI;

    public ApplicantService() {
        Input input = new Input();
        this.applicants = Initializer.getApplicants();
        this.applicantUI = new ApplicantUI(input);
        this.locationUI = new LocationUI(input);
        this.jobApplications = Initializer.getJobApplication();
    }

    public void accessApplicant() {
        boolean applicantExists = !Initializer.getApplicants().isEmpty();
        if (!applicantExists)
            this.initApplicant();

        if (applicantExists) {
            boolean login = this.loginOrRegister();
            if (login) applicantUI.accessMenu(this, new CompanyService());
        }
    }

    @Override
    public void run() {
        this.applicantUI.menu(this);
    }

    @Override
    public void create() {
        while (true) {
            applicantUI.printCreateApplicantMsg();
            applicantUI.printNextIDMsg();
            Applicant applicant = getApplicant();
            if (applicant == null) return;
            applicants.add(applicant);
            applicantUI.printSuccessCreateApplicantMsg();
            if (!applicantUI.continueToCreateApplicant()) return;
        }
    }

    @Override
    public void read() {
        this.applicantUI.displayAllApplicants(applicants);
    }

    @Override
    public void search() {
        while (true) {
            applicantUI.printSearchApplicantMsg(applicants);
            if (this.applicants.isEmpty()) return;
            String query = applicantUI.getSearchApplicantQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) return;
            FuzzySearch.Result<Applicant> result = FuzzySearch.searchList(Applicant.class, this.applicants, query);
            applicantUI.printSearchResult(result);
        }
    }

    @Override
    public void filter() {
        applicantUI.filterMode(this);
    }

    @Override
    public void update() {
        applicantUI.printUpdateApplicantMsg(applicants);
        if (applicants.isEmpty()) return;

        List<String> ids = BaseEntity.getIds(applicants);
        String id = applicantUI.getApplicantId("| Select Applicant ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) return;

        Applicant applicant = BaseEntity.getById(id, applicants);
        applicantUI.printOriginalApplicantValue(applicant);
        applicantUI.updateApplicantMode(this, applicant.getId());
    }

    @Override
    public void delete() {
        applicantUI.deleteMenu(this, this.applicants);
    }

    @Override
    public void report() {
        applicantUI.reportMenu(this);
    }
    
    public void reportTopLocations() {
        if (applicants.isEmpty()) {
            Log.warn("No applicant data available.");
            return;
        }

        City[] cities = City.values();
        int[] counts = new int[cities.length];

        for (Applicant applicant : applicants) {
            counts[applicant.getLocation().getCity().ordinal()]++;
        }

        LinkedList<Integer> indexList = new LinkedList<Integer>();
        for (int i = 0; i < counts.length; i++) {
            indexList.add(i);
        }

        indexList.sort((i1, i2) -> Integer.compare(counts[i2], counts[i1]));

        int topN = Math.min(10, indexList.size());
        int total = 0;
        for (int count : counts) total += count;

        if (total == 0 || topN == 0) {
            System.out.println("No applicant location data to report.");
            return;
        }

        LinkedList<String> labelList = new LinkedList<>();
        LinkedList<Integer> valueList = new LinkedList<>();

        for (int i = 0; i < topN; i++) {
            int index = indexList.get(i);
            labelList.add(cities[index].toString());
            valueList.add(counts[index]);
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("Report Generated At: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
        System.out.println("-------------------------------------------------------");

        Chart.barChart(labelList, valueList, "Top 10 Applicant Locations", 30, '█', true);

        System.out.println("\n==> Location Breakdown <==");
        for (int i = 0; i < topN; i++) {
            String label = labelList.get(i);
            int value = valueList.get(i);
            double percent = value * 100.0 / total;
            System.out.printf("%-30s : %-3d (%.1f%%)%n", label, value, percent);
        }

        Log.info("Report generation completed.");
    }

    public void reportAllLocations() {
        if (applicants.isEmpty()) {
            Log.warn("No applicant data available.");
            return;
        }

        City[] cities = City.values();
        int[] counts = new int[cities.length];

        for (Applicant applicant : applicants) {
            counts[applicant.getLocation().getCity().ordinal()]++;
        }

        int total = 0;
        for (int count : counts) {
            total += count;
        }

        if (total == 0) {
            Log.warn("No applicant location data to report.");
            return;
        }

        List<String> labels = new LinkedList<>();
        List<Integer> values = new LinkedList<>();

        for (int i = 0; i < cities.length; i++) {
            labels.add(cities[i].toString());
            values.add(counts[i]);
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("Report Generated At: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
        System.out.println("-------------------------------------------------------");

        Chart.barChart(labels, values, "All Applicant Locations", 30, '█', true);

        System.out.println("\n==> Location Breakdown <==");
        for (int i = 0; i < labels.size(); i++) {
            double percent = values.get(i) * 100.0 / total;
            System.out.printf("%-30s : %-3d (%.1f%%)%n", labels.get(i), values.get(i), percent);
        }

        Log.info("Report generation completed.");
    }


    public List<JobApplication> getAllJobApplications() {
        if (jobApplications == null) {
            return new ArrayList<>(); 
        }
        return jobApplications;
    }
    
    public void reportAllStatuses() {
        if (jobApplications == null || jobApplications.isEmpty()) {
            Log.warn("No job applications available.");
            return;
        }

        JobApplication.Status[] statuses = JobApplication.Status.values();
        int[] counts = new int[statuses.length];

        for (JobApplication app : jobApplications) {
            counts[app.getStatus().ordinal()]++;
        }

        int total = Arrays.stream(counts).sum();
        if (total == 0) {
            Log.warn("No valid job application statuses found.");
            return;
        }

        List<String> labels = new LinkedList<>();
        List<Integer> values = new LinkedList<>();

        for (int i = 0; i < statuses.length; i++) {
            labels.add(statuses[i].toString());
            values.add(counts[i]);
        }
        
        System.out.println("-------------------------------------------------------");
        System.out.printf("Report Generated At: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
        System.out.println("-------------------------------------------------------");

        Chart.barChart(labels, values, "All Job Application Statuses", 30, '█', true);

        System.out.println("\n==> Job Application Status Breakdown <==");
        for (int i = 0; i < labels.size(); i++) {
            double percent = values.get(i) * 100.0 / total;
            System.out.printf("%-12s : %-3d (%.1f%%)%n", labels.get(i), values.get(i), percent);
        }
        
        Log.info("Report generation completed.");
        
    }
    
    public void reportApplicationsByDate() {
        if (jobApplications.isEmpty()) {
            Log.warn("No job application data available.");
            return;
        }

        StringCondition dateCondition = ConditionFactory.string().regex(
            "^\\d{4}-\\d{2}-\\d{2}$",
            "Invalid format. Please enter date as yyyy-MM-dd"
        );

        Input input = new Input();

        String startStr = input.getString("Enter start date (yyyy-MM-dd): ", dateCondition);
        String endStr = input.getString("Enter end date (yyyy-MM-dd): ", dateCondition);

        LocalDate start;
        LocalDate end;

        try {
            start = LocalDate.parse(startStr);
            end = LocalDate.parse(endStr);
        } catch (Exception e) {
            Log.error("Invalid date input. Please ensure the format is correct (yyyy-MM-dd).");
            return;
        }

        if (end.isBefore(start)) {
            LocalDate temp = start;
            start = end;
            end = temp;
        }

        List<String> labels = new LinkedList<>();
        List<Integer> values = new LinkedList<>();
        List<LocalDate> uniqueDates = new LinkedList<>();

        for (JobApplication jobApplication : jobApplications) {
            LocalDate date = jobApplication.getApplicationDate();
            if (!date.isBefore(start) && !date.isAfter(end)) {
                if (!uniqueDates.contains(date)) {
                    uniqueDates.add(date);
                }
            }
        }

        for (LocalDate date : uniqueDates) {
            int count = 0;
            for (JobApplication jobApplication : jobApplications) {
                if (jobApplication.getApplicationDate().equals(date)) {
                    count++;
                }
            }
            labels.add(date.toString());
            values.add(count);
        }

        if (labels.isEmpty()) {
            Log.warn("No applications found within the selected date range.");
            return;
        }

        int total = 0;
        for (int value : values) {
            total += value;
        }
        
        System.out.println("-------------------------------------------------------");
        System.out.printf("Report Generated At: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
        System.out.println("-------------------------------------------------------");


        Chart.barChart(labels, values, "Applications from " + start + " to " + end, 30, '█', true);
        System.out.printf("\n==> Application Count (%s to %s) <==%n", start, end);
        for (int i = 0; i < labels.size(); i++) {
            double percent = values.get(i) * 100.0 / total;
            System.out.printf("%-12s : %3d (%.1f%%)%n", labels.get(i), values.get(i), percent);
        }
        
        Log.info("Report generation completed.");
        
    }

    public void reportApplicationsByMonth() {
        if (jobApplications.isEmpty()) {
            Log.warn("No job application data available.");
            return;
        }

        Input input = new Input();

        IntegerCondition monthCondition = ConditionFactory.integer()
            .min(1)
            .max(12);

        IntegerCondition yearCondition = ConditionFactory.integer()
            .min(1900)
            .max(LocalDate.now().getYear());

        int year = input.getInt("Enter year (e.g., 2025): ", yearCondition);
        int month = input.getInt("Enter month (1-12): ", monthCondition);

        YearMonth selectedMonth = YearMonth.of(year, month);

        List<LocalDate> uniqueDates = new LinkedList<>();

        for (JobApplication jobApplication : jobApplications) {
            LocalDate date = jobApplication.getApplicationDate();
            if (YearMonth.from(date).equals(selectedMonth)) {
                if (!uniqueDates.contains(date)) {
                    uniqueDates.add(date);
                }
            }
        }

        if (uniqueDates.isEmpty()) {
            Log.warn("No applications found for " + selectedMonth);
            return;
        }

        for (int i = 0; i < uniqueDates.size() - 1; i++) {
            for (int j = 0; j < uniqueDates.size() - i - 1; j++) {
                if (uniqueDates.get(j).isAfter(uniqueDates.get(j + 1))) {
                    LocalDate temp = uniqueDates.get(j);
                    uniqueDates.set(j, uniqueDates.get(j + 1));
                    uniqueDates.set(j + 1, temp);
                }
            }
        }

        List<String> labels = new LinkedList<>();
        List<Integer> values = new LinkedList<>();

        for (LocalDate date : uniqueDates) {
            int count = 0;
            for (JobApplication jobApplication : jobApplications) {
                if (jobApplication.getApplicationDate().equals(date)) {
                    count++;
                }
            }
            labels.add(date.toString());
            values.add(count);
        }

        int total = 0;
        for (int value : values) {
            total += value;
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("Report Generated At: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
        System.out.println("-------------------------------------------------------");

        Chart.barChart(labels, values, "Applications in " + selectedMonth, 30, '█', true);
        System.out.printf("\n==> Application Count (%s) <==%n", selectedMonth);
        for (int i = 0; i < labels.size(); i++) {
            double percent = values.get(i) * 100.0 / total;
            System.out.printf("%-12s : %3d (%.1f%%)%n", labels.get(i), values.get(i), percent);
        }

        Log.info("Report generation completed.");
    }

    public void reportTop5ApplicationDates() {
        if (applicants.isEmpty()) {
            Log.warn("No applicant data available.");
            return;
        }

        List<LocalDate> uniqueDates = new LinkedList<>();
        List<Integer> frequencies = new LinkedList<>();

        for (JobApplication jobApplication : jobApplications) { 
            LocalDate date = jobApplication.getApplicationDate(); 
            int index = uniqueDates.indexOf(date);
            if (index == -1) {
                uniqueDates.add(date);
                frequencies.add(1);
            } else {
                frequencies.set(index, frequencies.get(index) + 1);
            }
        }

        for (int i = 0; i < frequencies.size() - 1; i++) {
            for (int j = i + 1; j < frequencies.size(); j++) {
                if (frequencies.get(j) > frequencies.get(i)) {
                    
                    int tempFreq = frequencies.get(i);
                    frequencies.set(i, frequencies.get(j));
                    frequencies.set(j, tempFreq);

                    LocalDate tempDate = uniqueDates.get(i);
                    uniqueDates.set(i, uniqueDates.get(j));
                    uniqueDates.set(j, tempDate);
                }
            }
        }

        List<String> labels = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        int total = applicants.size();

        int limit = Math.min(5, uniqueDates.size());
        for (int i = 0; i < limit; i++) {
            labels.add(uniqueDates.get(i).toString());
            values.add(frequencies.get(i));
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("Report Generated At: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
        System.out.println("-------------------------------------------------------");

        Chart.barChart(labels, values, "Top 5 Application Dates", 30, '█', true);

        System.out.println("\n==> Application Count Per Date <==");
        for (int i = 0; i < labels.size(); i++) {
            double percent = values.get(i) * 100.0 / total;
            System.out.printf("%-12s : %3d (%.1f%%)%n", labels.get(i), values.get(i), percent);
        }
        
        Log.info("Report generation completed.");

    }


    public void reportFull() {
        System.out.println();
        System.out.println("==> Generating Full Report...");

        reportTopLocations();
        System.out.println();

        reportAllLocations();
        System.out.println();

        reportAllStatuses();
        System.out.println();
        
        reportApplicationsByDate();
        System.out.println();
        
        reportApplicationsByMonth();
        System.out.println();
        
        reportTop5ApplicationDates();
        System.out.println();

        Log.info("Full report generation completed.");
    }


    private Applicant getApplicant() {
        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE)) return null;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE)) return null;

        JobPosting.Type desiredJobType = applicantUI.getApplicantDesiredJobType();
        if (desiredJobType == null) return null;

        Location location = locationUI.getLocation();
        if (location == null) return null;

        return new Applicant(name, contactEmail, desiredJobType, location/*, null*/);
    }

    public void updateApplicantName(String id) {
        String fieldName = "Name";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        String newName = applicantUI.getApplicantName();
        if (newName.equals(Input.STRING_EXIT_VALUE)) return;

        applicant.setName(newName);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantContactEmail(String id) {
        String fieldName = "Contact Email";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        String newEmail = applicantUI.getApplicantContactEmail();
        if (newEmail.equals(Input.STRING_EXIT_VALUE)) return;

        applicant.setContactEmail(newEmail);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantDesiredJobType(String id) {
        String fieldName = "Desired Job Type";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        JobPosting.Type newJobType = applicantUI.getApplicantDesiredJobType();
        if (newJobType == null) return;

        applicant.setDesiredJobType(newJobType);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantLocation(String id) {
        String fieldName = "Location";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        Location newLocation = locationUI.getLocation();
        if (newLocation == null) return;

        applicant.setLocation(newLocation);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateAllFields(String id) {
        final String fieldName = "All Fields";
        Applicant applicant = BaseEntity.getById(id, applicants);
        applicantUI.printUpdateMessage(fieldName);

        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE)) return;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE)) return;

        JobPosting.Type desiredJobType = applicantUI.getApplicantDesiredJobType();
        if (desiredJobType == null) return;

        Location location = locationUI.getLocation();
        if (location == null) return;

        applicant.setName(name);
        applicant.setContactEmail(contactEmail);
        applicant.setDesiredJobType(desiredJobType);
        applicant.setLocation(location);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void deleteByIndex() {
        applicantUI.printDeleteByIndexMsg();
        int index = applicantUI.getApplicantIndex(applicants.size());
        if (index == Input.INT_EXIT_VALUE) return;
        if (applicantUI.confirmDelete()) {
            Applicant applicant = applicants.remove(index - 1);
            applicantUI.printSuccessDeleteMsg(applicant.getId());
        }
    }

    public void deleteByRange() {
        applicantUI.printDeleteByRangeMsg();
        int startIndex = applicantUI.getDeleteStartIndex(applicants.size());
        if (startIndex == Input.INT_EXIT_VALUE) return;
        int endIndex = applicantUI.getDeleteEndIndex(startIndex, applicants.size());
        if (endIndex == Input.INT_EXIT_VALUE) return;
        if (endIndex >= startIndex) {
            if (applicantUI.confirmDelete()) {
                applicants.subList(startIndex - 1, endIndex).clear();
                applicantUI.printSuccessDeleteByRangeMsg(startIndex, endIndex);
            }
        }
    }

    public void deleteById() {
        applicantUI.printDeleteByIdMsg();
        List<String> ids = BaseEntity.getIds(applicants);
        String id = applicantUI.getApplicantId("| Select Applicant ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) return;
        if (applicantUI.confirmDelete()) {
            Applicant applicant = BaseEntity.getById(id, applicants);
            applicants.remove(applicant);
            applicantUI.printSuccessDeleteMsg(applicant.getId());
        }
    }

    public void deleteAll() {
        if (applicantUI.confirmDelete()) {
            applicants.clear();
            applicantUI.printSuccessDeleteAllMsg();
        }
    }

    public void initApplicant() {
        this.applicantUI.printNoExistingMsg();
        this.create();
    }

    public boolean loginOrRegister() {
        try {
            this.applicantUI.loginOrRegisterMenu(this);
        } catch (Menu.ExitMenuException ex) {
            return true;
        }
        return false;
    }

    public void login() {
        List<String> ids = BaseEntity.getIds(this.applicants);
        applicantUI.printLoginMsg();
        String applicantId = applicantUI.getApplicantId("| Applicant ID => ", ids);
        if (ids.contains(applicantId)) {
            Applicant loggedInApplicant = BaseEntity.getById(applicantId, this.applicants);
            if (loggedInApplicant != null) {
                Context.setApplicant(loggedInApplicant);
                applicantUI.printLoginSuccessMsg();
            }
            throw new Menu.ExitMenuException();
        }
    }
    
    public void accessJobApplicationMenu() {
        JobPostingService jobPostingService = new JobPostingService();
        this.applicantUI.jobApplicationMenu(jobPostingService, this);
    }


    public void displayProfile() {
        System.out.println(Context.getApplicant());
    }
    
    public Applicant getApplicantById(String id) {
        for (Applicant applicant : applicants) {
            if (applicant.getId().equals(id)) {
                return applicant;
            }
        }
        return null; 
    }
    
    public void updateUserProfile(Applicant applicant) {
        if (applicant == null) return;

        applicantUI.printOriginalApplicantValue(applicant);
        applicantUI.updateUserApplicantMode(this, applicant.getId(), applicantUI);

        Applicant updatedApplicant = getApplicantById(applicant.getId());
        Context.setApplicant(updatedApplicant);
    }
    
    public void updateOwnName(Applicant applicant) {
        String fieldName = "Name";
        applicantUI.printUpdateMessage(fieldName);
        String newName = applicantUI.getApplicantName();
        if (newName.equals(Input.STRING_EXIT_VALUE)) return;
        applicant.setName(newName);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
        Context.setApplicant(applicant);
    }

    public void updateOwnContactEmail(Applicant applicant) {
        String fieldName = "Contact Email";
        applicantUI.printUpdateMessage(fieldName);
        String newEmail = applicantUI.getApplicantContactEmail();
        if (newEmail.equals(Input.STRING_EXIT_VALUE)) return;
        applicant.setContactEmail(newEmail);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
        Context.setApplicant(applicant);
    }

    public void updateOwnDesiredJobType(Applicant applicant) {
        String fieldName = "Desired Job Type";
        applicantUI.printUpdateMessage(fieldName);
        JobPosting.Type newType = applicantUI.getApplicantDesiredJobType();
        if (newType == null) return;
        applicant.setDesiredJobType(newType);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
        Context.setApplicant(applicant);
    }

    public void updateOwnLocation(Applicant applicant) {
        String fieldName = "Location";
        applicantUI.printUpdateMessage(fieldName);
        Location newLocation = locationUI.getLocation();
        if (newLocation == null) return;
        applicant.setLocation(newLocation);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
        Context.setApplicant(applicant);
    }
    
    public void updateOwnAllFields(Applicant applicant) {
        final String fieldName = "All Fields";
        applicantUI.printUpdateMessage(fieldName);

        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE)) return;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE)) return;

        JobPosting.Type desiredJobType = applicantUI.getApplicantDesiredJobType();
        if (desiredJobType == null) return;

        Location location = locationUI.getLocation();
        if (location == null) return;

        applicant.setName(name);
        applicant.setContactEmail(contactEmail);
        applicant.setDesiredJobType(desiredJobType);
        applicant.setLocation(location);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);

        Context.setApplicant(applicant);
    }
    
    public List<Applicant> getAllApplicants() {
        return this.applicants; 
    }
    
    public String getSelectedJobType(Input input) {
        JobPosting.Type[] types = JobPosting.Type.values();
        final int columns = 3;
        final int columnWidth = 40;

        System.out.println("Available Job Types:");
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%2d. %-"+columnWidth+"s", i + 1, types[i].toString());
            if ((i + 1) % columns == 0 || i == types.length - 1) {
                System.out.println();
            }
        }

        while (true) {
            String entry = input.getString("Select Job Type by number (or <Return> to exit): ", new StringCondition());
            if (entry.equals(Input.STRING_EXIT_VALUE)) return Input.STRING_EXIT_VALUE;

            try {
                int index = Integer.parseInt(entry);
                if (index >= 1 && index <= types.length) {
                    return types[index - 1].toString(); 
                } else {
                    System.out.printf("❌ Invalid selection. Please enter a number between 1 and %d.%n", types.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }
    }

    public void filterByJobType() {
        Input input = new Input();  
        String jobType = this.getSelectedJobType(input);  
        if (jobType.equals(Input.STRING_EXIT_VALUE)) return;  

        List<JobApplication> filteredApplications = new ArrayList<>();

        if (jobApplications == null) {
            System.out.println("No applications available for filtering.");
            return;
        }

        for (JobApplication application : jobApplications) {
            if (application.getJobPosting().getType().toString().equalsIgnoreCase(jobType)) {
                filteredApplications.add(application);
            }
        }

        if (!filteredApplications.isEmpty()) {
            System.out.println("Filtered Results for Job Type: " + jobType);
            TabularPrint.printTabular(filteredApplications, true);  
        } else {
            System.out.println("No applications found for Job Type: " + jobType);
        }
    }
    
    public void filterByLocation() {
        Input input = new Input();
        City selectedCity = getSelectedLocation(input);
        if (selectedCity == null) return;

        if (jobApplications == null || jobApplications.isEmpty()) {
            System.out.println("No applications available for filtering.");
            return;
        }

        List<JobApplication> filteredApplications = new ArrayList<>();
        for (JobApplication application : jobApplications) {
            if (application.getApplicant().getLocation() != null &&
                application.getApplicant().getLocation().getCity() == selectedCity) {
                filteredApplications.add(application);
            }
        }

        if (!filteredApplications.isEmpty()) {
            System.out.println("Filtered Results for Location: " + selectedCity.toString());
            TabularPrint.printTabular(filteredApplications, true);
        } else {
            System.out.println("No applications found for Location: " + selectedCity.toString());
        }
    }

    private City getSelectedLocation(Input input) {
        City[] cities = City.values();
        System.out.println("Available Job Locations:");
        for (int i = 0; i < cities.length; i++) {
            System.out.printf("%2d. %-30s", i + 1, cities[i].toString());
            if ((i + 1) % 3 == 0 || i == cities.length - 1) System.out.println();
        }
        System.out.println();

        StringCondition condition = new StringCondition() {
            public boolean validate(String input) throws ValidationException {
                if (input.isEmpty()) {
                    throw new ValidationException("Input cannot be empty.");
                }
                try {
                    int number = Integer.parseInt(input);
                    if (number < 1 || number > cities.length) {
                        throw new ValidationException("Number out of range (1-" + cities.length + ").");
                    }
                } catch (NumberFormatException e) {
                    throw new ValidationException("Invalid number format.");
                }
                return true;
            }
        };

        String userInput = input.getString("Select Job Location by number (or <Return> to exit): ", condition);
        if (userInput.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        int option = Integer.parseInt(userInput);
        City selectedCity = cities[option - 1];
        System.out.println("Selected City: " + selectedCity.toString());
        return selectedCity;
    }
    
    public String getSelectedStatus(Input input) {
        JobApplication.Status[] statuses = JobApplication.Status.values();

        System.out.println("Available Job Application Statuses:");
        for (int i = 0; i < statuses.length; i++) {
            System.out.printf(" %2d. %-25s", (i + 1), statuses[i]);
            if ((i + 1) % 3 == 0 || i == statuses.length - 1) System.out.println();
        }

        while (true) {
            String entry = input.getString("Select Job Application Status by number (or <Return> to exit): ", new StringCondition());
            if (entry.equals(Input.STRING_EXIT_VALUE)) return entry;

            try {
                int index = Integer.parseInt(entry);
                if (index >= 1 && index <= statuses.length) {
                    return statuses[index - 1].toString();
                } else {
                    System.out.println("Invalid number. Please enter a number between 1 and " + statuses.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public void filterByStatus() {
        Input input = new Input();  
        String status = this.getSelectedStatus(input); 
        if (status.equals(Input.STRING_EXIT_VALUE)) return;

        if (jobApplications == null || jobApplications.isEmpty()) {
            System.out.println("No applications available for filtering.");
            return;
        }

        List<JobApplication> filteredApplications = new ArrayList<>();
        for (JobApplication application : jobApplications) {
            if (application.getStatus() != null && application.getStatus().toString().equalsIgnoreCase(status)) {
                filteredApplications.add(application);
            }
        }

        if (!filteredApplications.isEmpty()) {
            System.out.println("Filtered Results for Status: " + status);
            TabularPrint.printTabular(filteredApplications, true);  
        } else {
            System.out.println("No applications found for Status: " + status);
        }
    }
    
    public LocalDate getStartDate(Input input) {
        StringCondition dateCondition = new StringCondition();  
        String startDateString = input.getString("Enter the start date (yyyy-mm-dd): ", dateCondition);
        return parseDate(startDateString);
    }

    public LocalDate getEndDate(Input input) {
        StringCondition dateCondition = new StringCondition();  
        String endDateString = input.getString("Enter the end date (yyyy-mm-dd): ", dateCondition);
        return parseDate(endDateString);
    }
    
    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);  
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return null;
        }
    }
    
    public void filterByDateRage(){
        Input input = new Input();
        LocalDate startDate = this.getStartDate(input); 
        LocalDate endDate = this.getEndDate(input); 

        if (startDate == null || endDate == null) {
            System.out.println("Invalid date range entered.");
            return; 
        }

        if (jobApplications == null || jobApplications.isEmpty()) {
            System.out.println("No applications available for filtering.");
            return;
        }

        List<JobApplication> filteredApplications = new ArrayList<>();
        for (JobApplication application : jobApplications) {
            if (application.getApplicationDate() != null && 
                !application.getApplicationDate().isBefore(startDate) && 
                !application.getApplicationDate().isAfter(endDate)) {
                filteredApplications.add(application);
            }
        }

        if (!filteredApplications.isEmpty()) {
            System.out.println("Filtered Results for Date Range: " + startDate + " to " + endDate);
            TabularPrint.printTabular(filteredApplications, true);  
        } else {
            System.out.println("No applications found for Date Range: " + startDate + " to " + endDate);
        }
    }
    
    
    public void applicantFilter() {
        applicantUI.applicantFilterMode(this);
    }
    
    public String getCompanySelectedJobType(Input input) {
      JobPosting.Type[] types = JobPosting.Type.values();
      final int columns = 3;
      final int columnWidth = 40;

      System.out.println("Available Job Types:");
      for (int i = 0; i < types.length; i++) {
          System.out.printf("%2d. %-"+columnWidth+"s", i + 1, types[i].toString());
          if ((i + 1) % columns == 0 || i == types.length - 1) {
              System.out.println();
          }
      }

      while (true) {
          String entry = input.getString("Select Job Type by number (or <Return> to exit): ", new StringCondition());
          if (entry.equals(Input.STRING_EXIT_VALUE)) return Input.STRING_EXIT_VALUE;

          try {
              int index = Integer.parseInt(entry);
              if (index >= 1 && index <= types.length) {
                  return types[index - 1].toString(); 
              } else {
                  System.out.printf("❌ Invalid selection. Please enter a number between 1 and %d.%n", types.length);
              }
          } catch (NumberFormatException e) {
              System.out.println("❌ Invalid input. Please enter a valid number.");
          }
      }
    }

   
    public void filterByCompanyJobType() {
        Input input = new Input();  
        String jobType = this.getCompanySelectedJobType(input);  
        if (jobType.equals(Input.STRING_EXIT_VALUE)) return;  

        if (jobApplications == null || jobApplications.isEmpty()) {
            System.out.println("No applications available for filtering.");
            return;
        }

        List<Company> uniqueCompanies = new ArrayList<>();

        for (JobApplication application : jobApplications) {
            JobPosting posting = application.getJobPosting();
            if (posting.getType().toString().equalsIgnoreCase(jobType)) {
                Company company = posting.getCompany();
                if (!containsCompany(uniqueCompanies, company)) {
                    uniqueCompanies.add(company);
                }
            }
        }

        if (!uniqueCompanies.isEmpty()) {
            System.out.println("Companies Offering Job Type: " + jobType);
            TabularPrint.printTabular(uniqueCompanies, true);  
        } else {
            System.out.println("No companies found for Job Type: " + jobType);
        }
    }
    
    private boolean containsCompany(List<Company> companies, Company company) {
        for (Company c : companies) {
            if (c.getName().equals(company.getName())) { 
                return true;
            }
        }
        return false;
    }
    
    public void filterByCompanyLocation() {
        Input input = new Input();
        City selectedCity = getCompanyLocation(input);
        if (selectedCity == null) return;

        List<Company> companiesInLocation = new ArrayList<>();

        for (JobApplication application : jobApplications) {
            JobPosting posting = application.getJobPosting();
            if (posting.getCompany().getLocation() != null &&
                    posting.getCompany().getLocation().getCity() == selectedCity) {
                Company company = posting.getCompany();
                if (!containsCompany(companiesInLocation, company)) {
                    companiesInLocation.add(company);
                }
            }
        }

        if (!companiesInLocation.isEmpty()) {
            System.out.println("Companies in Location: " + selectedCity.toString());
            TabularPrint.printTabular(companiesInLocation, true);
        } else {
            System.out.println("No companies found for Location: " + selectedCity.toString());
        }
    }

   private City getCompanyLocation(Input input) {
        City[] cities = City.values();
        System.out.println("Available Job Locations:");
        for (int i = 0; i < cities.length; i++) {
            System.out.printf("%2d. %-35s", i + 1, cities[i].toString());
            if ((i + 1) % 3 == 0) System.out.println();
        }
        System.out.println();

        StringCondition condition = new StringCondition() {

            public boolean validate(String input) throws ValidationException {
                if (input.isEmpty()) {
                    throw new ValidationException("Input cannot be empty.");
                }
                try {
                    int number = Integer.parseInt(input);
                    if (number < 1 || number > cities.length) {
                        throw new ValidationException("Number out of range (1-" + cities.length + ").");
                    }
                } catch (NumberFormatException e) {
                    throw new ValidationException("Invalid number format.");
                }
                return true;
            }
        };

        String userInput = input.getString("Select Job Location by number (or <Return> to exit): ", condition);
        if (userInput.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        int option = Integer.parseInt(userInput);
        City selectedCity = cities[option - 1];
        System.out.println("Selected City: " + selectedCity.toString()); 
        return selectedCity;
    }
}
