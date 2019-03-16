<?php

class Controller
{
	private $model;

	private $view = false;
	
	private $route;

	public function __contruct(){}
	
	public function showView($viewModel = false)
	{
		
		if(get_class($this) == 'LogonController' && !isset($_SESSION['USER']))
		{
			include('view/' . $this->getRoute());
		}
		else
		{
			if(isset($_SESSION['USER']))
				$userAccount = unserialize($_SESSION['USER']);
			else
			{
				$message = Message::singleton();
				$message->addWarning('Efetue o logon para acessar essa página.');
				$message->save();

				header('location: index.php');
				exit();
			}

			if($viewModel)
				foreach ($viewModel as $key => $value)
					eval('$'.$key. ' = $viewModel["$key"];');
			
			//include('view/default/header.php');

			//include("view/helper/message.php");
			
			include("view/".$this->getRoute());
			
			//include('view/default/footer.php');
		}
	}
	public function getView()
	{
		return $this->view;
	}

	public function setRoute($route)
	{
		$this->route = $route; 
	}
	public function getRoute()
	{
		return $this->route;
	}
}