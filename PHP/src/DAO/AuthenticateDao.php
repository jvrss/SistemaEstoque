<?php

class AuthenticateDao {

    const _table = 'user_data';

    public function authenticate($login, $password) {
        
        $db = Database::singleton();

        $sql = 'SELECT * FROM ' . self::_table . ' WHERE login = ? AND password = ?';

        $sth = $db->prepare($sql);
        
        $sth->bindValue(1, strtolower(trim($login)), PDO::PARAM_STR);
        $sth->bindValue(2, strtolower(trim($password)), PDO::PARAM_STR);
        
        $sth->execute();

        if ($obj = $sth->fetch(PDO::FETCH_OBJ)) {
            $user = new User();
            $user->setId($obj->id);
            $user->setLogin($obj->login);
            $user->setName($obj->name);
        }

        return $user;
        
    }

}
