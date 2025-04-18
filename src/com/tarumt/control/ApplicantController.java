package com.tarumt.control;

import com.tarumt.boundary.ApplicantUI;
import com.tarumt.boundary.LocationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.entity.location.Location;
import com.tarumt.utility.common.*;
import com.tarumt.utility.pretty.Chart;
import com.tarumt.utility.search.FuzzySearch;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class ApplicantController {

    private static ApplicantController instance;
    private ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private final ApplicantUI applicantUI;
    private final LocationUI locationUI;

    private ApplicantController() {
        Input input = new Input();
        this.applicants = Initializer.getApplicants();
        this.jobApplications = Initializer.getJobApplications();
        this.applicantUI = new ApplicantUI(input);
        this.locationUI = new LocationUI(input);
    }

    public static ApplicantController getInstance() {
        if (instance == null) {
            instance = new ApplicantController();
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

    public ListInterface<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void run() {
        this.applicantUI.menu();
    }

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

    public void read() {
        this.applicantUI.printAllApplicants(applicants);
    }

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

    public void filter() {
        applicantUI.filterMode();
    }

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

    public void delete() {
        applicantUI.deleteMenu(this.applicants);
    }

    public void report() {
        ListInterface<JobApplication> applications = getJobApplications();
        String reportName = "Applicant Summary Report";
        String module = "Job Application Management System";
        int width = 90;

        System.out.print(Report.buildReportHeader(width, module, reportName));

        System.out.printf("| %-13s | %-20s | %-13s | %-31s |%n",
                "Applicant ID", "Name", "Applications", "Status Summary");
        System.out.println(Strings.repeat("-", width));

        int totalApplications = 0;
        int totalUniqueApplications = 0;
        int totalDuplicateApplications = 0;
        int maxApplications = 0;

        ListInterface<Applicant> topApplicants = new DoublyLinkedList<>();
        ListInterface<String> categories = new DoublyLinkedList<>();
        ListInterface<Integer> values = new DoublyLinkedList<>();

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

            ListInterface<String> statusLines = new DoublyLinkedList<>();
            for (int s = 0; s < statuses.length; s++) {
                if (statusCounts[s] > 0) {
                    statusLines.add(String.format("%s: %d", statuses[s], statusCounts[s]));
                }
            }

            ListInterface<String> appLines = new DoublyLinkedList<>();
            StringBuilder appLine = new StringBuilder();
            for (int a = 0; a < applicantApplications.size(); a++) {
                appLine.append(applicantApplications.get(a));
                if ((a + 1) % 2 == 0 || a == applicantApplications.size() - 1) {
                    appLines.add(appLine.toString().trim());
                    appLine = new StringBuilder();
                } else {
                    appLine.append(", ");
                }
            }

            System.out.printf("| %-14s| %-21s| %-14s| %-32s|%n", applicantId, applicantName,
                    appLines.size() > 0 ? appLines.get(0) : "",
                    statusLines.size() > 0 ? statusLines.get(0) : "");

            for (int l = 1; l < appLines.size(); l++) {
                String appStr = appLines.get(l);
                String statusStr = (l < statusLines.size()) ? statusLines.get(l) : "";
                System.out.printf("| %-14s| %-21s| %-14s| %-32s|%n", "", "", appStr, statusStr);
            }

            for (int l = appLines.size(); l < statusLines.size(); l++) {
                String statusStr = statusLines.get(l);
                System.out.printf("| %-14s| %-21s| %-14s| %-32s|%n", "", "", "", statusStr);
            }

            System.out.println(Strings.repeat("-", width));

            totalApplications += applicantApplications.size();

            if (applicantApplications.size() == 1) {
                totalUniqueApplications++;
            } else if (applicantApplications.size() > 1) {
                totalDuplicateApplications++;
            }

            if (applicantApplications.size() > maxApplications) {
                maxApplications = applicantApplications.size();
                topApplicants.clear();
                topApplicants.add(applicant);
            } else if (applicantApplications.size() == maxApplications && applicantApplications.size() > 0) {
                topApplicants.add(applicant);
            }

            categories.add(applicantName);
            values.add(applicantApplications.size());
        }

        System.out.println("Most Active Applicant(s):");
        if (topApplicants.isEmpty()) {
            System.out.println(" - None");
        } else {
            for (int i = 0; i < topApplicants.size(); i++) {
                Applicant top = topApplicants.get(i);
                System.out.printf(" - %s (%d applications)%n", top.getName(), maxApplications);
            }
        }

        System.out.println("\nSummary:");
        System.out.printf("Total Applications         : %d%n", totalApplications);
        System.out.printf("Total Unique Applications  : %d%n", totalUniqueApplications);
        System.out.printf("Total Duplicate Applications: %d%n", totalDuplicateApplications);

        System.out.println(Chart.barChart(categories, values, "Application Count per Applicant", width, '█', true));

        System.out.print(Report.buildReportFooter(width));
    }

    private boolean isEmailUnique(String email) {
        return !applicants.anyMatch(applicant -> applicant.getContactEmail().equalsIgnoreCase(email));
    }

    private Applicant getApplicant() {
        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE))
            return null;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE))
            return null;

        // Check if email is unique
        if (!isEmailUnique(contactEmail)) {
            applicantUI.printEmailAlreadyExistsMsg();
            return null;
        }

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

        // Check if the new email is different from current and is unique
        if (!applicant.getContactEmail().equals(newEmail) && !isEmailUnique(newEmail)) {
            applicantUI.printEmailAlreadyExistsMsg();
            return;
        }

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

        // Check if the new email is different from current and is unique
        if (!applicant.getContactEmail().equals(newEmail) && !isEmailUnique(newEmail)) {
            applicantUI.printEmailAlreadyExistsMsg();
            return;
        }

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

    public ListInterface<JobApplication> sortBySubmissionDate(ListInterface<JobApplication> jobApplications) {
        ListInterface<JobApplication> sorted = new DoublyLinkedList<>(jobApplications);
        sorted.sort((a1, a2) -> a1.getAppliedAt().compareTo(a2.getAppliedAt()));
        return sorted;
    }

    public ListInterface<Object[]> getApplicantsSortedWithApplications() {
        ListInterface<Applicant> applicants = new DoublyLinkedList<>();
        ListInterface<JobApplication> applications = getJobApplications();
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

