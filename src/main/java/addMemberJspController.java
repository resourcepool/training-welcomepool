/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.java;

import main.java.Querys.Member;
import main.java.Querys.MemberDAO;
import main.java.Querys.Promotion;
import main.java.Querys.PromotionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <p>
 * A simple servlet taking advantage of features added in 3.0.
 * </p>
 *
 * <p>
 * The servlet is registered and mapped to /HelloServlet using the {@linkplain WebServlet
 * @HttpServlet}. The {@link} is injected by CDI.
 * </p>
 *
 */
@SuppressWarnings("serial")
@WebServlet("/addmem")
public class addMemberJspController extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Servlet initialized successfully");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URL = "jdbc:mysql://localhost:3306/welcome_pool_Code_Review";
        String USERNAME = "SVC_Java";
        String PASSWORD = "1xqOOMTNMjnzZ76TPaRA";
        PromotionDAO dao = new PromotionDAO(URL, USERNAME, PASSWORD);
        List<String> promotionsNames = new ArrayList<>();

        try {
            List<Promotion> classes = dao.getAll();
            for(Promotion p: classes) {
                System.out.println(p.getName());
                promotionsNames.add(p.getName());
            }
        } catch (Exception e){
            System.out.println(e);
        }
        req.setAttribute("classes", promotionsNames);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/add_member.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String URL = "jdbc:mysql://localhost:3306/welcome_pool_Code_Review";
        String USERNAME = "SVC_Java";
        String PASSWORD = "1xqOOMTNMjnzZ76TPaRA";
        MemberDAO mDAO = new MemberDAO(URL, USERNAME, PASSWORD);

        if (request.getParameter("addMem") != null) {
            logger.info("COUCOU");
            logger.info(request.getParameter("name"));

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
            System.out.println("addMem");
            String dateInString = "1995-05-02";
            java.util.Date utilbirthdate = new java.util.Date();
            try {
                utilbirthdate = formatter.parse(dateInString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            java.sql.Date birthdate = new java.sql.Date(utilbirthdate.getTime());

            String c_selected = request.getParameter("classes_selected");

            try {
                System.out.println("REMI n'A RAISON");
                mDAO.add(new Member(name,email,birthdate,1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/Pool/index");
        }
        else if(request.getParameter("modifMem")!=null){
            List<String> classes = new ArrayList<>();
            classes.add("Fevrier");
            classes.add("Mars");
            request.setAttribute("classes", classes);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("email",email);
            request.setAttribute("name",name);
            request.setAttribute("id",id);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
            System.out.println("addMem");
            String dateInString = "1995-05-02";
            java.util.Date utilbirthdate = new java.util.Date();
            try {
                utilbirthdate = formatter.parse(dateInString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            System.out.println("modifmem");

            java.sql.Date birthdate = new java.sql.Date(utilbirthdate.getTime());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add_member.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("updateMem") != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
            System.out.println("addMem");
            String dateInString = "1995-05-02";
            java.util.Date utilbirthdate = new java.util.Date();
            try {
                utilbirthdate = formatter.parse(dateInString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            java.sql.Date birthdate = new java.sql.Date(utilbirthdate.getTime());
            String c_selected = request.getParameter("classes_selected");
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                System.out.println("gdhs");
                Member m = new Member(name,email,birthdate,1);
                m.setId(id);
                mDAO.update(m);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/Pool/index");
        }
        else if (request.getParameter("deleteMem") != null) {
            System.out.println("DELETE");
            int id = Integer.parseInt(request.getParameter("id"));

            System.out.println(id);

            try {
                mDAO.deleteById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/Pool/index");
        }

        //request.getRequestDispatcher("/index").forward(request,response);
        //request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
    }


}