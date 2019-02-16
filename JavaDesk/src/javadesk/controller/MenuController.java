/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadesk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javadesk.dao.ItemDao;
import javadesk.model.Item;
import javadesk.view.FormMenu;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SoldierJVX
 */
public class MenuController implements ActionListener {

    private FormMenu view;

    public MenuController() {

        view = new FormMenu();

        view.getBtnAdicionar().addActionListener(this);
        view.getBtnAlterar().addActionListener(this);
        view.getBtnSair().addActionListener(this);

        atualizarTabela();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Adicionar":

                view.setEnabled(false);
                new ItemController(this);

                break;

            case "Alterar":

                if (view.getTblItens().getSelectedRow() == -1) {

                    JOptionPane.showMessageDialog(null, "Escolha um registro primeiro!", "Escolha um registro primeiro!", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    view.setEnabled(false);
                    new ItemController((int) view.getTblItens().getModel().getValueAt(view.getTblItens().getSelectedRow(), 0), this);

                }

                break;
            case "Sair":
                System.exit(0);

        }
    }

    public void atualizarTabela() {

        DefaultTableModel model = (DefaultTableModel) view.getTblItens().getModel();

        limparModelo(model);

        ItemDao dao = new ItemDao();

        List<Item> list = dao.list();

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        for (int i = 0; i < list.size(); i++) {

            Object[] novosDados = {list.get(i).getId(), list.get(i).getName(), list.get(i).getAmount(), dateFormat.format(list.get(i).getCreatedDate()), dateFormat.format(list.get(i).getLastUpdate())};

            model.addRow(novosDados);

        }

        view.getTblItens().setModel(model);

    }

    public FormMenu getView() {
        return view;
    }

    private void limparModelo(DefaultTableModel model) {

        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }

    }

}
