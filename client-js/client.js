function consultarRestaurantes() {
    $.ajax({
        url: "http://localhost:8080/restaurantes",
        type: "get",

        success: function (response) {
            $("#conteudo").text(JSON.stringify(response));
        }
    });
}

function fecharRestaurante() {
    $.ajax({
        url: "http://localhost:8080/restaurantes/1/fechamento",
        type: "put",

        success: function () {
            alert('Restaurante fechado com sucesso')
        }
    });
}

function consultarFormasPagamento() {
    
    $.ajax({
        url: "http://localhost:8080/formas-pagamento",
        type: "get",

        success: function(response){
            preencherTabela(response);
        }
        
    });
}

function cadastrarFormaPagamento() {
    var formaPagamentoJson = JSON.stringify({
        "descricao": $("#campo-descricao").val()
    });
      
    $.ajax({
        url: "http://localhost:8080/formas-pagamento",
        type: "post",
        data: formaPagamentoJson,
        contentType: "application/json",

        success: function (response) {
            alert("Forma pagamento adicionadas!");
            consultarFormasPagamento();
        },
        error: function (error) {
            if (error.status == 400) {
                var problema = error.responseJSON;
                alert(problema.mensagemUsuario);
            } else {
                alert("Erro ao cadastrar forma de pagamento!");
            }
        }
        
    });
    
}

function exluirFormaPagamento(formaPagamento) {
    $.ajax({
        url: `http://localhost:8080/formas-pagamento/${formaPagamento.id}`,
        type: "delete",
        success: function(response) {
            alert("Forma de pagamento removida!");
            consultarFormasPagamento();
        },
        error: function(error) {
            if (error.status >= 400 && error.status <= 499) {
                var problema = error.responseJSON;
                alert(problema.mensagemUsuario);
            } else {
                alert("Erro ao cadastrar forma de pagamento!");
            }
        }
    });
}

function preencherTabela(formasPagamento) {
    $("#tabela tbody tr").remove();

    $.each(formasPagamento, function (i, formaPagamento) {
        var linha = $("<tr>");
        var linkAcao = $("<a href='#'>")
                        .text("Excluir")
                        .click(function(event){
                            event.preventDefault();
                            exluirFormaPagamento(formaPagamento);
                        });

        linha.append (
            $("<td>").text(formaPagamento.id),
            $("<td>").text(formaPagamento.descricao),
            $("<td>").append(linkAcao)
        );

        linha.appendTo("#tabela");
    });
}




$("#btn-consultar").click(consultarFormasPagamento);
$("#btn-cadastrar").click(cadastrarFormaPagamento);