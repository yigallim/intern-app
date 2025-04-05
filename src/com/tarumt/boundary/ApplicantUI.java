package com.tarumt.boundary;

import com.tarumt.control.ApplicantService;
import com.tarumt.control.CompanyService;
import com.tarumt.control.JobPostingService;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.JobApplication;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class ApplicantUI {

    private final Input input;

    public ApplicantUI(Input input) {
        this.input = input;
    }

    public void menu(ApplicantService service) {
        new Menu()
                .banner("Applicant")
                .header("==> Manage Applicant <==")
                .choice(
                        new Menu.Choice("🆕 Create Applicant", service::create),
                        new Menu.Choice("📋 Display Applicants", service::read),
                        new Menu.Choice("🔍 Search Applicant", service::search),
                        new Menu.Choice("📂 Filter Applicant", service::filter),
                        new Menu.Choice("🔃 Update Applicant", service::update),
                        new Menu.Choice("❌ Delete Applicant", service::delete),
                        new Menu.Choice("📈 Generate Report", service::report)
                )
                .exit("<Return to Main Menu>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printNextIDMsg() {
        System.out.println("| Applicant ID => " + BaseEntity.getNextId(Applicant.class));
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

    public void displayAllApplicants(List<Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicants to display");
            return;
        }
        Log.info("Displaying " + applicants.size() + " applicants");
        TabularPrint.printTabular(applicants, true, "default");
        input.clickAnythingToContinue();
    }

    public void printSearchApplicantMsg(List<Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicants to search");
            return;
        }
        System.out.println("<== Search Applicant [ X to Exit ] ==>");
    }

    public String getSearchApplicantQuery() {
        StringCondition condition = ConditionFactory.string().min(1).max(50);
        return input.getString("| Search Keyword => ", condition);
    }

    public void printSearchResult(FuzzySearch.Result<Applicant> result) {
        List<Applicant> matchedApplicants = result.getSubList();
        Set<String> matches = result.getMatches();
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

    public JobPosting.Type getApplicantDesiredJobType() {
        return input.getEnum("|\n| Desired Job Type => ", JobPosting.Type.class, 38);
    }

    public String getApplicantId(String msg, List<String> ids) {
        StringCondition condition = ConditionFactory.string().enumeration(ids, "ID doesn't exist, try again");
        return input.getString(msg, condition);
    }

    public void printUpdateApplicantMsg(List<Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicants to update");
            return;
        }
        System.out.println("<== Update Applicant [ X to Exit ] ==>");
    }

    public void updateApplicantMode(ApplicantService service, String id) {
        System.out.println();
        new Menu()
                .header("Select Update Mode ==>")
                .choice(
                        new Menu.Choice("Update Applicant Name", () -> service.updateApplicantName(id)),
                        new Menu.Choice("Update Contact Email", () -> service.updateApplicantContactEmail(id)),
                        new Menu.Choice("Update Desired Job Type", () -> service.updateApplicantDesiredJobType(id)),
                        new Menu.Choice("Update Location", () -> service.updateApplicantLocation(id)),
                        new Menu.Choice("Update All Fields", () -> service.updateAllFields(id))
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printUpdateMessage(String fieldName) {
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

    public void deleteMenu(ApplicantService service, List<Applicant> applicants) {
        if (applicants == null || applicants.isEmpty()) {
            Log.info("No applicant to delete");
            return;
        }
        new Menu()
                .header("<== Delete Applicant ==>")
                .choice(
                        new Menu.Choice("Delete By Index", service::deleteByIndex),
                        new Menu.Choice("Delete By Index Range", service::deleteByRange),
                        new Menu.Choice("Delete By ID", service::deleteById),
                        new Menu.Choice("Delete All", service::deleteAll)
                )
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
    }

    public void printNoExistingMsg() {
        Log.warn("No existing applicant record, please register first");
        System.out.println();
    }

    public void loginOrRegisterMenu(ApplicantService service) {
        new Menu()
                .banner("Applicant")
                .header("==> Applicant Section <==")
                .choice(
                        new Menu.Choice("Login", service::login),
                        new Menu.Choice("Register New Applicant", service::create)
                )
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
    
    public void accessMenu(ApplicantService applicantService, CompanyService companyService) {
        boolean running = true;

        while (running) {
            Applicant applicant = Context.getApplicant();
            String name = applicant.getName();

            new Menu()
                .banner(name)
                .header("==> Welcome, Applicant \"" + name + "\" <==")
                .choice(
                    new Menu.Choice("📝 Job Application", applicantService::accessJobApplicationMenu),
                    new Menu.Choice("🏢 Display Companies", companyService::read),
                    new Menu.Choice("🔍 Search Companies", companyService::search),
                    new Menu.Choice("👤 Display Applicant Profile", applicantService::displayProfile),
                    new Menu.Choice("🔃 Update Applicant Profile", () -> {
                        applicantService.updateUserProfile(applicant);

                        Applicant updated = BaseEntity.getById(applicant.getId(), applicantService.getAllApplicants());
                        Context.setApplicant(updated);
                    })
                )
                .exit("<Logout>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();

            running = false;
        }

        System.out.println();
        Log.warn("Logged out");
    }

 
    public void jobApplicationMenu(JobPostingService jobPostingService) {
        new Menu()
                .header("==> Job Application <==")
                .choice(
                        new Menu.Choice("📋 Display All Job Postings", jobPostingService::read),
                        new Menu.Choice("🔍 Search Job Postings", jobPostingService::search),
                        new Menu.Choice("📂 Filter Job Postings", Log::na),
                        new Menu.Choice("🔖 Display Recommended Job Postings", Log::na),
                        new Menu.Choice("📝 Apply Job Posting", jobPostingService::applyJob),
                        new Menu.Choice("📄 Display Applied Job Postings", jobPostingService::displayJobApplication),
                        new Menu.Choice("❌ Withdraw Applied Job Posting", jobPostingService::withdrawJobApplication)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }
    
     public void reportMenu(ApplicantService service) {
        System.out.println();
        new Menu()
                .header("==> Select Report Type <==")
                .choice(
                        new Menu.Choice(" Top 10 Locations", service::reportTopLocations),
                        new Menu.Choice(" All Locations (Descending)", service::reportAllLocations),
                        new Menu.Choice(" Top 10 Jobs", service::reportTopJobs),
                        new Menu.Choice(" All Jobs (Descending)", service::reportAllJobs),
                        new Menu.Choice(" Applicants Applied Status", () -> {
                                               List<JobApplication> jobApplications = service.getAllJobApplications();
                                               service.reportAllStatuses(jobApplications);
                                           }),                        
                        new Menu.Choice(" Full Report", service::reportFull)
                    )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
    }
     
     public void updateUserApplicantMode(ApplicantService service, String id) {
        System.out.println();

        new Menu()
            .header("Select Update Mode ==>")
            .choice(
                new Menu.Choice("Update Applicant Name", () -> {
                    service.updateApplicantName(id);
                    Context.setApplicant(service.getApplicantById(id)); 
                }),
                new Menu.Choice("Update Contact Email", () -> {
                    service.updateApplicantContactEmail(id);
                    Context.setApplicant(service.getApplicantById(id)); 
                }),
                new Menu.Choice("Update Desired Job Type", () -> {
                    service.updateApplicantDesiredJobType(id);
                    Context.setApplicant(service.getApplicantById(id)); 
                }),
                new Menu.Choice("Update Location", () -> {
                    service.updateApplicantLocation(id);
                    Context.setApplicant(service.getApplicantById(id)); 
                }),
                new Menu.Choice("Update All Fields", () -> {
                    service.updateApplicantAllFields(id);
                    Context.setApplicant(service.getApplicantById(id)); 
                })
            )
            .exit("<Return>")
            .beforeEach(System.out::println)
            .afterEach(() -> {
                System.out.println("Reloading latest applicant data...");
                Context.setApplicant(service.getApplicantById(id)); 
            })
            .run();
    }

}
