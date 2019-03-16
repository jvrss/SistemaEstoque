package controller;

import com.google.gson.Gson;
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
        int id;
        String nome;
        String descricao;
        String quantidade;

        switch (operation) {

            case "list":
                list(response);
                break;

            case "get":

                id = Integer.parseInt(request.getParameter("id"));

                get(id, response);
                break;

            case "set":

                id = Integer.parseInt(request.getParameter("id"));
                nome = request.getParameter("nome");
                descricao = request.getParameter("descricao");
                quantidade = request.getParameter("quantidade");

                set(id, nome, descricao, quantidade);
                break;

            case "add":

                nome = request.getParameter("nome");
                descricao = request.getParameter("descricao");
                quantidade = request.getParameter("quantidade");

                add(nome, descricao, quantidade, response);
                break;

        }

    }

    private void list(HttpServletResponse response) throws IOException {

        ItemDao dao = new ItemDao();
        List<Item> list = dao.list();

        StringBuilder sb = new StringBuilder();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < list.size(); i++) {

            sb.append("<tr onclick='editItem(" + list.get(i).getId() + ")' style='cursor:pointer;'>");
            sb.append("<th>" + list.get(i).getId() + "</th>");
            sb.append("<td>" + list.get(i).getName() + "</td>");
            sb.append("<td>" + list.get(i).getAmount() + "</td>");
            sb.append("<td>" + dateFormat.format(list.get(i).getCreatedDate()) + "</td>");
            sb.append("<td>" + dateFormat.format(list.get(i).getLastUpdate()) + "</td>");
            sb.append("</tr>");

        }

        if (list.size() > 0) {

            PrintWriter writer = response.getWriter();
            writer.append(sb.toString());

        }

        response.setStatus(HttpServletResponse.SC_OK);

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

        ItemDao dao = new ItemDao();

        try {

            dao.insertItem(nome, descricao, Integer.parseInt(quantidade));

        } catch (Exception e) {

            dao.insertItem(nome, descricao, 0);
        }

        PrintWriter writer = response.getWriter();
        writer.append("OK");

    }

    private void get(int id, HttpServletResponse response) throws IOException {

        ItemDao dao = new ItemDao();
        Gson gson = new Gson();

        Item item = dao.get(id);

        PrintWriter writer = response.getWriter();
        writer.append(gson.toJson(item));

    }

    private void set(int id, String nome, String descricao, String quantidade) {
        
        ItemDao dao = new ItemDao();
        
        dao.updateItem(id, nome, descricao, quantidade);
        
    }

}
