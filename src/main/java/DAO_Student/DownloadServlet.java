package DAO_Student;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import Beans.student;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the student object from the session
        HttpSession session = request.getSession();
        student loggedInStudent = (student) session.getAttribute("loggedInStudent");

        // Check if the loggedInStudent and cv data are not null
        if (loggedInStudent != null && loggedInStudent.getCv() != null) {
            // Set the content type based on the file type
            response.setContentType("application/pdf"); // Assuming CVs are in PDF format, update if needed

            // Set the headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", "cv.pdf");
            response.setHeader(headerKey, headerValue);

            // Copy the CV content to the response output stream
            try (InputStream inputStream = new ByteArrayInputStream(loggedInStudent.getCv());
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Handle the case when there is no logged-in student or no CV data
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "CV not found");
        }
    }
}

