/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadesk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javadesk.dao.AuthenticateDao;
import javadesk.model.User;
import javadesk.view.FormLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author SoldierJVX
 */
public class LoginController implements ActionListener {

    private FormLogin view;

    public LoginController() {

        view = new FormLogin();

        view.getBtnLogin().addActionListener(this);
        view.getBtnSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Logar":

                autenticarUsuario();

                break;

            case "Sair":

                System.exit(0);

        }

    }

    private void autenticarUsuario() {

        String login = view.getTxtLogin().getText();
        String senha = view.getTxtSenha().getText();

        AuthenticateDao dao = new AuthenticateDao();

        User usuario = dao.authenticate(login, senha);

        if (usuario != null) {
            
            view.setVisible(false);
            view.dispose();
            view = null;

            new MenuController();

        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encotrado!", "Usuário não encotrado!", JOptionPane.ERROR_MESSAGE);
        }

    }

}
