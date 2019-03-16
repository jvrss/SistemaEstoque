<?php
$message = Message::singleton();

if ($message->has()) {
    ?>
    <div id="idMessage">

        <?php while ($msg = $message->get())
            echo $msg;
        ?>


    </div>
    <?php
    $message->clear();
}