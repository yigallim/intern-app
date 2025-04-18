package com.tarumt.boundary;

import com.tarumt.control.*;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.*;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.*;

import java.lang.reflect.Field;

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

        Applicant applicant = Context.getApplicant();

        try {
            new Menu()
                    .banner(applicant::getName)
                    .header(() -> "==> Welcome, Applicant \"" + applicant.getName() + "\"  |  " + Strings.formatDateTime(Context.getDateTime()) + " <==")
                    .choice(
                            new Menu.Choice("🔎 Explore Jobs & Companies", applicantController::exploreJobsAndCompanies),
                            new Menu.Choice("📄 Manage Job Application", jobApplicationController::accessApplicant),
                            new Menu.Choice("🤝 Manage Interviews", interviewController::accessApplicant),
                            new Menu.Choice("👤 Manage Applicant Profile", applicantController::manageProfile))
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
                        new Menu.Choice("📂 Filter Job Postings", Log::na),
                        new Menu.Choice("🔖 Display Recommended Job Postings", Log::na),
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

    public void reportMenu() {
        System.out.println();
        new Menu()
                .header("==> Select Report Type <==")
                .choice(
                        new Menu.Choice(" Top 10 Locations", Log::na),
                        new Menu.Choice(" All Locations (Descending)", Log::na),
                        new Menu.Choice(" Top 10 Jobs", Log::na),
                        new Menu.Choice(" All Jobs (Descending)", Log::na),
                        new Menu.Choice(" Applicants Applied Status", Log::na),
                        new Menu.Choice(" Full Report", Log::na))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }

    public void printEmailAlreadyExistsMsg() {
        System.out.println("| Error: This email is already registered. Please use a different email.");
    }
}
