package com.example.findjobbe.service.ApplyJob;

import com.example.findjobbe.model.*;
import com.example.findjobbe.repository.ApplyJobRepository;
import com.example.findjobbe.service.CompanyService;
import com.example.findjobbe.service.ICoreService;
import com.example.findjobbe.service.Notification.NotificationService;
import com.example.findjobbe.service.UserService;
import com.example.findjobbe.service.jobs.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyJobService implements ICoreService<ApplyJob> {
    @Autowired
    ApplyJobRepository applyJobRepository;
    @Autowired
    UserService userService;
    @Autowired
    JobService jobService;
    @Autowired
    CompanyService companyService;
    @Autowired
    NotificationService notificationService;


    @Override
    public Page<ApplyJob> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ApplyJob findOne(Long id) {
        return applyJobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ApplyJob applyJob) {
        applyJobRepository.save(applyJob);
    }

    @Override
    public void delete(Long id) {
        applyJobRepository.deleteById(id);

    }

    //ứng tuyển
    public boolean applyJob(Long jobId,Long userId){
        User user = userService.findOne(userId);
        Job job = jobService.findOne(jobId);
        ApplyJob applyJobFind = applyJobRepository.findApplyJobByJob_IdAndUser_Id(jobId,userId);
        if (user!=null && job!=null){
            if(applyJobFind==null){
            ApplyJob applyJob = new ApplyJob();
            applyJob.setUser(user);
            applyJob.setJob(job);
            applyJob.setStatus("Pending");
            applyJobRepository.save(applyJob);
            Notification notification = new Notification();
            String text = user.getAccount().getName() + " applied for "+ job.getEmployeeType().getName()+"-"+
                           job.getCompany().getAccount().getName();
            notification.setText(text);
            notification.setStatus(true);
            notification.setAccount(applyJob.getJob().getCompany().getAccount());
            notificationService.save(notification);
            return true;
            }
            if (applyJobFind.getStatus().equals("Canceled") || applyJobFind.getStatus().equals("Rejected") ){
                applyJobFind.setStatus("Pending");
                applyJobRepository.save(applyJobFind);
                Notification notification = new Notification();
                String text = user.getAccount().getName() + " applied for "+ job.getEmployeeType().getName()+"-"+
                        job.getCompany().getAccount().getName();
                notification.setText(text);
                notification.setStatus(true);
                notification.setAccount(applyJobFind.getJob().getCompany().getAccount());
                notificationService.save(notification);
                return true;
            }
        }
        return false;
    }

    //hủy ứng tuyển
    public boolean cancelApplyJob(Long applyJobId){
        ApplyJob applyJobFind = applyJobRepository.findById(applyJobId).orElse(null);
        if (applyJobFind!=null){
                applyJobFind.setStatus("Canceled");
                applyJobRepository.save(applyJobFind);
                Notification notification = new Notification();
                String text = applyJobFind.getUser().getAccount().getName() + " canceled "+ applyJobFind.getJob().getEmployeeType().getName()
                     + "-" + applyJobFind.getJob().getCompany().getAccount().getName() + " !";
                notification.setText(text);
                notification.setAccount(applyJobFind.getJob().getCompany().getAccount());
                notification.setStatus(true);
                notificationService.save(notification);
                return true;
        }
        return false;
    }

    //chấp nhận apply
    public Boolean acceptApplyJob(Long id){
        ApplyJob applyJob= applyJobRepository.findById(id).orElse(null);
        if (applyJob != null){
            applyJob.setStatus("Accepted");
            applyJobRepository.save(applyJob);
            Notification notification = new Notification();
            String text = applyJob.getJob().getCompany().getAccount().getName() + " accepted your apply for"
                    + applyJob.getJob().getEmployeeType().getName()+"-"+applyJob.getJob().getCompany().getAccount().getName();
            notification.setText(text);
            notification.setAccount(applyJob.getUser().getAccount());
            notification.setStatus(true);
            notificationService.save(notification);
            Job job = applyJob.getJob();
            Long quantity = job.getQuantity();
            if (quantity >0 ){
                if (quantity==1){
                    job.setQuantity(0L);
                    job.setStatus(false);
                    jobService.save(job);
                    List<ApplyJob> applyJobs = applyJobRepository.findAllByJob_Id(applyJob.getId());
                    for (ApplyJob a:applyJobs) {
                        if (a.getStatus().equals("Pending")){
                            rejectApplyJob(a.getId());
                        }
                    }
                }else {
                    job.setQuantity(quantity-1);
                    jobService.save(job);
                }

            }
            return true;
        }
        return false;
    }

    public Boolean rejectApplyJob(Long id){
        ApplyJob applyJob= applyJobRepository.findById(id).orElse(null);
        if (applyJob!=null){
            applyJob.setStatus("Rejected");
            applyJobRepository.save(applyJob);
            Notification notification = new Notification();
            String text = applyJob.getJob().getCompany().getAccount().getName()+
                    " rejected your apply for "+applyJob.getJob().getEmployeeType().getName()+
                    "-"+applyJob.getJob().getCompany().getAccount().getName();
            notification.setText(text);
            notification.setAccount(applyJob.getUser().getAccount());
            notification.setStatus(true);
            notificationService.save(notification);
            return true;
        }
       return false;
    }

    public List<ApplyJob> findAllByCompanyId(Long id){
        return applyJobRepository.findAllByJob_Company_Id(id);
    }
    public Page<ApplyJob> findAllByUserId(Long id,Pageable pageable){
        return applyJobRepository.findAllByUserId(id,pageable);
    }
    public List<ApplyJob> findAllByJobId(Long id){
        return applyJobRepository.findAllByJob_Id(id);
    }

    public Boolean lockOrUnlockJob(Long id){
        Job job = jobService.findOne(id);
        if (job!=null) {
            if (job.getStatus()) {
                List<ApplyJob> applyJobList = applyJobRepository.findAllByJob_Id(id);
                for (ApplyJob a : applyJobList) {
                    if (a.getStatus().equals("Pending")){
                        rejectApplyJob(a.getId());
                    }
                }
                job.setStatus(false);
            } else {
                job.setStatus(true);
            }
            jobService.save(job);
            return true;
        }
        return false;
    }
}
