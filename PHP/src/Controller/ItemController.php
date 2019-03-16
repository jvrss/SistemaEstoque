<?php

class ItemController extends Controller {
    
    private $itemDao;

    public function __construct() {
        $this->itemDao = new ItemDao();
    }
    
    public function getAllAction(){
        
        $list = $this->itemDao->getAll();
        
        foreach ($list as $item){
            
            echo "<tr onclick='editItem(" . $item->id . ")' style='cursor:pointer;'>";
            echo "<th>" . $item->id . "</th>";
            echo "<td>" . $item->name . "</td>";
            echo "<td>" . $item->amount . "</td>";
            echo "<td>" . date("d/n/Y", strtotime($item->createdDate)) . "</td>";
            echo "<td>" . date("d/n/Y", strtotime($item->lastUpdate)) . "</td>";
            echo "</tr>";
            
        }
        
    }

}
