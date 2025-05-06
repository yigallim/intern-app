/**
 * @author Lim Yuet Yang
 */

package com.tarumt.control;

import com.tarumt.adt.list.DoublyLinkedList;
import com.tarumt.adt.list.ListInterface;
import com.tarumt.boundary.JobApplicationUI;
import com.tarumt.dao.Initializer;
import com.tarumt.entity.*;
import com.tarumt.entity.interview.Invitation;
import com.tarumt.entity.interview.ScheduledInterview;
import com.tarumt.utility.common.Context;
import com.tarumt.utility.common.Input;
import com.tarumt.utility.pretty.annotation.ExcludeKey;
import com.tarumt.utility.pretty.annotation.OutputLength;

import java.time.LocalDateTime;
import java.util.Comparator;

public class JobApplicationController {

    private static JobApplicationController instance;
    private ListInterface<JobApplication> jobApplications = new DoublyLinkedList<>();
    private ListInterface<ScheduledInterview> scheduledInterviews = new DoublyLinkedList<>();
    private ListInterface<Invitation> invitations = new DoublyLinkedList<>();
    private final JobApplicationUI jobApplicationUI;

    private JobApplicationController() {
        Input input = new Input();
        this.jobApplications = Initializer.getJobApplications();
        this.scheduledInterviews = Initializer.getScheduledInterviews();
        this.invitations = Initializer.getInvitations();
        this.jobApplicationUI = new JobApplicationUI(input);
        updateInterviewedStatus();
    }

    public static JobApplicationController getInstance() {
        if (instance == null) {
            instance = new JobApplicationController();
        }
        return instance;
    }

    public void updateInterviewedStatus() {
        for (ScheduledInterview interview : scheduledInterviews) {
            if (interview.getTimeSlot().isPast()) {
                JobApplication application = interview.getJobApplication();
                if (application != null && application.isReadyForInterview()) {
                    application.setStatus(JobApplication.Status.INTERVIEWED);
                }
            }
        }
    }

    private ListInterface<JobApplication> getAllJobApplications() {
        updateInterviewedStatus();
        return jobApplications;
    }

    private ListInterface<ScheduledInterview> getAllScheduledInterviews() {
        updateInterviewedStatus();
        return scheduledInterviews;
    }

