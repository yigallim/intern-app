/**
 * @author Leong Hon Yan
 */
package com.tarumt.boundary;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.control.*;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.entity.location.City;
import com.tarumt.utility.common.*;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.*;

import java.lang.reflect.Field;
import java.util.Scanner;

import com.tarumt.adt.list.ListInterface;

public class ApplicantUI {

    private final Input input;

    public ApplicantUI(Input input) {
        this.input = input;
    }

    public void menu() {
        ApplicantController applicantController = ApplicantController.getInstance();
        new Menu()
                .header("==> Manage Applicant <==")
                .choice(
                        new Menu.Choice("🆕 Create Applicant", applicantController::create),
                        new Menu.Choice("📋 Display Applicants", applicantController::read),
                        new Menu.Choice("🔍 Search Applicant", applicantController::search),
                        new Menu.Choice("📂 Filter Applicant", applicantController::filter),
                        new Menu.Choice("🔃 Update Applicant", applicantController::update),
                        new Menu.Choice("❌ Delete Applicant", applicantController::delete),
                        new Menu.Choice("📈 Generate Report", applicantController::report))
                .exit("<Return to Main Menu>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printNextIDMsg() {
        System.out.println("| Applicant ID => " + Applicant.getNextId());
    }

    public void printCreateApplicantMsg() {
        System.out.println("<== Create Applicant [ X to Exit ] ==>");
    }

    public void printSuccessCreateApplicantMsg() {
        System.out.println();
        Log.info("New applicant created");
    }

    public boolean continueToCreateApplicant() {
        StringCondition condition = ConditionFactory.string()
                .regex("^[xy]$|^[XY]$", "Invalid input, please input X or Y");
        String xOrY = input.withoutExitKey().getString("Continue to add applicant? [ Y / X ] => ", condition);
        if (xOrY.equalsIgnoreCase("y")) {
            System.out.println();
            return true;
        }
        return false;
    }

    public void printAllApplicants(ListInterface<Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicants to display");
            input.clickAnythingToContinue();
            return;
        }
        Log.info("Displaying " + applicants.size() + " applicants");
        TabularPrint.printTabular(applicants, true, "default");
        input.clickAnythingToContinue();
    }

