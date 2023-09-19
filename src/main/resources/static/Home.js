$("#sortear").click(function() {
    realizaSorteio();
});

function realizaSorteio() {
    let minimo = parseInt($("#minimo").val());
    let maximo = parseInt($("#maximo").val());
    let quantidade = parseInt($("#quantidade").val());
    let ordenar = $("#ordenar").prop("checked");
    let repetir = $("#repetir").prop("checked");

   if (maximo < quantidade || minimo >= maximo) {
           if (maximo < quantidade) {
               $("#aviso").text("Não é possível realizar o sorteio com a quantidade de sorteios maior que o número máximo.");
           } else {
               $("#aviso").text("");
           }

           if (minimo >= maximo) {
               $("#aviso2").text("Não é possível realizar o sorteio com valor mínimo maior ou igual ao valor máximo.");
           } else {
               $("#aviso2").text("");
           }

           return;
       } else {
           $("#aviso").text("");
           $("#aviso2").text("");
       }
    $.ajax({
        type: "POST",
        url: "/sortear",
        data: {
            minimo: minimo,
            maximo: maximo,
            quantidade: quantidade,
            ordenar: ordenar,
            repetir: repetir
        },
        success: function (data) {
            $("#resultado").text("");
            for (let i = 0; i < data.length; i++) {
                $("#resultado").append("<h1>" + data[i] + "</h1>" + "<br>");
            }
            $("#dataSorteio").html(new Date().toLocaleDateString() + ' ' + new Date().toLocaleTimeString());

            $("#quantidadeSorteios").text(data.length);
            $("#min").text(minimo);
            $("#max").text(maximo);
        },
        error: function () {
            $("#resultado").text("Ops! Ocorreu um erro inesperado.");
        }
    });
}

$("#novosorteio").click(function() {

    $("#minimo").val("");
    $("#maximo").val("");
    $("#aviso").text("");
    $("#quantidade").val("");
    $("#ordenar").prop("checked", false);
    $("#repetir").prop("checked", false);


    $("#resultado").text("");
    $("#dataSorteio").html("");
    $("#quantidadeSorteios").text("");
    $("#min").text("");
    $("#max").text("");

    const wrapper = document.querySelector(".wrapper");
    wrapper.appendChild(resultadosContainer);
});
