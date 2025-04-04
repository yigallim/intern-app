package com.tarumt.control;

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

import java.util.LinkedList;
import java.util.List;

public class ApplicantService implements Service {

    private List<Applicant> applicants = new LinkedList<>();
    private final ApplicantUI applicantUI;
    private final LocationUI locationUI;

    public ApplicantService() {
        Input input = new Input();
        this.applicants = Initializer.getApplicants();
        this.applicantUI = new ApplicantUI(input);
        this.locationUI = new LocationUI(input);
    }

    public void accessApplicant() {
        boolean applicantExists = !Initializer.getApplicants().isEmpty();
        if (!applicantExists)
            this.initApplicant();

        if (applicantExists) {
            boolean login = this.loginOrRegister();
            if (login) applicantUI.accessMenu(this, new CompanyService());
        }
    }

    @Override
    public void run() {
        this.applicantUI.menu(this);
    }

    @Override
    public void create() {
        while (true) {
            applicantUI.printCreateApplicantMsg();
            applicantUI.printNextIDMsg();
            Applicant applicant = getApplicant();
            if (applicant == null) return;
            applicants.add(applicant);
            applicantUI.printSuccessCreateApplicantMsg();
            if (!applicantUI.continueToCreateApplicant()) return;
        }
    }

    @Override
    public void read() {
        this.applicantUI.displayAllApplicants(applicants);
    }

    @Override
    public void search() {
        while (true) {
            applicantUI.printSearchApplicantMsg(applicants);
            if (this.applicants.isEmpty()) return;
            String query = applicantUI.getSearchApplicantQuery();
            if (query.equals(Input.STRING_EXIT_VALUE)) return;
            FuzzySearch.Result<Applicant> result = FuzzySearch.searchList(Applicant.class, this.applicants, query);
            applicantUI.printSearchResult(result);
        }
    }

    @Override
    public void filter() {
        Log.na();
    }

    @Override
    public void update() {
        applicantUI.printUpdateApplicantMsg(applicants);
        if (applicants.isEmpty()) return;

        List<String> ids = BaseEntity.getIds(applicants);
        String id = applicantUI.getApplicantId("| Select Applicant ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) return;

        Applicant applicant = BaseEntity.getById(id, applicants);
        applicantUI.printOriginalApplicantValue(applicant);
        applicantUI.updateApplicantMode(this, applicant.getId());
    }

    @Override
    public void delete() {
        applicantUI.deleteMenu(this, this.applicants);
    }

    @Override
    public void report() {
        Log.na();
    }

    private Applicant getApplicant() {
        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE)) return null;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE)) return null;

        JobPosting.Type desiredJobType = applicantUI.getApplicantDesiredJobType();
        if (desiredJobType == null) return null;

        Location location = locationUI.getLocation();
        if (location == null) return null;

        return new Applicant(name, contactEmail, desiredJobType, location);
    }

    public void updateApplicantName(String id) {
        String fieldName = "Name";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        String newName = applicantUI.getApplicantName();
        if (newName.equals(Input.STRING_EXIT_VALUE)) return;

        applicant.setName(newName);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantContactEmail(String id) {
        String fieldName = "Contact Email";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        String newEmail = applicantUI.getApplicantContactEmail();
        if (newEmail.equals(Input.STRING_EXIT_VALUE)) return;

        applicant.setContactEmail(newEmail);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantDesiredJobType(String id) {
        String fieldName = "Desired Job Type";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        JobPosting.Type newJobType = applicantUI.getApplicantDesiredJobType();
        if (newJobType == null) return;

        applicant.setDesiredJobType(newJobType);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateApplicantLocation(String id) {
        String fieldName = "Location";
        Applicant applicant = BaseEntity.getById(id, applicants);
        if (applicant == null) return;

        applicantUI.printUpdateMessage(fieldName);
        Location newLocation = locationUI.getLocation();
        if (newLocation == null) return;

        applicant.setLocation(newLocation);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void updateAllFields(String id) {
        final String fieldName = "All Fields";
        Applicant applicant = BaseEntity.getById(id, applicants);
        applicantUI.printUpdateMessage(fieldName);

        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE)) return;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE)) return;

        JobPosting.Type desiredJobType = applicantUI.getApplicantDesiredJobType();
        if (desiredJobType == null) return;

        Location location = locationUI.getLocation();
        if (location == null) return;

        applicant.setName(name);
        applicant.setContactEmail(contactEmail);
        applicant.setDesiredJobType(desiredJobType);
        applicant.setLocation(location);
        applicantUI.printUpdateSuccessMessage(applicant, fieldName);
    }

    public void deleteByIndex() {
        applicantUI.printDeleteByIndexMsg();
        int index = applicantUI.getApplicantIndex(applicants.size());
        if (index == Input.INT_EXIT_VALUE) return;
        if (applicantUI.confirmDelete()) {
            Applicant applicant = applicants.remove(index - 1);
            applicantUI.printSuccessDeleteMsg(applicant.getId());
        }
    }

    public void deleteByRange() {
        applicantUI.printDeleteByRangeMsg();
        int startIndex = applicantUI.getDeleteStartIndex(applicants.size());
        if (startIndex == Input.INT_EXIT_VALUE) return;
        int endIndex = applicantUI.getDeleteEndIndex(startIndex, applicants.size());
        if (endIndex == Input.INT_EXIT_VALUE) return;
        if (endIndex >= startIndex) {
            if (applicantUI.confirmDelete()) {
                applicants.subList(startIndex - 1, endIndex).clear();
                applicantUI.printSuccessDeleteByRangeMsg(startIndex, endIndex);
            }
        }
    }

    public void deleteById() {
        applicantUI.printDeleteByIdMsg();
        List<String> ids = BaseEntity.getIds(applicants);
        String id = applicantUI.getApplicantId("| Select Applicant ID => ", ids);
        if (id.equals(Input.STRING_EXIT_VALUE)) return;
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
            this.applicantUI.loginOrRegisterMenu(this);
        } catch (Menu.ExitMenuException ex) {
            return true;
        }
        return false;
    }

    public void login() {
        List<String> ids = BaseEntity.getIds(this.applicants);
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

    public void accessJobApplicationMenu() {
        this.applicantUI.jobApplicationMenu(new JobPostingService());
    }

    public void displayProfile() {
        System.out.println(Context.getApplicant());
    }
}
