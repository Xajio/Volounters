<?php
$db = new mysqli ('localhost', 'license', 'password', 'samsung'); //A6v4F6t0
$db->set_charset("utf8");
if(isset($_GET["getEvents"])){
    $query = $db->query("SELECT * FROM `eventssamsung`");
    $result = $query->fetch_all();
    $counts = count($result);
    for($i = 0; $i < $counts; $i++){
        echo $result[$i]['0']."%".$result[$i]['1']."%".$result[$i]['2']."%".$result[$i]['4']."%".$result[$i]["5"];
        if($i != ($counts - 1)){
            echo "/";
        }
    }
}

if(isset($_GET['checkClient'])){
    if(isset($_GET['mail']) && isset($_GET['password'])){
        $mail = $_GET['mail'];
        $password = $_GET['password'];
    }
}

if(isset($_GET["registerOrganisation"])){
    $INN = $_GET['INN'];
    $result = Dadata::suggest("findById/party", array('query' => $INN, 'branch_type' => 'MAIN'));
    if(isset($result['suggestions'][0]['value']) && isset($_GET['mail']) && isset($_GET['firstName']) && isset($_GET['secondName']) && isset($_GET['password']) && isset($_GET['phoneNumber'])){
        $mail = $_GET['mail'];
        $firstName = $_GET['firstName'];
        $secondName = $_GET['secondName'];
        $password = $_GET['password'];
        $phoneNumber = $_GET['phoneNumber'];
        $db->query("INSERT INTO `organisations` (`organisationName`, `firstName`, `secondName`, `phoneNumber`, `password`, `Inn`, `email`) VALUES ('". $result['suggestions'][0]['value'] ."', '". $firstName ."', '". $secondName ."',
         '". $phoneNumber ."', '". $password ."', '". $INN ."', '". $mail ."')"); 
        echo '1';
        exit;    
    }
    echo '0';
}

class Dadata
{
    public static function suggest($type, $fields)
    {
        $result = false;
        if ($ch = curl_init("http://suggestions.dadata.ru/suggestions/api/4_1/rs/$type"))
        {
             curl_setopt ($ch, CURLOPT_RETURNTRANSFER, 1);
             curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                 'Content-Type: application/json',
                 'Accept: application/json',
                 'Authorization: Token <apikey>' //622b1eaa3c35a4bc6a64375eab08078b93d6cdee
              ));
             curl_setopt($ch, CURLOPT_POST, 1);
             // json_encode
             curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
             $result = curl_exec($ch);
             $result = json_decode($result, true);
             curl_close($ch);
        }
        return $result;
    }
}
?>