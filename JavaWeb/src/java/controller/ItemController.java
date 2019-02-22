/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ItemDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;

/**
 *
 * @author SoldierJVX
 */
public class ItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        switch (operation) {

            case "list":
                list(response);
                break;

            case "add":

                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                String quantidade = request.getParameter("quantidade");

                add(nome, descricao, quantidade, response);
                break;

        }

    }

    private void list(HttpServletResponse response) throws IOException {

        ItemDao dao = new ItemDao();
        List<Item> list = dao.list();

        StringBuilder sb = new StringBuilder();

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        for (int i = 0; i < list.size(); i++) {

            sb.append("<tr>");
            sb.append("<th>" + list.get(i).getId() + "</th>");
            sb.append("<td>" + list.get(i).getName() + "</td>");
            sb.append("<td>" + list.get(i).getAmount() + "</td>");
            sb.append("<td>" + dateFormat.format(list.get(i).getCreatedDate()) + "</td>");
            sb.append("<td>" + dateFormat.format(list.get(i).getLastUpdate()) + "</td>");
            sb.append("</tr>");

        }

        PrintWriter writer = response.getWriter();
        writer.append(sb.toString());

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

    private void add(String nome, String descricao, String quantidade, HttpServletResponse response) throws IOException {
        
        System.out.println("HEre");

        ItemDao dao = new ItemDao();
        
        try {
            
            dao.salvarItem(nome, descricao, Integer.parseInt(quantidade));

        } catch (Exception e) {
            
            dao.salvarItem(nome, descricao, 0);
        }
        
        PrintWriter writer = response.getWriter();
        writer.append("OK");

    }

}
