package com.org.servlet;

import com.org.dao.DoctorDao;
import com.org.entity.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/CloneDoctor")
public class CloneDoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        DoctorDao doctorDao = new DoctorDao();
        Doctor originalDoctor = doctorDao.getDoctorsById(doctorId);

        if (originalDoctor != null) {
            try {
                Doctor clonedDoctor = (Doctor) originalDoctor.clone();
                // Generare automată a unei adrese de e-mail unice pentru doctorul clonat
                String newEmail = generateUniqueEmail(originalDoctor.getEmail());
                clonedDoctor.setEmail(newEmail);
                clonedDoctor.setId(0);
                boolean success = doctorDao.registerDoctor(clonedDoctor);
                if (success) {
                    request.getSession().setAttribute("succMsg", "Doctor cloned successfully!");
                } else {
                    request.getSession().setAttribute("errorMsg", "Failed to clone doctor!");
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                request.getSession().setAttribute("errorMsg", "Cloning not supported for this object!");
            }
        } else {
            request.getSession().setAttribute("errorMsg", "Original doctor not found!");
        }
        response.sendRedirect(request.getContextPath() + "/admin/view_doctor.jsp");
    }

    // Metodă pentru generarea unei adrese de e-mail unice
 // Metodă pentru generarea unei adrese de e-mail unice
    private String generateUniqueEmail(String originalEmail) {
        String newEmail = originalEmail;
        DoctorDao doctorDao = new DoctorDao();
        int suffix = 1;

        // Verificăm dacă adresa de e-mail există deja în baza de date
        while (doctorDao.isEmailExists(newEmail)) {
            // Concatenăm un sufix numeric la adresa de e-mail originală
            newEmail = originalEmail + suffix;
            suffix++;
        }

        return newEmail;
    }

}
