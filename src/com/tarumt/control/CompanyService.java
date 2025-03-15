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

import java.util.LinkedList;
import java.util.List;

public class CompanyService implements Service {

    private List<Company> companies = new LinkedList<>();
    private final CompanyUI companyUI;
    private final LocationUI locationUI;

    public CompanyService() {
        Input input = new Input();
        this.companies = Initializer.getCompanies();
        this.companyUI = new CompanyUI(input);
        this.locationUI = new LocationUI(input);
    }

    public void accessEmployer() {
        boolean companyExists = !Initializer.getCompanies().isEmpty();
        if (!companyExists)
            this.initCompany();

        if (companyExists) {
            boolean login = this.loginOrRegister();
            if (login) companyUI.accessMenu();
        }
    }

    @Override
    public void run() {
        companyUI.menu(this);
    }

    @Override
    public void create() {
        while (true) {
            companyUI.printCreateCompanyMsg();
            companyUI.printNextIDMsg();
            Company company = this.getCompany();
            if (company == null) return;
            companies.add(company);
            companyUI.printSuccessCreateCompanyMsg();
            if (!companyUI.continueToCreateCompany()) return;
        }
    }

    @Override
    public void read() {
        companyUI.displayAllCompanies(companies);
    }

    @Override
    public void search() {
        while (true) {
            companyUI.printSearchCompanyMsg(companies);
            if (this.companies.isEmpty()) return;
            String query = companyUI.getSearchCompanyQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) return;
            FuzzySearch.Result<Company> result = FuzzySearch.searchList(Company.class, this.companies, query);
            companyUI.printSearchResult(result);
        }
    }

    @Override
    public void filter() {
        Log.na();
    }

    @Override
    public void update() {
        companyUI.printUpdateCompanyMsg(this.companies);
        if (this.companies.isEmpty()) return;
        List<String> ids = BaseEntity.getIds(companies);
        String id = companyUI.getCompanyId("| Select Company ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) return;

        Company company = BaseEntity.getById(id, companies);
        companyUI.printOriginalCompanyValue(company);
        companyUI.updateCompanyMode(this, company.getId());
    }

    @Override
    public void delete() {
        companyUI.deleteMenu(this, this.companies);
    }

    public void deleteByIndex() {
        companyUI.printDeleteByIndexMsg();
        int index = companyUI.getCompanyIndex(companies.size());
        if (index == Input.INT_EXIT_VALUE) return;

        if (companyUI.confirmDelete()) {
            Company company = companies.remove(index - 1);
            companyUI.printSuccessDeleteMsg(company.getId());
        }
    }

    public void deleteByRange() {
        companyUI.printDeleteByRangeMsg();
        int startIndex = companyUI.getDeleteStartIndex(companies.size());
        if (startIndex == Input.INT_EXIT_VALUE) return;

        int endIndex = companyUI.getDeleteEndIndex(startIndex, companies.size());
        if (endIndex == Input.INT_EXIT_VALUE) return;

        if (endIndex >= startIndex) {
            if (companyUI.confirmDelete()) {
                companies.subList(startIndex - 1, endIndex).clear();
                companyUI.printSuccessDeleteByRangeMsg(startIndex, endIndex);
            }
        }
    }

    public void deleteById() {
        companyUI.printDeleteByIdMsg();
        List<String> ids = BaseEntity.getIds(companies);
        String id = companyUI.getCompanyId("| Select Company ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) return;

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

    @Override
    public void report() {
        Log.na();
    }

    private Company getCompany() {
        String name = companyUI.getCompanyName();
        if (name.equals(Input.STRING_EXIT_VALUE)) return null;

        String description = companyUI.getCompanyDescription();
        if (description.equals(Input.STRING_EXIT_VALUE)) return null;

        Location location = locationUI.getLocation();
        if (location == null) return null;

        String email = companyUI.getCompanyEmail();
        if (email.equals(Input.STRING_EXIT_VALUE)) return null;

        String phone = companyUI.getCompanyPhone();
        if (phone.equals(Input.STRING_EXIT_VALUE)) return null;

        return new Company(name, description, location, email, phone);
    }

    public void updateName(String id) {
    }

    public void updateDescription(String id) {
    }

    public void updateLocation(String id) {
    }

    public void updateContactEmail(String id) {
    }

    public void updateContactPhone(String id) {
    }

    public void updateAllFields(String id) {
    }

    public void initCompany() {
        this.companyUI.printNoExistingMsg();
        this.create();
    }

    public boolean loginOrRegister() {
        try {
            this.companyUI.loginOrRegisterMenu(this);
        } catch (Menu.ExitMenuException ex) {
            return true;
        }
        return false;
    }

    public void login() {
        List<String> ids = BaseEntity.getIds(this.companies);
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
}