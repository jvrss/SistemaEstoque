<?php

class LogonController extends Controller {

    private $userDao;

    public function __construct() {
        $this->view = new LogonView();

        $this->userDao = new AuthenticateDao();
    }

    public function loginAction() {

        $message = Message::singleton();

        $email = array_key_exists('login', $_POST) ? $_POST['login'] : '';

        $password = array_key_exists('senha', $_POST) ? $_POST['senha'] : '';

        try {

            if (empty($email))
                throw new Exception('Preencha o campo Login.');

            if (empty($password))
                throw new Exception('Preencha o campo Senha.');

            if ($user = $this->userDao->authenticate($email, $password)) {

                $message->addMessage('Usuário autenticado com sucesso.');

                $this->setRoute($this->view->getIndexRoute());

                $_SESSION['USER'] = serialize($user);
            } else {
                $message->addWarning('Login ou Senha incorretos.');

                $this->setRoute($this->view->getLogonRoute());
            }
        } catch (Exception $e) {

            $this->setRoute($this->view->getLogonRoute());

            $message->addWarning($e->getMessage());
        }

        $this->showView();

        return;
    }
    
    public function indexAction(){
        
        $this->setRoute($this->view->getIndexRoute());
        
        $this->showView();
        
    }

    public function logoffAction() {
        unset($_SESSION['USER']);

        $this->setRoute($this->view->getLogonRoute());

        $message = Message::singleton();

        $message->addMessage('Você foi deslogado com sucesso.');

        $this->showView();
    }

}
