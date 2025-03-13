package com.tarumt.boundary;

import com.tarumt.control.ApplicantService;
import com.tarumt.entity.Applicant;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.JobPosting;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.validation.*;

import java.lang.reflect.Field;
import java.util.List;

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
                        new Menu.Choice("Create Applicant", service::create),
                        new Menu.Choice("Display Applicants", service::read),
                        new Menu.Choice("Search Applicant", service::search),
                        new Menu.Choice("Filter Applicant", service::filter),
                        new Menu.Choice("Update Applicant", service::update),
                        new Menu.Choice("Delete Applicant", service::delete),
                        new Menu.Choice("Generate Report", service::report)
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
                .exit("<Return to Main Menu>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
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

    public void printSuccessDeleteByIndexMsg(Applicant removedApplicant) {
        System.out.println();
        Log.info("Deleted applicant ID => " + removedApplicant.getId());
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

    public void accessMenu() {
        String applicantName = Context.getApplicant().getName();
        new Menu()
                .banner(applicantName)
                .header("==> Welcome, Applicant \"" + applicantName + "\" <==")
                .choice(
                        new Menu.Choice("View Job Posting", Log::na),
                        new Menu.Choice("View Companies", Log::na),
                        new Menu.Choice("Display Applicant Profile", Log::na),
                        new Menu.Choice("Update Applicant Profile", Log::na)
                )
                .exit("<Logout>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
        Log.warn("Logged out");
    }
}
