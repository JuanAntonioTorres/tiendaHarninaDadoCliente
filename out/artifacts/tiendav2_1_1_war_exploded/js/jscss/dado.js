var imagenUsuario;
var nif;
if(nif===undefined){
  imagenUsuario = '../img/fotocliente/fotoSin.jpg';
}
else{
    imagenUsuario = 'img/fotoClient/'+ nif + '.png';
}

var dados = ['dado informatica','dado harnina','dado usuario'];

var imagenes = ['../img/escudo02.png','../img/harni01.png', imagenUsuario];
var clases = ['cara frontal','cara trasera','cara derecha','cara izquierda','cara arriba','cara abajo'];

var creaImagen = function(imag){
  var imagen = document.createElement("img");
  imagen.setAttribute('src', imag);
  imagen.className = 'Imagen';
  return imagen;
}

var creaCara = function(dado, n, imagen){
  for (var i = 0; i < clases.length; i++){
    var cara = document.createElement('div');
    cara.className = clases[i];
    cara.appendChild(creaImagen(imagen));
    dado[n].appendChild(cara);
  }
}
for (var i = 0; i <= dados.length; i++) {
  var dado = document.getElementsByClassName(dados[i]);
  for (var j = 0; j < dado.length; j++){
    creaCara(dado, j, imagenes[i]);
  }
}
