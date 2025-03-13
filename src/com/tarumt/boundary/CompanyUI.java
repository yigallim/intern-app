package com.tarumt.boundary;

import com.tarumt.control.CompanyService;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.Company;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.StringCondition;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.ValidationFieldReflection;

import java.lang.reflect.Field;
import java.util.List;

public class CompanyUI {

    private final Input input;

    public CompanyUI(Input input) {
        this.input = input;
    }

    public void menu(CompanyService service) {
        new Menu()
                .banner("Company")
                .header("==> Manage Company <==")
                .choice(
                        new Menu.Choice("Create Company", service::create),
                        new Menu.Choice("Display Company", service::read),
                        new Menu.Choice("Search Company", service::search),
                        new Menu.Choice("Filter Company", service::filter),
                        new Menu.Choice("Update Company", service::update),
                        new Menu.Choice("Delete Company", service::delete)
                )
                .exit("<Return to Main Menu>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printNextIDMsg() {
        System.out.println("| Company ID => " + BaseEntity.getNextId(Company.class));
    }

    public void printCreateCompanyMsg() {
        System.out.println("<== Create Company [ X to Exit ] ==>");
    }

    public void printSuccessCreateCompanyMsg() {
        System.out.println();
        Log.info("New company created");
    }

    public boolean continueToCreateCompany() {
        StringCondition condition = ConditionFactory
                .string()
                .regex("^[xy]$|^[XY]$", "Invalid input, please input X or Y");
        String xOrY = input.withoutExitKey().getString("Continue to add company? [ Y / X ] => ", condition);
        if (xOrY.equalsIgnoreCase("y")) {
            System.out.println();
            return true;
        }
        return false;
    }

    public void displayAllCompanies(List<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            Log.info("No companies to display");
            return;
        }
        Log.info("Displaying " + companies.size() + " companies");
        TabularPrint.printTabular(companies, true, "default");
        input.clickAnythingToContinue();
    }

    public void printSearchCompanyMsg() {
        System.out.println("");
    }

    public String getCompanyName() {
        Field field = ValidationFieldReflection.getField(Company.class, "name");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Company Name => ", condition);
    }

    public String getCompanyDescription() {
        Field field = ValidationFieldReflection.getField(Company.class, "description");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Company Description => ", condition);
    }

    public String getCompanyEmail() {
        Field field = ValidationFieldReflection.getField(Company.class, "contactEmail");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Company Email => ", condition);
    }

    public String getCompanyPhone() {
        Field field = ValidationFieldReflection.getField(Company.class, "contactPhone");
        StringCondition condition = (StringCondition) ConditionFactory.fromField(field);
        return input.getString("| Company Phone => ", condition);
    }

    public void printUpdateCompanyMsg(List<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            Log.info("No companies to update");
            return;
        }
        System.out.println("<== Update Company [ X to Exit ] ==>");
    }

    public void updateCompanyMode(CompanyService service, String id) {
        System.out.println();
        new Menu()
                .header("Select Update Mode ==>")
                .choice(
                        new Menu.Choice("Update Name", () -> service.updateName(id)),
                        new Menu.Choice("Update Description", () -> service.updateDescription(id)),
                        new Menu.Choice("Update Location", () -> service.updateLocation(id)),
                        new Menu.Choice("Update Contact Email", () -> service.updateContactEmail(id)),
                        new Menu.Choice("Update Contact Phone", () -> service.updateContactPhone(id)),
                        new Menu.Choice("Update All Fields", () -> service.updateAllFields(id))
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printOriginalCompanyValue(Company company) {
        System.out.println("\n" + company);
    }

    public void deleteMenu(CompanyService service, List<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            Log.info("No company to delete");
            return;
        }
        new Menu()
                .header("<== Delete Company ==>")
                .choice(
                        new Menu.Choice("Delete By Index", service::deleteByIndex),
                        new Menu.Choice("Delete By Index Range", service::deleteByRange),
                        new Menu.Choice("Delete By ID", service::deleteById),
                        new Menu.Choice("Delete All", service::deleteAll)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .terminate(companies::isEmpty)
                .run();
    }

    public void printDeleteByIndexMsg() {
        System.out.println("<== Delete By Index [ X to Exit ] ==>");
    }

    public int getCompanyIndex(int size) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(size);
        return input.getInt("| Select Company Index => ", condition);
    }

    public void printSuccessDeleteByIndexMsg(Company companyRemoved) {
        System.out.println();
        Log.info("Deleted company ID => " + companyRemoved.getId());
    }

    public int getDeleteStartIndex(int size) {
        IntegerCondition condition = ConditionFactory.integer().min(1).max(size);
        return input.getInt("| Starting index => ", condition);
    }

    public int getDeleteEndIndex(int startIndex, int size) {
        IntegerCondition condition = ConditionFactory.integer().min(startIndex).max(size);
        return input.getInt("| Ending index => ", condition);
    }

    public void printDeleteByRangeMsg() {
        System.out.println("<== Delete By Index Range [ X to Exit ] ==>");
    }

    public void printSuccessDeleteByRangeMsg(int startIndex, int endIndex) {
        System.out.println();
        Log.info("Deleted companies from index " + startIndex + " to " + endIndex);
    }

    public void printDeleteByIdMsg() {
        System.out.println("<== Delete By ID [ X to Exit ] ==>");
    }

    public String getCompanyId(String msg, List<String> ids) {
        StringCondition condition = ConditionFactory.string().enumeration(ids, "ID doesn't exist, try again");
        return input.getString(msg, condition);
    }

    public boolean confirmDelete() {
        return input.confirm("Confirm to delete company? [ Y / X ] => ");
    }

    public void printSuccessDeleteAllMsg() {
        System.out.println();
        Log.info("Deleted all companies");
    }

    public void printNoExistingMsg() {
        Log.warn("No existing company record, please register first");
        System.out.println();

    }

    public void loginOrRegisterMenu(CompanyService service) {
        new Menu()
                .banner("Employer")
                .header("==> Employer Section <==")
                .choice(
                        new Menu.Choice("Login", service::login),
                        new Menu.Choice("Register New Company", service::create)
                )
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printLoginMsg() {
        System.out.println("<== Employer Login [ X to Exit ] ==>");
    }

    public void printLoginSuccessMsg() {
        System.out.println();
        Company company = Context.getCompany();
        Log.info("Logged in as => " + company.getId() + ", " + company.getName() + " <=");
        System.out.println();
    }

    public void accessMenu() {
        String companyName = Context.getCompany().getName();

        new Menu()
                .banner(companyName)
                .header("==> Welcome, Employer \"" + companyName + "\" <==")
                .choice(
                        new Menu.Choice("Manage Job Posting", Log::na),
                        new Menu.Choice("View All Applicants", Log::na),
                        new Menu.Choice("Display Company Profile", Log::na),
                        new Menu.Choice("Update Company Profile", Log::na)
                )
                .exit("<Logout>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
        System.out.println();
        Log.warn("Logged out");
    }
}