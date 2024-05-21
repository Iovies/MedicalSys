package com.org.dao;

public class DaoWrapper 
{
    public final AppointmentDao appointmentDao;
    public final DoctorDao doctorDao;
    public final SpecialistDao specialistDao;
    public final UserDao userDao;

    public DaoWrapper() {
        this.appointmentDao = new AppointmentDao();
        this.doctorDao = new DoctorDao();
        this.specialistDao = new SpecialistDao();
        this.userDao = new UserDao();
    }

   

}
