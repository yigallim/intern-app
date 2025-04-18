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

import com.tarumt.adt.list.ListInterface;
import com.tarumt.adt.list.DoublyLinkedList;

public class ApplicantController {

    private static ApplicantController instance;
    private ListInterface<Applicant> applicants = new DoublyLinkedList<>();
    private final ApplicantUI applicantUI;
    private final LocationUI locationUI;

    private ApplicantController() {
        Input input = new Input();
        this.applicants = Initializer.getApplicants();
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
        Log.na();
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
        applicantUI.reportMenu();
    }

    private Applicant getApplicant() {
        String name = applicantUI.getApplicantName();
        if (name.equals(Input.STRING_EXIT_VALUE))
            return null;

        String contactEmail = applicantUI.getApplicantContactEmail();
        if (contactEmail.equals(Input.STRING_EXIT_VALUE))
            return null;

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

    private boolean isEmailUnique(String email) {
        return !applicants.anyMatch(applicant -> applicant.getContactEmail().equalsIgnoreCase(email));
    }
}