    private ListInterface<JobApplication> getEmployerJobApplications() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return getAllJobApplications().filter(application ->
                application.getJobPosting().getCompany() != null &&
                        application.getJobPosting().getCompany().equals(company)
        );
    }

    private ListInterface<JobApplication> getApplicantJobApplications() {
        updateInterviewedStatus();
        Applicant applicant = Context.getApplicant();
        if (applicant == null) {
            return new DoublyLinkedList<>();
        }
        return getAllJobApplications().filter(application ->
                application.getApplicant() != null &&
                        application.getApplicant().equals(applicant)
        );
    }

    private ListInterface<Invitation> getEmployerInvitations() {
        Company company = Context.getCompany();
        if (company == null) {
            return new DoublyLinkedList<>();
        }
        return invitations.filter(invitation -> {
            JobApplication app = invitation.getJobApplication();
            return app != null && app.getJobPosting() != null && company.equals(app.getJobPosting().getCompany());
        });
    }

    private ListInterface<Invitation> getApplicantInvitations() {
        Applicant applicant = Context.getApplicant();
        if (applicant == null) {
            return new DoublyLinkedList<>();
        }
        return invitations.filter(invitation -> invitation.getJobApplication().getApplicant() != null
                && invitation.getJobApplication().getApplicant().equals(applicant));
    }

    private ListInterface<ScheduledInterview> getEmployerScheduledInterviews() {
        Company company = Context.getCompany();
        return getAllScheduledInterviews().filter(
                scheduledInterview -> scheduledInterview.getJobApplication().getJobPosting().getCompany()
                        .equals(company));
    }

    private ListInterface<ScheduledInterview> getApplicantScheduledInterviews() {
        Applicant applicant = Context.getApplicant();
        return getAllScheduledInterviews()
                .filter(scheduledInterview -> scheduledInterview.getJobApplication().getApplicant().equals(applicant));
    }

    public void accessEmployer() {
        this.jobApplicationUI.accessEmployerMenu();
    }

    public void displayJobApplication() {
        if(Context.isAdmin()) {
            ListInterface<JobApplication> applications = this.getAllJobApplications();
            this.jobApplicationUI.printJobApplication(applications);
        }
        if (Context.isEmployer()) {
            ListInterface<JobApplication> applications = this.getEmployerJobApplications();
            ListInterface<JobPosting> uniqueJobPosting = getUniqueJobPostings(jobApplications);
            this.jobApplicationUI.printGroupedJobApplications(applications, uniqueJobPosting);
        }
        if (Context.isApplicant()) {
            ListInterface<JobApplication> applications = this.getApplicantJobApplications();
            this.jobApplicationUI.printJobApplication(applications);
        }
    }

    public void displayOngoingJobApplication() {
        ListInterface<JobApplication> allApplications = this.getEmployerJobApplications();
        ListInterface<JobApplication> ongoingApplications = allApplications.filter(JobApplication::isOngoing);
        ListInterface<JobPosting> uniqueJobPosting = getUniqueJobPostings(jobApplications);
        this.jobApplicationUI.printGroupedJobApplications(ongoingApplications, uniqueJobPosting);
    }

    public void displayTerminatedJobApplication() {
        ListInterface<JobApplication> allApplications = this.getEmployerJobApplications();
        ListInterface<JobApplication> terminatedApplications = allApplications.filter(JobApplication::isTerminated);
        ListInterface<JobPosting> uniqueJobPosting = getUniqueJobPostings(jobApplications);
        this.jobApplicationUI.printGroupedJobApplications(terminatedApplications, uniqueJobPosting);
    }

    private ListInterface<JobPosting> getUniqueJobPostings(ListInterface<JobApplication> jobApplications) {
        ListInterface<JobPosting> uniqueJobPostings = new DoublyLinkedList<>();
        for (JobApplication application : jobApplications) {
            JobPosting currentPosting = application.getJobPosting();
            boolean found = false;

            for (JobPosting posting : uniqueJobPostings) {
                if (posting.getId().equals(currentPosting.getId())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                uniqueJobPostings.add(currentPosting);
            }
        }
        return uniqueJobPostings;
    }

    public void shortlistApplication() {
        ListInterface<JobApplication> pendingApplications = getEmployerJobApplications().filter(
                application -> application.getStatus() == JobApplication.Status.PENDING);

        JobApplication jobApplication = jobApplicationUI.getShortlistApplicationChoice(pendingApplications);
        if (jobApplication == null) {
            return;
        }

        if (jobApplicationUI.confirmShortlist()) {
            jobApplication.setStatus(JobApplication.Status.SHORTLISTED);
            jobApplicationUI.printSuccessShortlistApplicationMsg();
        }
    }

    public void offerApplication() {
        ListInterface<JobApplication> offerableApplications = getEmployerJobApplications().filter(
                (jobApplication -> jobApplication.getStatus() == JobApplication.Status.SHORTLISTED ||
                        jobApplication.getStatus() == JobApplication.Status.INTERVIEWED));

        JobApplication jobApplication = jobApplicationUI.getOfferApplicationChoice(offerableApplications);
        if (jobApplication == null) {
            return;
        }

        boolean hasUpcomingInterview = getEmployerScheduledInterviews().anyMatch(interview ->
                interview.getJobApplication().equals(jobApplication) && !interview.getTimeSlot().isPast()
        );

        if (hasUpcomingInterview) {
            jobApplicationUI.printCannotOfferApplicationWarning();
            return;
        }

        ListInterface<Invitation> associatedInvitations = invitations.filter(invitation ->
                invitation.getJobApplication().equals(jobApplication)
        );

        if (!associatedInvitations.isEmpty()) {
            jobApplicationUI.printOfferWithInvitationWarning();
        }

        if (jobApplicationUI.confirmOffer()) {
            if (!associatedInvitations.isEmpty()) {
                invitations.removeAll(associatedInvitations);
            }
            jobApplication.setStatus(JobApplication.Status.OFFERED);
            jobApplicationUI.printSuccessOfferApplicationMsg();
        }
    }

    public void rejectApplication() {
        ListInterface<JobApplication> ongoingApplications = getEmployerJobApplications().filter(JobApplication::isOngoing);

        JobApplication jobApplication = jobApplicationUI.getRejectApplicationChoice(ongoingApplications);
        if (jobApplication == null) {
            return;
        }

        boolean hasUpcomingInterview = getEmployerScheduledInterviews().anyMatch(interview ->
                interview.getJobApplication().equals(jobApplication) && !interview.getTimeSlot().isPast()
        );

        if (hasUpcomingInterview) {
            jobApplicationUI.printCannotRejectApplicationWarning();
            return;
        }

        ListInterface<Invitation> associatedInvitations = invitations.filter(invitation ->
                invitation.getJobApplication().equals(jobApplication)
        );

        if (!associatedInvitations.isEmpty()) {
            jobApplicationUI.printRejectWithInvitationWarning();
        }

        if (jobApplicationUI.confirmReject()) {
            if (!associatedInvitations.isEmpty()) {
                invitations.removeAll(associatedInvitations);
            }
            jobApplication.setStatus(JobApplication.Status.REJECTED);
            jobApplication.setTerminatedAt(Context.getDateTime());
            jobApplicationUI.printSuccessRejectApplicationMsg();
        }
    }

    public void accessApplicant() {
        this.jobApplicationUI.accessApplicantMenu();
    }

    public void applyJobPosting() {
        while (true) {
            ListInterface<JobPosting> allOpenJobPostings = Initializer.getJobPostings().filter(
                    jobPosting -> jobPosting.getStatus() == JobPosting.Status.OPEN);
            ListInterface<JobApplication> applicantApplications = getApplicantJobApplications();
            ListInterface<JobPosting> availableJobPostings = allOpenJobPostings.filter(posting ->
                    !applicantApplications.anyMatch(application -> application.getJobPosting().getId().equals(posting.getId()))
            );

            JobPosting jobPosting = jobApplicationUI.getApplyJobPostingChoice(availableJobPostings);
            if (jobPosting == null) {
                return;
            }

            JobApplication jobApplication = new JobApplication(jobPosting, Context.getApplicant(), JobApplication.Status.PENDING, Context.getDateTime());
            jobApplications.add(jobApplication);
            jobApplicationUI.printSuccessApplyJobPostingMsg();
            if (!jobApplicationUI.continueToApplyJobPosting()) {
                return;
            }
        }
    }

    public void acceptJobApplication() {
        ListInterface<JobApplication> offeredApplications = getApplicantJobApplications().filter(
                app -> app.getStatus() == JobApplication.Status.OFFERED
        );

        JobApplication applicationToAccept = jobApplicationUI.getAcceptJobApplicationChoice(offeredApplications);
        if (applicationToAccept == null) {
            return;
        }

        if (jobApplicationUI.confirmAccept()) {
            applicationToAccept.setStatus(JobApplication.Status.ACCEPTED);
            applicationToAccept.setTerminatedAt(Context.getDateTime());
            jobApplicationUI.printSuccessAcceptJobApplicationMsg(applicationToAccept);
        }
    }

    public void withdrawJobApplication() {
        ListInterface<JobApplication> withdrawableJobApplications = getApplicantJobApplications().filter(JobApplication::isOngoing);
        JobApplication jobApplication = jobApplicationUI.getWithdrawJobApplicationChoice(withdrawableJobApplications);
        if (jobApplication == null) {
            return;
        }

        boolean hasUpcomingInterview = getApplicantScheduledInterviews().anyMatch(interview ->
                interview.getJobApplication().equals(jobApplication) && !interview.getTimeSlot().isPast()
        );

        if (hasUpcomingInterview) {
            jobApplicationUI.printCannotWithdrawScheduledInterviewWarning();
            return;
        }

        ListInterface<Invitation> associatedInvitations = invitations.filter(invitation ->
                invitation.getJobApplication().equals(jobApplication)
        );

        if (!associatedInvitations.isEmpty()) {
            jobApplicationUI.printWithdrawWithInvitationWarning();
        }

        if (jobApplicationUI.confirmWithdraw()) {
            if (!associatedInvitations.isEmpty()) {
                invitations.removeAll(associatedInvitations);
            }
            jobApplication.setStatus(JobApplication.Status.WITHDRAWN);
            jobApplication.setTerminatedAt(Context.getDateTime());
            jobApplicationUI.printSuccessWithdrawJobApplicationMsg();
        }
    }

    public static class SuccessfulJobApplication {
        @OutputLength(4)
        String id;
        @ExcludeKey("employer")
        JobPosting jobPosting;
        @OutputLength(26)
        Applicant applicant;
        @OutputLength(14)
        JobApplication.Status status;
        @OutputLength(13)
        int averageScore;
        LocalDateTime appliedAt;

        public SuccessfulJobApplication(String id, JobPosting jobPosting, Applicant applicant, JobApplication.Status status, int averageScore, LocalDateTime appliedAt) {
            this.id = id;
            this.jobPosting = jobPosting;
            this.applicant = applicant;
            this.status = status;
            this.averageScore = averageScore;
            this.appliedAt = appliedAt;
        }

        public JobPosting getJobPosting() {
            return jobPosting;
        }

        public int getAverageScore() {
            return averageScore;
        }

    }

    public void filterSuccessfulApplicant() {
        ListInterface<SuccessfulJobApplication> successfulJobApplications = new DoublyLinkedList<>();
        ListInterface<JobApplication> jobApplications = getEmployerJobApplications()
                .filter(app -> app.getStatus() == JobApplication.Status.INTERVIEWED);
        ListInterface<ScheduledInterview> allInterviews = getAllScheduledInterviews();
        ListInterface<JobPosting> uniqueJobPostings = getUniqueJobPostings(jobApplications);
        for (JobApplication jobApplication : jobApplications) {
            ListInterface<ScheduledInterview> interviews = allInterviews
                    .filter(si ->
                            si.getJobApplication().equals(jobApplication)
                                    && si.getRating() >= 0
                    );
            int totalScore = 0;
            int count = 0;
            for (ScheduledInterview si : interviews) {
                totalScore += si.getRating();
                count++;
            }
            int averageScore = (count > 0) ? (totalScore / count) : 0;

            SuccessfulJobApplication sja = new SuccessfulJobApplication(
                    jobApplication.getId(),
                    jobApplication.getJobPosting(),
                    jobApplication.getApplicant(),
                    jobApplication.getStatus(),
                    averageScore,
                    jobApplication.getAppliedAt()
            );

            if (sja.averageScore > 5)
                successfulJobApplications.add(sja);
        }
        successfulJobApplications.sort(
                Comparator.comparingInt(SuccessfulJobApplication::getAverageScore)
                        .reversed()
        );
        this.jobApplicationUI.printSuccessfulApplications(
                successfulJobApplications,
                uniqueJobPostings
        );
    }
}
