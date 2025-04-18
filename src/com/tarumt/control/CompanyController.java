package com.tarumt.control;

import com.tarumt.boundary.CompanyUI;
import com.tarumt.boundary.LocationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.BaseEntity;
import com.tarumt.entity.Company;
import com.tarumt.entity.location.Location;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.common.Log;
import com.tarumt.utility.common.Menu;
import com.tarumt.utility.search.FuzzySearch;

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class CompanyController {

    private static CompanyController instance;
    private ListInterface<Company> companies = new DoublyLinkedList<>();
    private final CompanyUI companyUI;
    private final LocationUI locationUI;

    private CompanyController() {
        Input input = new Input();
        this.companies = Initializer.getCompanies();
        this.companyUI = new CompanyUI(input);
        this.locationUI = new LocationUI(input);
    }

    public static CompanyController getInstance() {
        if (instance == null) {
            instance = new CompanyController();
        }
        return instance;
    }

    public void accessEmployer() {
        boolean companyExists = !Initializer.getCompanies().isEmpty();
        if (!companyExists) {
            this.initCompany();
        }

        if (companyExists) {
            boolean login = this.loginOrRegister();
            if (login) {
                companyUI.accessMenu();
            }
        }
    }

    public void run() {
        companyUI.menu();
    }

    public void create() {
        while (true) {
            companyUI.printCreateCompanyMsg();
            companyUI.printNextIDMsg();
            Company company = this.getCompany();
            if (company == null) {
                return;
            }
            companies.add(company);
            companyUI.printSuccessCreateCompanyMsg();
            if (!companyUI.continueToCreateCompany()) {
                return;
            }
        }
    }

    public void read() {
        companyUI.printAllCompanies(companies);
    }

    public void search() {
        while (true) {
            companyUI.printSearchCompanyMsg(companies);
            if (this.companies.isEmpty()) {
                return;
            }
            String query = companyUI.getSearchCompanyQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) {
                return;
            }
            FuzzySearch.Result<Company> result = FuzzySearch.searchList(Company.class, this.companies, query);
            companyUI.printSearchResult(result);
        }
    }

    public void filter() {
        Log.na();
    }

    public void update() {
        companyUI.printUpdateCompanyMsg(this.companies);
        if (this.companies.isEmpty()) {
            return;
        }
        ListInterface<String> ids = BaseEntity.getIds(companies);
        String id = companyUI.getCompanyId("| Select Company ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        Company company = BaseEntity.getById(id, companies);
        companyUI.printOriginalCompanyValue(company);
        companyUI.updateCompanyMode(company.getId());
    }

    public void delete() {
        companyUI.deleteMenu(this.companies);
    }

    public void deleteByIndex() {
        companyUI.printDeleteByIndexMsg();
        int index = companyUI.getCompanyIndex(companies.size());
        if (index == Input.INT_EXIT_VALUE) {
            return;
        }

        if (companyUI.confirmDelete()) {
            Company company = companies.remove(index - 1);
            companyUI.printSuccessDeleteMsg(company.getId());
        }
    }

    public void deleteByRange() {
        companyUI.printDeleteByRangeMsg();
        int startIndex = companyUI.getDeleteStartIndex(companies.size());
        if (startIndex == Input.INT_EXIT_VALUE) {
            return;
        }

        int endIndex = companyUI.getDeleteEndIndex(startIndex, companies.size());
        if (endIndex == Input.INT_EXIT_VALUE) {
            return;
        }

        if (endIndex >= startIndex) {
            if (companyUI.confirmDelete()) {
                ListInterface<Company> toRemove = companies.subList(startIndex - 1, endIndex);
                companies.removeAll(toRemove);
                companyUI.printSuccessDeleteByRangeMsg(startIndex, endIndex);
            }
        }
    }

    public void deleteById() {
        companyUI.printDeleteByIdMsg();
        ListInterface<String> ids = BaseEntity.getIds(companies);
        String id = companyUI.getCompanyId("| Select Company ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        if (companyUI.confirmDelete()) {
            Company company = BaseEntity.getById(id, companies);
            companies.remove(company);
            companyUI.printSuccessDeleteMsg(company.getId());
        }
    }

    public void deleteAll() {
        if (companyUI.confirmDelete()) {
            companies.clear();
            companyUI.printSuccessDeleteAllMsg();
        }
    }

    public void report() {
        Log.na();
    }

    private boolean isEmailUnique(String email) {
        return !companies.anyMatch(company -> company.getContactEmail().equalsIgnoreCase(email));
    }

    private Company getCompany() {
        String name = companyUI.getCompanyName();
        if (name.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        String description = companyUI.getCompanyDescription();
        if (description.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        Location location = locationUI.getLocation();
        if (location == null) {
            return null;
        }

        String email = companyUI.getCompanyEmail();
        if (email.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        if (!isEmailUnique(email)) {
            companyUI.printEmailAlreadyExistsMsg();
            return null;
        }

        String phone = companyUI.getCompanyPhone();
        if (phone.equals(Input.STRING_EXIT_VALUE)) {
            return null;
        }

        return new Company(name, description, location, email, phone);
    }

    public void updateName(String id) {
        String fieldName = "Company Name";
        Company company = BaseEntity.getById(id, companies);
        if (company == null) {
            return;
        }

        companyUI.printUpdateFieldMessage(fieldName);
        String newName = companyUI.getCompanyName();
        if (newName.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }
        company.setName(newName);
        companyUI.printUpdateSuccessMessage(company, fieldName);
    }

    public void updateDescription(String id) {
        String fieldName = "Company Description";
        Company company = BaseEntity.getById(id, companies);
        if (company == null) {
            return;
        }

        companyUI.printUpdateFieldMessage(fieldName);
        String newDescription = companyUI.getCompanyDescription();
        if (newDescription.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }
        company.setDescription(newDescription);
        companyUI.printUpdateSuccessMessage(company, fieldName);
    }

    public void updateLocation(String id) {
        String fieldName = "Company Location";
        Company company = BaseEntity.getById(id, companies);
        if (company == null) {
            return;
        }

        companyUI.printUpdateFieldMessage(fieldName);
        Location newLocation = locationUI.getLocation();
        if (newLocation == null) {
            return;
        }
        company.setLocation(newLocation);
        companyUI.printUpdateSuccessMessage(company, fieldName);
    }

    public void updateContactEmail(String id) {
        String fieldName = "Contact Email";
        Company company = BaseEntity.getById(id, companies);
        if (company == null) {
            return;
        }

        companyUI.printUpdateFieldMessage(fieldName);
        String newEmail = companyUI.getCompanyEmail();
        if (newEmail.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        // Check if the new email is different from current and is unique
        if (!company.getContactEmail().equals(newEmail) && !isEmailUnique(newEmail)) {
            companyUI.printEmailAlreadyExistsMsg();
            return;
        }

        company.setContactEmail(newEmail);
        companyUI.printUpdateSuccessMessage(company, fieldName);
    }

    public void updateContactPhone(String id) {
        String fieldName = "Contact Phone";
        Company company = BaseEntity.getById(id, companies);
        if (company == null) {
            return;
        }

        companyUI.printUpdateFieldMessage(fieldName);
        String newPhone = companyUI.getCompanyPhone();
        if (newPhone.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }
        company.setContactPhone(newPhone);
        companyUI.printUpdateSuccessMessage(company, fieldName);
    }

    public void updateAllFields(String id) {
        String fieldName = "All Fields";
        Company company = BaseEntity.getById(id, companies);
        if (company == null) {
            return;
        }

        companyUI.printUpdateFieldMessage(fieldName);

        String newName = companyUI.getCompanyName();
        if (newName.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        String newDescription = companyUI.getCompanyDescription();
        if (newDescription.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        Location newLocation = locationUI.getLocation();
        if (newLocation == null) {
            return;
        }

        String newEmail = companyUI.getCompanyEmail();
        if (newEmail.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        // Check if the new email is different from current and is unique
        if (!company.getContactEmail().equals(newEmail) && !isEmailUnique(newEmail)) {
            companyUI.printEmailAlreadyExistsMsg();
            return;
        }

        String newPhone = companyUI.getCompanyPhone();
        if (newPhone.equals(Input.STRING_EXIT_VALUE)) {
            return;
        }

        company.setName(newName);
        company.setDescription(newDescription);
        company.setLocation(newLocation);
        company.setContactEmail(newEmail);
        company.setContactPhone(newPhone);

        companyUI.printUpdateSuccessMessage(company, fieldName);
    }

    public void initCompany() {
        this.companyUI.printNoExistingMsg();
        this.create();
    }

    public boolean loginOrRegister() {
        try {
            this.companyUI.loginOrRegisterMenu();
        } catch (Menu.ExitMenuException ex) {
            return true;
        }
        return false;
    }

    public void login() {
        ListInterface<String> ids = BaseEntity.getIds(this.companies);
        companyUI.printLoginMsg();
        String companyId = companyUI.getCompanyId("| Company ID => ", ids);
        if (ids.contains(companyId)) {
            Company loggedInEmployer = BaseEntity.getById(companyId, this.companies);
            if (loggedInEmployer != null) {
                Context.setCompany(loggedInEmployer);
                companyUI.printLoginSuccessMsg();
            }
            throw new Menu.ExitMenuException();
        }
    }

    public void manageProfile() {
        this.companyUI.manageProfileMenu();
    }

    public void displayProfile() {
        this.companyUI.printProfile(true);
    }

    public void updateProfile() {
        Company company = Context.getCompany();
        this.companyUI.printProfile(false);
        companyUI.updateCompanyMode(company.getId());
    }

    public void deleteProfile() {
        Company company = Context.getCompany();
        if (company == null)
            return;

        companyUI.printDeleteProfileMsg();
        if (companyUI.confirmDeleteProfile()) {
            companies.remove(company);
            companyUI.printSuccessDeleteProfileMsg();
            Context.setCompany(null);
            throw new Menu.ExitMenuException();
        }
    }
}
