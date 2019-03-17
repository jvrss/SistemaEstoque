<?php

class ItemController extends Controller {

    private $itemDao;

    public function __construct() {
        $this->itemDao = new ItemDao();
    }

    public function getAllAction() {

        $list = $this->itemDao->getAll();

        foreach ($list as $item) {

            echo "<tr onclick='editItem(" . $item->id . ")' style='cursor:pointer;'>";
            echo "<th>" . $item->id . "</th>";
            echo "<td>" . $item->name . "</td>";
            echo "<td>" . $item->amount . "</td>";
            echo "<td>" . date("d/n/Y", strtotime($item->createdDate)) . "</td>";
            echo "<td>" . date("d/n/Y", strtotime($item->lastUpdate)) . "</td>";
            echo "</tr>";
        }
    }

    public function addAction() {

        $nome = $_REQUEST["nome"];
        $descricao = $_REQUEST["descricao"];
        $quantidade = $_REQUEST["quantidade"];

        $item = new Item();
        $item->name = $nome;
        $item->description = $descricao;
        $item->amount = $quantidade;

        $this->itemDao->insert($item);
    }

    public function getAction() {

        $id = $_REQUEST["id"];

        echo json_encode($this->itemDao->get($id));
    }

    public function updateAction() {


        $nome = $_REQUEST["nome"];
        $descricao = $_REQUEST["descricao"];
        $quantidade = $_REQUEST["quantidade"];
        $id = $_REQUEST["id"];

        $item = new Item();

        $item->id = $id;
        $item->name = $nome;
        $item->description = $descricao;
        $item->amount = $quantidade;

        try {
            $this->itemDao->update($item);
        } catch (Exception $exc) {
            echo $exc;
        }
    }

}
