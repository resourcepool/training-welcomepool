package main.java;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;


public class addReviewServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = LoggerFactory.getLogger(addMemberServlet.class);

        if (request.getParameter("addReview") != null) {
            //logger.info(request.getParameterValues("name").toString());
        }

        //request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
    }
}
