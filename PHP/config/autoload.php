<?php

function autoLoader($className)
{

  if(file_exists('common-class/'. $className . '.php'))
      require_once('common-class/'. $className . '.php');

  if(file_exists('src/Controller/'. $className . '.php'))
      require_once('src/Controller/'. $className . '.php');
  
  if(file_exists('src/DAO/'. $className . '.php'))
      require_once('src/DAO/'. $className . '.php');

  if(file_exists('src/Model/'. $className . '.php'))
      require_once('src/Model/'. $className . '.php');
  
  

  if(file_exists('src/View/'. $className . '.php'))
      require_once('src/View/'. $className . '.php');
}

spl_autoload_register("autoLoader");