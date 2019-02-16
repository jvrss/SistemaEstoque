/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadesk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javadesk.dao.ItemDao;
import javadesk.model.Item;
import javadesk.view.FormItem;
import javadesk.view.FormMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author SoldierJVX
 */
public class ItemController implements ActionListener {

    private MenuController controllerMenu;
    private FormItem view;
    private Item item;

    ItemController(MenuController controllerMenu) {
        this.controllerMenu = controllerMenu;

        view = new FormItem();

        view.getBtnAdicionar().addActionListener(this);
        view.getBtnCancelar().addActionListener(this);

    }

    ItemController(int i, MenuController controllerMenu) {
        this.controllerMenu = controllerMenu;
        
        
        ItemDao dao = new ItemDao();
        
        item = dao.get(i);

        view = new FormItem(item);

        view.getBtnAdicionar().addActionListener(this);
        view.getBtnCancelar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean status = false;

        switch (e.getActionCommand()) {

            case "Adicionar":

                status = adicionarItem();

                if (status) {
                    showMenu();
                }

                break;

            case "Alterar":

                status = alterarItem(item);

                if (status) {
                    showMenu();
                }

                break;

            case "Cancelar":

                showMenu();

        }
    }

    private void showMenu() {

        view.setVisible(false);
        controllerMenu.atualizarTabela();
        controllerMenu.getView().setEnabled(true);
        view.dispose();

    }

    private boolean adicionarItem() {

        String nome;
        String descricao;
        int quantidade;

        nome = view.getTxtNome().getText();
        descricao = view.getTxtDescricao().getText();

        try {
            quantidade = Integer.parseInt(view.getTxtQuantidade().getText());
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Quantidade Inválida", "Quantidade Inválida", JOptionPane.ERROR_MESSAGE);
            return false;

        }

        ItemDao dao = new ItemDao();

        boolean result = dao.salvarItem(nome, descricao, quantidade);

        if (!result) {

            JOptionPane.showMessageDialog(null, "Não foi possível inserir no banco!", "Não foi possível inserir no banco!", JOptionPane.ERROR_MESSAGE);

            return false;

        }

        return true;

    }

    private boolean alterarItem(Item item) {
        int id = item.getId();
        String nome;
        String descricao;
        int quantidade;

        nome = view.getTxtNome().getText();
        descricao = view.getTxtDescricao().getText();

        try {
            quantidade = Integer.parseInt(view.getTxtQuantidade().getText());
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Quantidade Inválida", "Quantidade Inválida", JOptionPane.ERROR_MESSAGE);
            return false;

        }
        
        ItemDao dao = new ItemDao();

        boolean result = dao.alterarItem(id, nome, descricao, quantidade);

        if (!result) {

            JOptionPane.showMessageDialog(null, "Não foi possível alterar no banco!", "Não foi possível alterar no banco!", JOptionPane.ERROR_MESSAGE);

            return false;

        }

        return true;
    }

}
