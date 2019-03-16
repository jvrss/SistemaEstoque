<?php

class ItemDao {

    const _table = 'item';

    public function __construct() {
        
    }

    public function insert(Item $item) {
        $db = Database::singleton();

        $sql = 'INSERT INTO ' . self::_table . ' (name, description, amount) VALUES (?,?,?)';

        $sth = $db->prepare($sql);

        $sth->bindValue(1, strtolower(trim($item->getName())), PDO::PARAM_STR);
        $sth->bindValue(2, trim($item->getDescription()), PDO::PARAM_STR);
        $sth->bindValue(3, trim(sha1($item->getAmount())), PDO::PARAM_STR);

        return $sth->execute();
    }

    public function update(Item $item) {
        $db = Database::singleton();

        $sql = 'UPDATE ' . self::_table . ' SET name = ?, description, amount = ?, last_update = now() WHERE id = ?';

        $sth = $db->prepare($sql);

        $sth->bindValue(1, strtolower(trim($item->getName())), PDO::PARAM_STR);
        $sth->bindValue(2, trim($item->getDescription()), PDO::PARAM_STR);
        $sth->bindValue(3, $item->getAmount(), PDO::PARAM_INT);
        $sth->bindValue(4, $item->getId(), PDO::PARAM_INT);

        return $sth->execute();
    }

    public function getAll() {

        $db = Database::singleton();

        $sql = 'SELECT * FROM ' . self::_table;

        $sth = $db->prepare($sql);

        $sth->execute();

        $itens = array();

        while ($obj = $sth->fetch(PDO::FETCH_OBJ)) {
            $item = new Item();
            
            $item->id = $obj->id;
            $item->name = $obj->name;
            $item->description = $obj->description;
            $item->amount = $obj->amount;
            $item->createdDate = $obj->created_date;
            $item->lastUpdate = $obj->last_update;
            
            array_push($itens, $item);
        }

        return $itens;
    }

}
