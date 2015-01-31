<?php
    include"../koneksi.php";

    $query = mysql_query("select * from master_provinsi") or die(mysql_error());

    $json = '{"prov" : [';

    while($d = mysql_fetch_assoc($query))
    {
        $char = '"';
        $json .= '{"id" : "'.$d['provinsi_id'].'",
                "nama_prov" : "'.$d['provinsi_nama'].'",
                "gambar" : "http://192.168.43.128/indonesian_culture/logo/'.$d['gambar_logo'].'"},';
    }

    $json = substr($json, 0, strlen($json)-1);
    $json .= ']}';

    echo $json;

?>