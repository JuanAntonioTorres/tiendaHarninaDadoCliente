<%--
  Created by IntelliJ IDEA.
  User: Luciano
  Date: 28/11/2018
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Avatar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1, user-scalable=no">
    <link type="text/css" rel="stylesheet" href="../css/catalogoEstilo.css">
    <link type="text/css" rel="stylesheet" href="../css/miscontenedores.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
</head>
<body>
<header class="cabecera"><a class="subtitulo" href="catalogo.html">DAM: ACCESO A DATOS</a>
    <h5 class="titulo">Tienda Harnina: Cliente</h5>
</header>
<div class="contenido01">
    <div class="medio dadoCatalogo">
        <div class="dado d1 informatica"></div>
    </div>
    <div class="medio dadoCatalogo">
        <div class="dado d3 harnina"></div>
    </div>
</div>

<div class="contenido01">
    <img src= "../img/fotoClient/<%=session.getAttribute("nifCliente")%>.png?<%=Math.random()%>" alt="" height="200px" width="300px">
    <form enctype="multipart/form-data" id="client_register" method="POST" action="/UpCliAvaCon">
        <div class="menu s3 caja03">
            <h4>New Image</h4>
            <div class="fileinputs etiqueta s8" id="div_clientImage">
                <input class="etiqueta s8 file" id="clientImage" type="file" name="imagenCliente" data-functioncallback="ValidarFicheroName.validarImagenName" required accept="image/png, image/jpeg" placeholder="input your Avatar" title="3 to 120 characters">
                <div class="fakefile">
                    <label class="labelInput" for="idFile">Elije tu foto</label>
                    <input id="idFile" name="myFile">
                </div>
            </div>
            <div class="etiqueta errorColor" id="alertaError">Error:</div>
            <button id="submit" type="submit">Enviar</button>
        </div>
    </form>
</div>

</body>
</html>

<script src="../js/jscss/dado.js"></script>
<script src="../js/jsStore/store.js"></script>
<script src="../js/jsStore/prefijos.js"></script>
<script src="../js/jsStore/color.js"></script>
<script src="../js/jsStore/lista.js"></script>
<script src="../js/jsStore/file.js"></script>
<script src="../js/jsStore/error.js"></script>
<script src="../js/jsStore/submit.js"></script>
<script src="../js/jsStore/validate.js"></script>
<script src="../js/jsStore/clienteStrategyOne.js"></script>
