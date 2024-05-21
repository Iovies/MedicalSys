//package com.org.servlet.admin;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.org.dao.DoctorDao;
//import com.org.entity.Doctor;
//
//@WebServlet("/CloneDoctor")
//
//
//public class CloneDoctor extends HttpServlet {
// 
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	    try {
//	        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
//
//	        // Obține doctorul original din baza de date
//	        DoctorDao dao = new DoctorDao();
//	        Doctor originalDoctor = dao.getDoctorsById(doctorId);
//
//	        // Clonează și salvează doctorul
//	        if (dao.cloneDoctor(originalDoctor)) {
//	            HttpSession session = req.getSession();
//	            session.setAttribute("succMsg", "Doctor Cloned Successfully");
//	        } else {
//	            HttpSession session = req.getSession();
//	            session.setAttribute("errorMsg", "Something Went Wrong");
//	        }
//
//	        resp.sendRedirect("admin/view_doctor.jsp");
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        HttpSession session = req.getSession();
//	        session.setAttribute("errorMsg", "Something Went Wrong");
//	        resp.sendRedirect("admin/view_doctor.jsp");
//	    }
//	}
//
//}


package com.org.servlet.admin;

import com.org.dao.DoctorDao;
import com.org.entity.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CloneDoctor")
public class CloneDoctor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        DoctorDao doctorDao = new DoctorDao();
        Doctor originalDoctor = doctorDao.getDoctorsById(doctorId);

        if (originalDoctor != null) {
            boolean success = doctorDao.cloneDoctor(originalDoctor);
            if (success) {
                request.getSession().setAttribute("succMsg", "Doctor cloned successfully.");
            } else {
                request.getSession().setAttribute("errorMsg", "Failed to clone doctor.");
            }
        } else {
            request.getSession().setAttribute("errorMsg", "Doctor not found.");
        }
        response.sendRedirect("view_doctor.jsp");
    }
}

