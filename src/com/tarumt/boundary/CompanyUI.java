/**
 * @author Lim Yuet Yang
 */

package com.tarumt.boundary;

import com.tarumt.control.*;
import com.tarumt.entity.Company;
import com.tarumt.utility.common.*;
import com.tarumt.utility.pretty.TabularPrint;
import com.tarumt.utility.search.FuzzySearch;
import com.tarumt.utility.validation.ConditionFactory;
import com.tarumt.utility.validation.StringCondition;
import com.tarumt.utility.validation.IntegerCondition;
import com.tarumt.utility.validation.ValidationFieldReflection;

import java.lang.reflect.Field;

import com.tarumt.adt.list.ListInterface;

public class CompanyUI {

    private final Input input;

    public CompanyUI(Input input) {
        this.input = input;
    }

    public void menu() {
        CompanyController companyController = CompanyController.getInstance();
        new Menu()
                .header("==> Manage Company <==")
                .choice(
                        new Menu.Choice("🏢 Create Company", companyController::create),
                        new Menu.Choice("📊 Display Company", companyController::read),
                        new Menu.Choice("🔍 Search Company", companyController::search),
                        new Menu.Choice("🔃 Update Company", companyController::update),
                        new Menu.Choice("❌ Delete Company", companyController::delete))
                .exit("<Return to Main Menu>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printNextIDMsg() {
        System.out.println("| Company ID => " + Company.getNextId());
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

    public void printAllCompanies(ListInterface<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            Log.info("No companies to display");
            input.clickAnythingToContinue();
            return;
        }
        Log.info("Displaying " + companies.size() + " companies");
        TabularPrint.printTabular(companies, true);
        input.clickAnythingToContinue();
    }

    public void printSearchCompanyMsg(ListInterface<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            Log.info("No companies to search");
            input.clickAnythingToContinue();
            return;
        }
        System.out.println("<== Search Company [ X to Exit ] ==>");
    }

    public String getSearchCompanyQuery() {
        StringCondition condition = ConditionFactory.string().min(1).max(50);
        return input.getString("| Search Keyword => ", condition);
    }