    public void printSearchApplicantMsg(ListInterface<Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicants to search");
            input.clickAnythingToContinue();
            return;
        }
        System.out.println("<== Search Applicant [ X to Exit ] ==>");
    }

    public String getSearchApplicantQuery() {
        StringCondition condition = ConditionFactory.string().min(1).max(50);
        return input.getString("| Search Keyword => ", condition);
    }

    public void printSearchResult(FuzzySearch.Result<Applicant> result) {
        ListInterface<Applicant> matchedApplicants = result.getSubList();
        ListInterface<String> matches = result.getMatches();
        System.out.println();
        if (matchedApplicants.isEmpty()) {
            Log.info("No applicants matched the search criteria");
        } else {
            System.out.println("Relevant Results => " + matches + "\n");
            Log.info("Displaying " + matchedApplicants.size() + " applicants");
            TabularPrint.printTabular(matchedApplicants, true, matches, "default");
            input.clickAnythingToContinue();
        }
        System.out.println();
    }

    public String getApplicantName() {
        Field field = ValidationFieldReflection.getField(Applicant.class, "name");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Applicant Name => ", condition);
    }

    public String getApplicantContactEmail() {
        Field field = ValidationFieldReflection.getField(Applicant.class, "contactEmail");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Applicant Contact Email => ", condition);
    }

    public String getApplicantContactPhone() {
        Field field = ValidationFieldReflection.getField(Applicant.class, "contactPhone");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Applicant Contact Phone => ", condition);
    }

    public JobPosting.Type getApplicantDesiredJobType() {
        return input.getEnum("|\n| Desired Job Type => ", JobPosting.Type.class, 38);
    }

    public String getApplicantId(String msg, ListInterface<String> ids) {
        StringCondition condition = ConditionFactory.string().enumeration(ids, "ID doesn't exist, try again");
        return input.getString(msg, condition);
    }

    public void printUpdateApplicantMsg(ListInterface<Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicants to update");
            input.clickAnythingToContinue();
            return;
        }
        System.out.println("<== Update Applicant [ X to Exit ] ==>");
    }

    public void updateApplicantMode(String id) {
        ApplicantController controller = ApplicantController.getInstance();
        System.out.println();
        new Menu()
                .header("Select Update Mode ==>")
                .choice(
                        new Menu.Choice("Update Name", () -> controller.updateApplicantName(id)),
                        new Menu.Choice("Update Contact Email", () -> controller.updateApplicantContactEmail(id)),
                        new Menu.Choice("Update Contact Phone", () -> controller.updateApplicantContactPhone(id)),
                        new Menu.Choice("Update Desired Job Type", () -> controller.updateApplicantDesiredJobType(id)),
                        new Menu.Choice("Update Location", () -> controller.updateApplicantLocation(id)),
                        new Menu.Choice("Update All Fields", () -> controller.updateAllFields(id)))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printUpdateFieldMessage(String fieldName) {
        System.out.println("<== Updating Applicant '" + fieldName + "' [ X to Exit ] ==>");
    }

    public void printUpdateSuccessMessage(Applicant applicant, String fieldName) {
        System.out.println();
        Log.info("Applicant '" + fieldName + "' updated successfully");
        this.printOriginalApplicantValue(applicant);
        input.clickAnythingToContinue();
    }

    public void printOriginalApplicantValue(Applicant applicant) {
        System.out.println("\n" + applicant);
    }

    public void deleteMenu(ListInterface<Applicant> applicants) {
        ApplicantController controller = ApplicantController.getInstance();
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicants to delete");
            input.clickAnythingToContinue();
            return;
        }
        new Menu()
                .header("<== Delete Applicant ==>")
                .choice(
                        new Menu.Choice("Delete By Index", controller::deleteByIndex),
                        new Menu.Choice("Delete By Index Range", controller::deleteByRange),
                        new Menu.Choice("Delete By ID", controller::deleteById),
                        new Menu.Choice("Delete All", controller::deleteAll))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .terminate(applicants::isEmpty)
                .run();
    }

    public void printDeleteByIndexMsg() {
        System.out.println("<== Delete By Index [ X to Exit ] ==>");
    }

    public int getApplicantIndex(int size) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(size);
        return input.getInt("| Select Applicant Index => ", condition);
    }

    public int getDeleteStartIndex(int size) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(size);
        return input.getInt("| Starting index => ", condition);
    }

    public int getDeleteEndIndex(int startIndex, int size) {
        IntegerCondition condition = ConditionFactory.integer().min(startIndex).max(size);
        return input.getInt("| Ending index => ", condition);
    }

    public void printSuccessDeleteMsg(String id) {
        System.out.println();
        Log.info("Deleted applicant ID => " + id);
    }

    public void printDeleteByRangeMsg() {
        System.out.println("<== Delete By Index Range [ X to Exit ] ==>");
    }

    public void printSuccessDeleteByRangeMsg(int startIndex, int endIndex) {
        System.out.println();
        Log.info("Deleted applicants from index " + startIndex + " to " + endIndex);
    }

    public void printDeleteByIdMsg() {
        System.out.println("<== Delete By ID [ X to Exit ] ==>");
    }

    public boolean confirmDelete() {
        return input.confirm("Confirm to delete applicant? [ Y / X ] => ");
    }

    public void printSuccessDeleteAllMsg() {
        System.out.println();
        Log.info("Deleted all applicants");
        input.clickAnythingToContinue();
    }

    public void printNoExistingMsg() {
        Log.warn("No existing applicant record, please register first");
        System.out.println();
    }

    public void loginOrRegisterMenu() {
        ApplicantController controller = ApplicantController.getInstance();
        new Menu()
                .banner("Applicant")
                .header("==> Applicant Section <==")
                .choice(
                        new Menu.Choice("Login", controller::login),
                        new Menu.Choice("Register New Applicant", controller::create))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printLoginMsg() {
        System.out.println("<== Applicant Login [ X to Exit ] ==>");
    }

    public void printLoginSuccessMsg() {
        System.out.println();
        Applicant applicant = Context.getApplicant();
        Log.info("Logged in as => " + applicant.getId() + ", " + applicant.getName() + " <=");
        System.out.println();
    }

    public void accessMenu() {
        ApplicantController applicantController = ApplicantController.getInstance();
        JobApplicationController jobApplicationController = JobApplicationController.getInstance();
        InterviewController interviewController = InterviewController.getInstance();
        MatchingController matchingController = MatchingController.getInstance();

        Applicant applicant = Context.getApplicant();

        try {
            new Menu()
                    .banner(applicant::getName)
                    .header(() -> "==> Welcome, Applicant \"" + applicant.getName() + "\"  |  " + Strings.formatDateTime(Context.getDateTime()) + " <==")
                    .choice(
                            new Menu.Choice("🔍 Explore Jobs & Companies", applicantController::exploreJobsAndCompanies),
                            new Menu.Choice("📄 Manage Job Applications", jobApplicationController::accessApplicant),
                            new Menu.Choice("📅 Manage Interviews", interviewController::accessApplicant),
                            new Menu.Choice("🎓 Manage Qualifications", matchingController::manageApplicantQualification),
                            new Menu.Choice("👤 Manage Profile", applicantController::manageProfile)
                    )
                    .exit("<Logout>")
                    .beforeEach(System.out::println)
                    .afterEach(System.out::println)
                    .run();
        } catch (Menu.ExitMenuException ignored) {
        }

        System.out.println();
        Log.warn("Logged out");
    }

    public void exploreJobsAndCompaniesMenu() {
        JobPostingController jobPostingController = JobPostingController.getInstance();
        CompanyController companyController = CompanyController.getInstance();
        new Menu()
                .header("==> Explore Jobs & Companies <==")
                .choice(
                        new Menu.Choice("📋 Display All Job Postings", jobPostingController::read),
                        new Menu.Choice("🔍 Search Job Postings", jobPostingController::search),
                        new Menu.Choice("📂 Filter Job Postings", jobPostingController::filter),
                        new Menu.Choice("🏢 Display Companies", companyController::read),
                        new Menu.Choice("🔍 Search Companies", companyController::search))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void manageProfileMenu() {
        ApplicantController applicantController = ApplicantController.getInstance();
        new Menu()
                .header("==> Manage Applicant Profile <==")
                .choice(
                        new Menu.Choice("📋 Display Applicant Profile", applicantController::displayProfile),
                        new Menu.Choice("🔃 Update Applicant Profile", applicantController::updateProfile),
                        new Menu.Choice("❌ Delete Applicant Profile", applicantController::deleteProfile))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printProfile(boolean pause) {
        System.out.println(Context.getApplicant());
        if (pause)
            input.clickAnythingToContinue();
    }

    public void printDeleteProfileMsg() {
        System.out.println("<== Delete Profile ==>");
        System.out.println("| Warning: This action cannot be undone");
        System.out.println("| Your profile will be permanently deleted from the system");
    }

    public boolean confirmDeleteProfile() {
        return input.confirm("Are you sure you want to delete your profile? [ Y / X ] => ");
    }

    public void printSuccessDeleteProfileMsg() {
        System.out.println();
        Log.info("Your profile has been successfully deleted");
        Log.info("You will be logged out automatically");
        input.clickAnythingToContinue();
    }

    public void printEmailAlreadyExistsMsg() {
        System.out.println("| Error: This email is already registered. Please use a different email.");
    }

    public void filterMode() {
        ApplicantController controller = ApplicantController.getInstance();
        System.out.println();
        new Menu()
                .header("Select Filter/Sort Criteria ==>")
                .choice(
                        new Menu.Choice("Filter by Location", () -> {
                            City selectedLocation = this.selectCity();

                            ListInterface<JobApplication> jobApplications = controller.getJobApplications();

                            ListInterface<JobApplication> filteredApplications = controller.filterApplicationsByLocation(jobApplications, selectedLocation);

                            if (filteredApplications.isEmpty()) {
                                System.out.println("No applications found for status: " + selectedLocation);
                            } else {
                                System.out.println("\nFiltered Job Applications by Status: " + selectedLocation);
                                TabularPrint.printTabular(filteredApplications, true);
                            }

                        }),
                        new Menu.Choice("Filter by Status", () -> {
                            JobApplication.Status chosenStatus = this.selectStatusMenu();

                            ListInterface<JobApplication> jobApplications = controller.getJobApplications();
                            ListInterface<JobApplication> filteredApplications = controller.filterByStatus(jobApplications, chosenStatus);
                            if (filteredApplications.isEmpty()) {
                                System.out.println("No applications found for status: " + chosenStatus);
                            } else {
                                System.out.println("\nFiltered Job Applications by Status: " + chosenStatus);
                                TabularPrint.printTabular(filteredApplications, true);
                            }
                        }),
                        new Menu.Choice("Sort by Submission Date", () -> {
                            ListInterface<JobApplication> sorted = controller.sortBySubmissionDate(controller.getJobApplications());
                            TabularPrint.printTabular(sorted, true);
                        }),
                        new Menu.Choice("Sort by Applicant Submission Count", this::sortByApplicantSubmissionCount)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public City selectCity() {
        Scanner scanner = new Scanner(System.in);
        final int columns = 4;
        final City[] cities = City.values();

        while (true) {
            System.out.println("Select a City (x to return):\n");

            int maxLength = 0;
            for (City city : cities) {
                maxLength = Math.max(maxLength, city.toString().length());
            }

            for (int i = 0; i < cities.length; i++) {
                String formatted = String.format("%2d. %-" + maxLength + "s", i + 1, cities[i]);
                System.out.print(formatted + "\t");

                if ((i + 1) % columns == 0) System.out.println();
            }
            if (cities.length % columns != 0) System.out.println();

            System.out.print("\nEnter your choice: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("x")) return null;

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= cities.length) {
                    City selectedCity = cities[choice - 1];

                    ListInterface<JobApplication> applicationsForCity = getApplicationsForCity(selectedCity);

                    ListInterface<JobApplication> selectedCityApplicationsList = new DoublyLinkedList<>(applicationsForCity);

                    TabularPrint.printTabular(selectedCityApplicationsList, true);

                    return selectedCity;
                } else {
                    System.out.println("Invalid number. Please choose a valid city index between 1 and " + cities.length + ".\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'x' to return.\n");
            }
        }
    }

    private ListInterface<JobApplication> getApplicationsForCity(City city) {
        ListInterface<JobApplication> applications = new DoublyLinkedList<>();
        return applications;
    }

    public JobApplication.Status selectStatusMenu() {
        Scanner scanner = new Scanner(System.in);
        final int columns = 3;
        final JobApplication.Status[] statuses = JobApplication.Status.values();

        while (true) {
            System.out.println("Select an Application Status (x to return):\n");

            int maxLength = 0;
            for (JobApplication.Status status : statuses) {
                maxLength = Math.max(maxLength, status.toString().length());
            }

            for (int i = 0; i < statuses.length; i++) {
                String formatted = String.format("%2d. %-" + maxLength + "s", i + 1, statuses[i]);
                System.out.print(formatted + "\t");

                if ((i + 1) % columns == 0) System.out.println();
            }
            if (statuses.length % columns != 0) System.out.println();

            System.out.print("\nEnter your choice: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("x")) return null;

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= statuses.length) {
                    JobApplication.Status selectedStatus = statuses[choice - 1];

                    ListInterface<JobApplication> applications = getApplicationsByStatus(selectedStatus);
                    ListInterface<JobApplication> filtered = new DoublyLinkedList<>(applications);

                    TabularPrint.printTabular(filtered, true);

                    return selectedStatus;
                } else {
                    System.out.println("Invalid number. Please choose a valid status index between 1 and " + statuses.length + ".\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'x' to return.\n");
            }
        }
    }

    private ListInterface<JobApplication> getApplicationsByStatus(JobApplication.Status status) {
        ListInterface<JobApplication> filtered = new DoublyLinkedList<>();
        ListInterface<JobApplication> allApplications = ApplicantController.getInstance().getJobApplications();
        for (int i = 0; i < allApplications.size(); i++) {
            JobApplication app = allApplications.get(i);
        }
        return filtered;
    }

    public void sortByApplicantSubmissionCount() {
        ApplicantController controller = ApplicantController.getInstance();
        ListInterface<Object[]> sorted = controller.getApplicantsSortedWithApplications();

        if (sorted.isEmpty()) {
            System.out.println("\nNo applications available to sort by submission count.");
            return;
        }

        System.out.println("\n=== Applicants Sorted by Submission Count ===");
        System.out.printf("+----+-------------------------+------------------+--------------------------+-------------------------+\n");
        System.out.printf("| %-2s | %-23s | %-16s | %-24s | %-23s |\n", "No", "Applicant Name", "Submission No.", "Job Application ID", "Job Title");
        System.out.printf("+----+-------------------------+------------------+--------------------------+-------------------------+\n");

        for (int i = 0; i < sorted.size(); i++) {
            Object[] entry = sorted.get(i);
            Applicant applicant = (Applicant) entry[0];
            ListInterface<JobApplication> apps = (ListInterface<JobApplication>) entry[1];
            int label = i + 1;
            boolean firstRow = true;

            for (int j = 0; j < apps.size(); j++) {
                JobApplication ja = apps.get(j);
                String ordinal = getOrdinal(j + 1);

                // Standardize the output format
                if (firstRow) {
                    System.out.printf("| %-2d | %-23s | %-16s | %-24s | %-23s |\n",
                            label, applicant.getName(), ordinal, ja.getId(), ja.getJobPosting().getTitle());
                    firstRow = false;
                } else {
                    System.out.printf("| %-2s | %-23s | %-16s | %-24s | %-23s |\n",
                            "", "", ordinal, ja.getId(), ja.getJobPosting().getTitle());
                }
            }

            System.out.printf("+----+-------------------------+------------------+--------------------------+-------------------------+\n");
        }
    }

    private String getOrdinal(int num) {
        if (num % 100 >= 11 && num % 100 <= 13) return num + "th";
        switch (num % 10) {
            case 1:
                return num + "st";
            case 2:
                return num + "nd";
            case 3:
                return num + "rd";
            default:
                return num + "th";
        }
    }

    public void printCannotDeleteApplicantWarning(Applicant applicant) {
        System.out.println();
        Log.warn("Cannot delete applicant " + applicant.getId() + " (" + applicant.getName() + ") because they have ongoing job applications.");
        input.clickAnythingToContinue();
    }

    public void printCannotDeleteApplicantProfileWarning(Applicant applicant) {
        Log.warn("Cannot delete profile: Your profile (" + applicant.getName() + ") has ongoing job applications.");
        input.clickAnythingToContinue();
    }
}
