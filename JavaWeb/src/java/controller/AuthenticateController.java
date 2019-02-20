/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AuthenticateDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import util.Message;

/**
 *
 * @author SoldierJVX
 */
public class AuthenticateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Message message = Message.singleton();

        AuthenticateDao authenticateDao = new AuthenticateDao();

        String email = request.getParameter("login");

        String password = request.getParameter("senha");

        User user = authenticateDao.authenticate(email, password);

        RequestDispatcher view = request.getRequestDispatcher("view/home.jsp");

        if (user == null) {
            view = request.getRequestDispatcher("index.html");

            message.addWarning("Usuário ou senha incorreto!");
        } else {

            HttpSession session = request.getSession(true);

            session.setAttribute("user", user);

            message.addMessage("Usuário autenticado com sucesso!");
        }

        request.setAttribute("message", message);

        view.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