    public void printSearchResult(FuzzySearch.Result<Company> result) {
        ListInterface<Company> matchedCompanies = result.getSubList();
        ListInterface<String> matches = result.getMatches();
        System.out.println();
        if (matchedCompanies.isEmpty()) {
            Log.info("No companies matched the search criteria");
        } else {
            System.out.println("Relevant Results => " + matches + "\n");
            Log.info("Displaying " + matchedCompanies.size() + " companies");
            TabularPrint.printTabular(matchedCompanies, true, matches);
            input.clickAnythingToContinue();
        }
        System.out.println();
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

    public void printUpdateFieldMessage(String fieldName) {
        System.out.println("<== Updating Company '" + fieldName + "' [ X to Exit ] ==>");
    }

    public void printUpdateSuccessMessage(Company company, String fieldName) {
        System.out.println();
        Log.info("Company '" + fieldName + "' updated successfully");
        this.printOriginalCompanyValue(company);
        input.clickAnythingToContinue();
    }

    public void printUpdateCompanyMsg(ListInterface<Company> companies) {
        if (companies == null || companies.isEmpty()) {
            Log.info("No companies to update");
            input.clickAnythingToContinue();
            return;
        }
        System.out.println("<== Update Company [ X to Exit ] ==>");
    }

    public void updateCompanyMode(String id) {
        CompanyController controller = CompanyController.getInstance();
        System.out.println();
        new Menu()
                .header("Select Update Mode ==>")
                .choice(
                        new Menu.Choice("Update Name", () -> controller.updateName(id)),
                        new Menu.Choice("Update Description", () -> controller.updateDescription(id)),
                        new Menu.Choice("Update Location", () -> controller.updateLocation(id)),
                        new Menu.Choice("Update Contact Email", () -> controller.updateContactEmail(id)),
                        new Menu.Choice("Update Contact Phone", () -> controller.updateContactPhone(id)),
                        new Menu.Choice("Update All Fields", () -> controller.updateAllFields(id)))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printOriginalCompanyValue(Company company) {
        System.out.println("\n" + company);
    }

    public void deleteMenu(ListInterface<Company> companies) {
        CompanyController controller = CompanyController.getInstance();
        if (companies == null || companies.isEmpty()) {
            Log.info("No company to delete");
            input.clickAnythingToContinue();
            return;
        }
        new Menu()
                .header("<== Delete Company ==>")
                .choice(
                        new Menu.Choice("Delete By Index", controller::deleteByIndex),
                        new Menu.Choice("Delete By Index Range", controller::deleteByRange),
                        new Menu.Choice("Delete By ID", controller::deleteById),
                        new Menu.Choice("Delete All", controller::deleteAll))
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

    public void printSuccessDeleteMsg(String id) {
        System.out.println();
        Log.info("Deleted company ID => " + id);
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

    public String getCompanyId(String msg, ListInterface<String> ids) {
        StringCondition condition = ConditionFactory.string().enumeration(ids, "ID doesn't exist, try again");
        return input.getString(msg, condition);
    }

    public boolean confirmDelete() {
        return input.confirm("Confirm to delete company? [ Y / X ] => ");
    }

    public void printSuccessDeleteAllMsg() {
        System.out.println();
        Log.info("Deleted all companies");
        input.clickAnythingToContinue();
    }

    public void printNoExistingMsg() {
        Log.warn("No existing company record, please register first");
        System.out.println();
    }

    public void loginOrRegisterMenu() {
        CompanyController controller = CompanyController.getInstance();
        new Menu()
                .banner("Employer")
                .header("==> Employer Section <==")
                .choice(
                        new Menu.Choice("Login", controller::login),
                        new Menu.Choice("Register New Company", controller::create))
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
        JobPostingController jobPostingController = JobPostingController.getInstance();
        JobApplicationController jobApplicationController = JobApplicationController.getInstance();
        InterviewController interviewController = InterviewController.getInstance();
        CompanyController companyController = CompanyController.getInstance();
        MatchingController matchingController = MatchingController.getInstance();

        Company company = Context.getCompany();

        try {
            new Menu()
                    .banner(company::getName)
                    .header(() -> "==> Welcome, Employer \"" + company.getName() + "\"  |  " + Strings.formatDateTime(Context.getDateTime()) + " <==")
                    .choice(
                            new Menu.Choice("💼 Manage Job Posting", jobPostingController::run),
                            new Menu.Choice("🔍 Manage Job Qualification", matchingController::manageJobQualification),
                            new Menu.Choice("📄 Manage Job Applications", jobApplicationController::accessEmployer),
                            new Menu.Choice("🤝 Manage Interviews", interviewController::accessEmployer),
                            new Menu.Choice("🏢 Manage Company Profile", companyController::manageProfile))
                    .exit("<Logout>")
                    .beforeEach(System.out::println)
                    .afterEach(System.out::println)
                    .run();
        } catch (Menu.ExitMenuException ignored) {
        }
        System.out.println();
        Log.warn("Logged out");
    }

    public void manageProfileMenu() {
        CompanyController companyController = CompanyController.getInstance();
        new Menu()
                .header("==> Manage Company Profile <==")
                .choice(
                        new Menu.Choice("📋 Display Company Profile", companyController::displayProfile),
                        new Menu.Choice("🔃 Update Company Profile", companyController::updateProfile),
                        new Menu.Choice("❌ Delete Company Profile", companyController::deleteProfile))
                .exit("<Return>")
                .beforeEach(System.out::println)
                .afterEach(System.out::println)
                .run();
    }

    public void printProfile(boolean pause) {
        System.out.println(Context.getCompany());
        if (pause)
            input.clickAnythingToContinue();
    }

    public void printDeleteProfileMsg() {
        System.out.println("<== Delete Profile ==>");
        System.out.println("| Warning: This action cannot be undone");
        System.out.println("| Your company profile will be permanently deleted from the system");
    }

    public boolean confirmDeleteProfile() {
        return input.confirm("Are you sure you want to delete your company profile? [ Y / X ] => ");
    }

    public void printSuccessDeleteProfileMsg() {
        System.out.println();
        Log.info("Your company profile has been successfully deleted");
        Log.info("You will be logged out automatically");
        input.clickAnythingToContinue();
    }

    public void printEmailAlreadyExistsMsg() {
        System.out.println("| Error: This email is already registered. Please use a different email.");
    }

    public void printCannotDeleteCompanyWarning(Company company) {
        System.out.println();
        Log.warn("Cannot delete company " + company.getId() + " (" + company.getName() + ") because it has active job postings.");
        input.clickAnythingToContinue();
    }

    public void printCannotDeleteCompanyProfileWarning(Company company) {
        Log.warn("Cannot delete profile: Your company (" + company.getName() + ") has active job postings.");
        input.clickAnythingToContinue();
    }
}
