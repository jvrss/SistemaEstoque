$(document).ready(function () {
    list();
});

var contentAdd = '<h2>Adicionar Item</h2> <div class="form-group"> <label for="nome">Nome Item</label> <input type="text" class="form-control" id="nome" placeholder="Digite o nome do item"> </div><div class="form-group"><label for="descricao">Descrição</label><textarea class="form-control" id="descricao" rows="5"></textarea></div><div class="form-group"> <label for="quantidade">Quantidade</label> <input type="text" class="form-control" id="quantidade" placeholder="Digite a quantidade do item"> </div><div class="form-group"> <div class="row"> <div class="col-6 text-center"> <button onclick="addItem();" type="button" class="btn btn-primary">Adicionar</button> </div><div onclick="toDashboard();" class="col-6 text-center"> <button type="button" class="btn btn-primary">Cancelar</button> </div></div></div>';

function contentEdit(id) {

    var contentEdit = '<h2>Editar Item</h2><div class="form-group"> <label for="nome">Nome Item</label> <input type="text" class="form-control" id="nome" placeholder="Digite o nome do item"> </div><div class="form-group"><label for="descricao">Descrição</label><textarea class="form-control" id="descricao" rows="5"></textarea></div><div class="form-group"> <label for="quantidade">Quantidade</label> <input type="text" class="form-control" id="quantidade" placeholder="Digite a quantidade do item"> </div><div class="form-group"> <div class="row"> <div class="col-6 text-center"> <button onclick="modifyItem(' + id + ');" type="button" class="btn btn-primary">Alterar</button> </div><div onclick="toDashboard();" class="col-6 text-center"> <button type="button" class="btn btn-primary">Cancelar</button> </div></div></div>';

    return contentEdit;

}

function toDashboard() {

    window.location.href = "/JavaWeb/AuthenticateController";
}

function toForm() {

    $("#menu-dash").removeClass("active");
    $("#menu-add").addClass("active");

    var main = document.getElementById("main");
    main.innerHTML = contentAdd;
}

function list() {

    $.ajax({
        method: "POST",
        dataType: 'text',
        url: "/JavaWeb/ItemController",
        data: {operation: "list"},
        success: function (msg) {
            $("#list").html(msg);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }});

}

function addItem() {

    var nome = $("#nome").val();
    var descricao = $("#descricao").val();
    var quantidade = $("#quantidade").val();

    $.ajax({
        method: "POST",
        url: "/JavaWeb/ItemController",
        data: {operation: "add",
            nome: nome,
            descricao: descricao,
            quantidade: quantidade},
        success: function (msg) {
            toDashboard();
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }});
}

function editItem(id) {

    $("#menu-dash").removeClass("active");

    var main = document.getElementById("main");
    main.innerHTML = contentEdit(id);

    $.ajax({
        method: "POST",
        url: "/JavaWeb/ItemController",
        data: {operation: "get",
            id: id},
        success: function (obj) {
            var item = JSON.parse(obj);

            $("#nome").val(item.name);
            $("#descricao").val(item.description);
            $("#quantidade").val(item.amount);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }});

}

function modifyItem(id) {

    var nome = $("#nome").val();
    var descricao = $("#descricao").val();
    var quantidade = $("#quantidade").val();

    $.ajax({
        method: "POST",
        url: "/JavaWeb/ItemController",
        data: {operation: "set",
            nome: nome,
            descricao: descricao,
            quantidade: quantidade,
            id: id},
        success: function (msg) {
            toDashboard();
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }});

}

