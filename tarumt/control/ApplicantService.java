package com.tarumt.control;

import com.tarumt.adt.list.Arrays;
import com.tarumt.boundary.ApplicantUI;
import com.tarumt.boundary.LocationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.Location;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.search.FuzzySearch;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.location.City;
import com.tarumt.utility.pretty.Chart;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicantService implements Service {

    private static ApplicantService instance;
    private ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private final ApplicantUI applicantUI;
    private final LocationUI locationUI;
        private JobPostingService jobPostingService;
    private ListInterface<JobApplication> applications = new DoublyLinkedList<>();

    public ApplicantService() {
        Input input = new Input();
        this.applicants = Initializer.getApplicants();
        this.applicantUI = new ApplicantUI(input, jobPostingService);
        this.locationUI = new LocationUI(input);
        this.applications = Initializer.getJobApplications();
    }
    
    public void setApplications(ListInterface<JobApplication> applications) {
        this.applications = applications;
    }

    public static ApplicantService getInstance() {
        if (instance == null) {
            instance = new ApplicantService();
        }
        return instance;
    }

    public void accessApplicant() {
        boolean applicantExists = !Initializer.getApplicants().isEmpty();
        if (!applicantExists)
            this.initApplicant();

        if (applicantExists) {
            boolean login = this.loginOrRegister();
            if (login)
                applicantUI.accessMenu();
        }
    }

    @Override
    public void run() {
        this.applicantUI.menu();
    }

    @Override
    public void create() {
        while (true) {
            applicantUI.printCreateApplicantMsg();
            applicantUI.printNextIDMsg();
            Applicant applicant = getApplicant();
            if (applicant == null)
                return;
            applicants.add(applicant);
            applicantUI.printSuccessCreateApplicantMsg();
            if (!applicantUI.continueToCreateApplicant())
                return;
        }
    }

    @Override
    public void read() {
        this.applicantUI.printAllApplicants(applicants);
    }

    @Override
    public void search() {
        while (true) {
            applicantUI.printSearchApplicantMsg(applicants);
            if (this.applicants.isEmpty())
                return;
            String query = applicantUI.getSearchApplicantQuery();
            if (query.equals(Input.STRING_EXIT_VALUE))
                return;
            FuzzySearch.Result<Applicant> result = FuzzySearch.searchList(Applicant.class, this.applicants, query);
            applicantUI.printSearchResult(result);
        }
    }

    @Override
    public void filter() {
        Input input = new Input(); 
        ApplicantUI applicantUI = new ApplicantUI(input, jobPostingService);
        ApplicantService service = ApplicantService.getInstance();
        applicantUI.filterMode(service);
    }

    @Override
    public void update() {
        applicantUI.printUpdateApplicantMsg(applicants);
        if (applicants.isEmpty())
            return;

        ListInterface<String> ids = BaseEntity.getIds(applicants);
        String id = applicantUI.getApplicantId("| Select Applicant ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE))
            return;

        Applicant applicant = BaseEntity.getById(id, applicants);
        applicantUI.printOriginalApplicantValue(applicant);
        applicantUI.updateApplicantMode(applicant.getId());
    }

    @Override
    public void delete() {
        applicantUI.deleteMenu(this.applicants);
    }

    @Override
    public void report() {
        String generatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        System.out.println("========================================================================================");
        System.out.printf("%55s%n", "APPLICANT SUMMARY REPORT");
        System.out.printf("%40s %s%n", "Generated on:", generatedTime);
        System.out.println("========================================================================================");

        System.out.printf("| %-13s | %-20s | %-13s | %-29s |%n", 
                "Applicant ID", "Name", "Applications", "Status Summary");
        System.out.println("----------------------------------------------------------------------------------------");

        int totalApplications = 0;
        int totalUniqueApplications = 0;
        int totalDuplicateApplications = 0;
        int maxApplications = 0;
        ListInterface<Applicant> topApplicants = new DoublyLinkedList<>();
        ListInterface<String> categories = new DoublyLinkedList<>();
        ListInterface<Integer> values = new DoublyLinkedList<>();

        ListInterface<String> applicantIds = new DoublyLinkedList<>();
        ListInterface<Integer> applicationCounts = new DoublyLinkedList<>();

        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            String applicantId = applicant.getId();
            String applicantName = applicant.getName();
            ListInterface<String> applicantApplications = new DoublyLinkedList<>();

            String[] statuses = {"Pending", "Shortlisted", "Interviewed", "Offered", "Accepted", "Rejected", "Withdrawn"};
            int[] statusCounts = new int[statuses.length];

            for (int j = 0; j < applications.size(); j++) {
                JobApplication app = applications.get(j);
                if (app.getApplicant().getId().equals(applicantId)) {
                    applicantApplications.add(app.getId()); 
                    for (int s = 0; s < statuses.length; s++) {
                        if (app.getStatus().name().equalsIgnoreCase(statuses[s])) {
                            statusCounts[s]++;
                            break;
                        }
                    }
                }
            }

            StringBuilder statusSummary = new StringBuilder();
            for (int s = 0; s < statuses.length; s++) {
                if (statusCounts[s] > 0) {
                    if (statusSummary.length() > 0) statusSummary.append("  ");
                    statusSummary.append(String.format("%s: %d", statuses[s], statusCounts[s]));
                }
            }

            int applicationCount = applicantApplications.size();
            String applicationsFormatted = String.format("%d (%s)", applicationCount, String.join(",", applicantApplications));

            System.out.printf("|%-15s|%-22s|%-15s|%-31s|%n", applicantId, applicantName, applicationsFormatted, statusSummary.toString());

            totalApplications += applicationCount;

            if (applicationCount == 1) {
                totalUniqueApplications++;
            } else if (applicationCount > 1) {
                totalDuplicateApplications++;
            }

            if (applicationCount > maxApplications) {
                maxApplications = applicationCount;
                topApplicants.clear();
                topApplicants.add(applicant);
            } else if (applicationCount == maxApplications && applicationCount > 0) {
                topApplicants.add(applicant);
            }

            categories.add(applicantName);
            values.add(applicationCount);

            boolean applicantFound = false;
            for (int k = 0; k < applicantIds.size(); k++) {
                if (applicantIds.get(k).equals(applicantId)) {
                    applicationCounts.set(k, applicationCounts.get(k) + 1); 
                    applicantFound = true;
                    break;
                }
            }

            if (!applicantFound) {
                applicantIds.add(applicantId);
                applicationCounts.add(applicationCount);
            }
        }

        System.out.println("========================================================================================");

        System.out.println();
        System.out.println("Most Active Applicant(s):");
        if (topApplicants.isEmpty()) {
            System.out.println(" - None");
        } else {
            for (int i = 0; i < topApplicants.size(); i++) {
                Applicant top = topApplicants.get(i);
                System.out.printf(" - %s (%d applications)%n", top.getName(), maxApplications);
            }
        }

        System.out.println("====================================================================================");

        System.out.printf("Total Applications: %d%n", totalApplications);
        System.out.printf("Total Unique Applications: %d%n", totalUniqueApplications);
        System.out.printf("Total Duplicate Applications: %d%n", totalDuplicateApplications);

        Chart.barChart(categories, values, "Application Count per Applicant", 50, '=', true);

        System.out.println("====================================================================================");
    }


    private Applicant getApplicant() {
        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE))
            return null;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE))
            return null;

        String contactPhone = applicantUI.getApplicantContactPhone();
        if (contactPhone.equals(Input.STRING_EXIT_VALUE))
            return null;

        JobPosting.Type desiredJobType = applicantUI.getApplicantDesiredJobType();
        if (desiredJobType == null)
            return null;

        Location location = locationUI.getLocation();
        if (location == null)
            return null;

        return new Applicant(name, contactEmail, contactPhone, desiredJobType, location);
    }

    public void updateApplicantName(String id) {
        String fieldName = "Name";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null)
            return;

        applicantUI.printUpdateFieldMessage(fieldName);
        String newName = applicantUI.getApplicantName();
        if (newName.equals(Input.STRING_EXIT_VALUE))
            return;

        applicant.setName(newName);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantContactEmail(String id) {
        String fieldName = "Contact Email";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null)
            return;

        applicantUI.printUpdateFieldMessage(fieldName);
        String newEmail = applicantUI.getApplicantContactEmail();
        if (newEmail.equals(Input.STRING_EXIT_VALUE))
            return;

        applicant.setContactEmail(newEmail);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantContactPhone(String id) {
        String fieldName = "Contact Phone";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null)
            return;

        applicantUI.printUpdateFieldMessage(fieldName);
        String newPhone = applicantUI.getApplicantContactPhone();
        if (newPhone.equals(Input.STRING_EXIT_VALUE))
            return;

        applicant.setContactPhone(newPhone);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantDesiredJobType(String id) {
        String fieldName = "Desired Job Type";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null)
            return;

        applicantUI.printUpdateFieldMessage(fieldName);
        JobPosting.Type newJobType = applicantUI.getApplicantDesiredJobType();
        if (newJobType == null)
            return;

        applicant.setDesiredJobType(newJobType);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantLocation(String id) {
        String fieldName = "Location";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null)
            return;

        applicantUI.printUpdateFieldMessage(fieldName);
        Location newLocation = locationUI.getLocation();
        if (newLocation == null)
            return;

        applicant.setLocation(newLocation);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateAllFields(String id) {
        String fieldName = "All Fields";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null)
            return;

        applicantUI.printUpdateFieldMessage(fieldName);

        String newName = applicantUI.getApplicantName();
        if (newName.equals(Input.STRING_EXIT_VALUE))
            return;

        String newEmail = applicantUI.getApplicantContactEmail();
        if (newEmail.equals(Input.STRING_EXIT_VALUE))
            return;

        String newPhone = applicantUI.getApplicantContactPhone();
        if (newPhone.equals(Input.STRING_EXIT_VALUE))
            return;

        JobPosting.Type newDesiredJobType = applicantUI.getApplicantDesiredJobType();
        if (newDesiredJobType == null)
            return;

        Location newLocation = locationUI.getLocation();
        if (newLocation == null)
            return;

        applicant.setName(newName);
        applicant.setContactEmail(newEmail);
        applicant.setContactPhone(newPhone);
        applicant.setDesiredJobType(newDesiredJobType);
        applicant.setLocation(newLocation);

        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void deleteByIndex() {
        applicantUI.printDeleteByIndexMsg();
        int index = applicantUI.getApplicantIndex(applicants.size());
        if (index == Input.INT_EXIT_VALUE)
            return;
        if (applicantUI.confirmDelete()) {
            Applicant applicant = applicants.remove(index - 1);
            applicantUI.printSuccessDeleteMsg(applicant.getId());
        }
    }

    public void deleteByRange() {
        applicantUI.printDeleteByRangeMsg();
        int startIndex = applicantUI.getDeleteStartIndex(applicants.size());
        if (startIndex == Input.INT_EXIT_VALUE)
            return;
        int endIndex = applicantUI.getDeleteEndIndex(startIndex, applicants.size());
        if (endIndex == Input.INT_EXIT_VALUE)
            return;
        if (endIndex >= startIndex) {
            if (applicantUI.confirmDelete()) {
                ListInterface<Applicant> toRemove = applicants.subList(startIndex - 1, endIndex);
                applicants.removeAll(toRemove);
                applicantUI.printSuccessDeleteByRangeMsg(startIndex, endIndex);
            }
        }
    }

    public void deleteById() {
        applicantUI.printDeleteByIdMsg();
        ListInterface<String> ids = BaseEntity.getIds(applicants);
        String id = applicantUI.getApplicantId("| Select Applicant ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE))
            return;
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
            this.applicantUI.loginOrRegisterMenu();
        } catch (Menu.ExitMenuException ex) {
            return true;
        }
        return false;
    }

    public void login() {
        ListInterface<String> ids = BaseEntity.getIds(this.applicants);
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

    public void exploreJobsAndCompanies() {
        this.applicantUI.exploreJobsAndCompaniesMenu();
    }

    public void manageProfile() {
        this.applicantUI.manageProfileMenu();
    }

    public void displayProfile() {
        this.applicantUI.printProfile(true);
    }

    public void updateProfile() {
        Applicant applicant = Context.getApplicant();
        this.applicantUI.printProfile(false);
        applicantUI.updateApplicantMode(applicant.getId());
    }

    public void deleteProfile() {
        Applicant applicant = Context.getApplicant();
        if (applicant == null)
            return;
            
        applicantUI.printDeleteProfileMsg();
        if (applicantUI.confirmDeleteProfile()) {
            applicants.remove(applicant);
            applicantUI.printSuccessDeleteProfileMsg();
            Context.setApplicant(null);
            throw new Menu.ExitMenuException();
        }
    }
    
    public ListInterface<Applicant> filterByLocation(ListInterface<Applicant> applicants, Location location) {
        ListInterface<Applicant> result = new DoublyLinkedList<>();
        for (Applicant applicant : applicants) {
            if (applicant.getLocation() != null && applicant.getLocation().equals(location)) {
                result.add(applicant);
            }
        }
        return result;
    }
    
    
    public ListInterface<JobApplication> filterApplicationsByLocation(ListInterface<JobApplication> applications, City location) {
        ListInterface<JobApplication> filtered = new DoublyLinkedList<>();

        for (JobApplication app : applications) {
            Applicant applicant = app.getApplicant();
            if (applicant != null && applicant.getLocation() != null && applicant.getLocation().getCity() == location) {
                filtered.add(app);
            }
        }

        return filtered;
    }

   public ListInterface<JobApplication> filterByStatus(ListInterface<JobApplication> jobApplications, JobApplication.Status status) {
        ListInterface<JobApplication> filtered = new DoublyLinkedList<>();
        for (JobApplication app : jobApplications) {
            if (app.getStatus() == status) {
                filtered.add(app);
            }
        }
        return filtered;
    }


    public ListInterface<JobApplication> getJobApplications() {
        return applications;
    }

    
    public ListInterface<JobApplication> sortBySubmissionDate(ListInterface<JobApplication> jobApplications) {
        ListInterface<JobApplication> sorted = new DoublyLinkedList<>();
        JobApplication[] array = new JobApplication[jobApplications.size()];

        int i = 0;
        for (JobApplication app : jobApplications) {
            array[i++] = app;
        }

        Arrays.sort(array, (a1, a2) -> a1.getAppliedAt().compareTo(a2.getAppliedAt()));

        for (JobApplication app : array) {
            sorted.add(app);
        }

        return sorted;
    }
    
    public void addApplication(JobApplication application) {
        applications.add(application);
    }

    
    public ListInterface<Object[]> getApplicantsSortedWithApplications() {
        ListInterface<Applicant> applicants = new DoublyLinkedList<>();
        ListInterface<Integer> counts = new DoublyLinkedList<>();
        ListInterface<ListInterface<JobApplication>> applicationGroups = new DoublyLinkedList<>();

        for (int a = 0; a < applications.size(); a++) {
            JobApplication app = applications.get(a);
            Applicant applicant = app.getApplicant();
            boolean found = false;

            for (int i = 0; i < applicants.size(); i++) {
                if (applicants.get(i).getId().equals(applicant.getId())) {
                    counts.set(i, counts.get(i) + 1);
                    applicationGroups.get(i).add(app);
                    found = true;
                    break;
                }
            }

            if (!found) {
                applicants.add(applicant);
                counts.add(1);
                ListInterface<JobApplication> apps = new DoublyLinkedList<>();
                apps.add(app);
                applicationGroups.add(apps);
            }
        }

        Applicant[] applicantArray = new Applicant[applicants.size()];
        Integer[] countArray = new Integer[counts.size()];
        ListInterface<ListInterface<JobApplication>> sortedApplications = new DoublyLinkedList<>();

        for (int i = 0; i < applicants.size(); i++) {
            applicantArray[i] = applicants.get(i);
            countArray[i] = counts.get(i);
            sortedApplications.add(applicationGroups.get(i));
        }

        for (int i = 0; i < countArray.length - 1; i++) {
            for (int j = i + 1; j < countArray.length; j++) {
                if (countArray[i] < countArray[j]) {

                    int tempCount = countArray[i];
                    countArray[i] = countArray[j];
                    countArray[j] = tempCount;

                    Applicant tempApplicant = applicantArray[i];
                    applicantArray[i] = applicantArray[j];
                    applicantArray[j] = tempApplicant;

                    ListInterface<JobApplication> tempApps = sortedApplications.get(i);
                    sortedApplications.set(i, sortedApplications.get(j));
                    sortedApplications.set(j, tempApps);
                }
            }
        }

        ListInterface<Object[]> sortedApplicants = new DoublyLinkedList<>();
        for (int i = 0; i < applicantArray.length; i++) {
            sortedApplicants.add(new Object[]{applicantArray[i], sortedApplications.get(i)});
        }

        return sortedApplicants;
    }

}
