<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

session_start();

require_once('config/autoload.php');

try {

    $controller = !isset($_REQUEST['controller']) ? 'Logon' : $_REQUEST['controller'];

    $action = !isset($_REQUEST['action']) ? 'login' : $_REQUEST['action'];

    eval('$controller = new ' . $controller . 'Controller();');

    eval('$controller->' . $action . 'Action();');
    
} catch (Exception $e) {
    error_log($e->getMessage());
}
