<?php

class User {

    private $id;
    private $login;
    private $name;

    function getId() {
        return $this->id;
    }

    function getLogin() {
        return $this->login;
    }

    function getName() {
        return $this->name;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setLogin($login) {
        $this->login = $login;
    }

    function setName($name) {
        $this->name = $name;
    }

}
