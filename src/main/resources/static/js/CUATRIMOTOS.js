traerInformacion();

function traerInformacion(){
    $.ajax({
        url:"http://132.226.63.45:8080/api/Quadbike/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            pintarRespuesta(respuesta);
            traerCategory();
        }
    })
}


function pintarRespuesta(items){
    let myTable="<table>";
        myTable+="<tr>";
      
        myTable+="<td>"+"NOMBRE"+"</td>";
        myTable+="<td>"+"MARCA"+"</td>";
        myTable+="<td>"+"AÑO"+"</td>";
        myTable+="<td>"+"DESCRIPCION"+"</td>";
        myTable+="<td>"+"ACCIONES"+"</td>";
        myTable+="</tr>";
    for(i=0;i<items.length;i++){
        myTable+="<tr>";
        myTable+="<td>"+items[i].name+"</td>";
        myTable+="<td>"+items[i].brand+"</td>";
        myTable+="<td>"+items[i].year+"</td>";
        myTable+="<td>"+items[i].description+"</td>";

        myTable+="<td> <button onclick='borrarElemento("+items[i].id+")'>Eliminar</button></td>";
        myTable+="<td> <button onclick='obtenerItemEspecifico("+items[i].id+")'>cargar</button></td>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").append(myTable);
}    


function guardarInformacion(){
    let myData={
        id:$("#id").val(),
        name:$("#name").val(),
        brand:$("#brand").val(),
        year:$("#year").val(),
        description:$("#description").val(),
        category:{"id":$("#category").val()},
        
    };
    let dataToSend=JSON.stringify(myData);
   if (validar()){
    $.ajax({
        url:"http://132.226.63.45:8080/api/Quadbike/save",
        type:"POST",
        data:dataToSend,
        datatype:"JSON",
        contentType:'application/json',
        success:function(){
            limpiarCampos();
            traerInformacion();
            alert("REGISTRO CREADO!")
        }
    });
   
}}


function editarInformacion(){
    let myData={
        id:$("#id").val(),
        name:$("#name").val(),
        brand:$("#brand").val(),
        year:$("#year").val(),
        description:$("#description").val(),
           
    };
    //console.log(myData);
    let dataToSend=JSON.stringify(myData);
    if (validar()){
    $.ajax({
        url:"http://132.226.63.45:8080/api/Quadbike/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            limpiarCampos();
            traerInformacion();
            alert("REGISTRO ACTUALIZADO!")
        }
    });
    }
}


function borrarElemento(idElemento){
    let myData={
        id:idElemento
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://132.226.63.45:8080/api/Quadbike/"+idElemento,
        type:"DELETE",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            limpiarCampos();
            traerInformacion();
            alert("REGISTRO BORRADO!")
        }
    });
}

function limpiarCampos(){
    $("#resultado").empty();
    $("#id").val("")
    $("#name").val("");
    $("#brand").val("");
    $("#year").val("");
    $("#description").val("");
    $("#category").val("");

}


function validaesVacio(dato){
    return !dato.trim().length;
}


function validar(){
    //obtiene valores
    let name = $("#name").val();
    let brand = $("#brand").val();
    let year = $("#year").val();
    let description = $("#description").val();
    let category= $("#category").val();
  

    //valida que los campos no sean vacios
    
    if( validaesVacio(name)) { 
        errores="name vacio<br>";
        alert("Espacio vacio");
        $("#name").focus();
        return false;
    }else if( validaesVacio(brand)) {
        errores="brand vacio<br>";
        alert("Espacio vacio");
        $("#brand").focus();
        return false;
    }else if( validaesVacio(year)) {  
        errores="year vacio<br>";
        alert("Espacio vacio");
        $("#year").focus();
        return false;
    }else if( validaesVacio(description)) {
        errores="description vacio<br>";
        alert("Espacio vacio");
        $("#description").focus();
        return false;
    }else if( validaesVacio(category)) { 
        errores="category vacio<br>";
        alert("Espacio vacio");
        $("#category").focus();
        return false;
    }else{
        return true;
    }
}


function obtenerItemEspecifico(idItem){
    $.ajax({
        dataType: 'json',
        url:"http://132.226.63.45:8080/api/Quadbike/"+idItem,
        type:'GET',
        success:function(response) {
          console.log(response);
          var item=response;
  
          $("#id").val(item.id);
          $("#name").val(item.name);
          $("#brand").val(item.brand);
          $("#year").val(item.year);
          $("#description").val(item.description);
          $("#category").val(item.category);
         
        },
  
        error: function(jqXHR, textStatus, errorThrown) {
  
        }
    });
  
  }


  function traerCategory(){
    $.ajax({
        url:"http://132.226.63.45:8080/api/Category/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            //console.log(respuesta);
            listarCategory(respuesta);
        }
    })
}
  
function listarCategory(items){
    var lista='<option value="">--Selecciona una Categoria--</option>';
        
    for(i=0;i<items.length;i++){
      
    lista+="<option value="+items[i].id+">"+items[i].name+"</option>";
    }
   
    $("#category").html(lista);
}   

