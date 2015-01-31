<?php
    include"../koneksi.php";

    $id = $_GET['id'];

    $query = mysql_query("select * from master_provinsi where provinsi_id = '".$id."'") or die(mysql_error());

    $json = '{"prov" : [';

    while($d = mysql_fetch_assoc($query))
    {
        $char = '"';
        $json .= '{"id" : "'.$d['provinsi_id'].'",
                "nama_prov" : "'.str_replace($char, ' ', strip_tags($d['provinsi_nama'])).'",
                "keterangan" : "'.str_replace($char,' ', strip_tags($d['keterangan'])).'",
                "gambar" : "http://192.168.43.128/indonesian_culture/images/'.$d['gambar'].'"},';
    }

    $json = substr($json, 0, strlen($json)-1);
    $json .= ']}';

    echo $json;

?>