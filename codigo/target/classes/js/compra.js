var CNPJdaEmpresa = JSON.parse(localStorage.getItem('dados')).CNPJ;

window.onload = () => {
    if(!hasCompras()){
        let value = {
            "compras": []
        };
        localStorage.setItem("compras", JSON.stringify(value));
    }
    isCompraUndefined();
    mostrarSalvos();

    document.querySelector("#btnRegistrar").addEventListener("click", (e) => {
        e.preventDefault();
        salvarCompra();
    });
}

function mostrarSalvos() {
    let tabela = document.querySelector("#corpoTabela");
    let compras = JSON.parse(localStorage.getItem("compras")).compras;
    let html = "";

    if (hasCompras() && !compraIsEmpty()) {
        console.log("A");
        for (i = 0; i < compras.length; i++) {
            dataAq = formatDate(compras[i].data);
            validade = formatDate(compras[i].validade);
            console.log("Data ",compras[i].data);
            console.log("Validade ",compras[i].validade);
            html += `
                <tr id="${compras[i].id}-linha">
                    <td>${compras[i].cod}</td>
                    <td>${compras[i].descricao}</td>
                    <td>${compras[i].fornecedor}</td>
                    <td>${compras[i].categoria}</td>
                    <td>${compras[i].quantidade}</td>
                    <td>${compras[i].valor}</td>
                    <td>${dataAq}</td>
                    <td>${validade}</td>
                    <th><i class="fas fa-check text-success " id="${compras[i].id}-check" data-toggle="modal" data-target="#modalConfirmarCompra"></i></th>
                    <th><i class="fas fa-times text-danger" id="${compras[i].id}-close"></i></th>
                </tr>
                `;
            console.log(compras[i]);
        }

        tabela.innerHTML = html;

        for (i = 0; i < compras.length; i++) {
            let valor = compras[i].id;
            let checkV = `${valor}-check`;
            let closeV = `${valor}-close`;
            document.getElementById(checkV).addEventListener("click", function () {
                abrirModalConfirmar(valor);
            });
            document.getElementById(closeV).addEventListener("click", function () {
                deletarCompra(valor);
            });
        }
    } else {
        html = `
            <tr>
                <td colspan="9">
                    <h3>Não há compras sendo processadas.</h3>
                </td>
            </tr>
            `;
        tabela.innerHTML = html;
    }

}

function formatDate(data){
    var date = new Date(data),
        dia = date.getDate().toString().padStart(2, "0"),
        mes = (date.getMonth()+1).toString().padStart(2, "0"),
        ano = date.getFullYear();
    return `${dia}/${mes}/${ano}`;
}

function salvarCompra() {
    if (hasCompras()) {
        if (isAllFilled()) {
            //let query = document.querySelector("#codigo").value;
            let compras = leCompras();
            compras = addCompra(compras);
            localStorage.setItem("compras", JSON.stringify(compras));
            mostrarSalvos();
        } else {
            alert("Preencha todas os campos.");
        }
    } else {
        let value = {
            "compras": []
        };
        localStorage.setItem("compras", JSON.stringify(value));
        
    }
}

function addCompra(compras) {
    let descricao = document.querySelector("#descricao").value;
    let codigo = document.querySelector("#codigo").value;
    let categoria = document.querySelector("#categoria").value;
    let quantidade = document.querySelector("#quantidade").value;
    let valor = document.querySelector("#valor").value;
    let data = document.querySelector("#data").value;
    let fornecedor = document.querySelector("#fornecedores").value;
    let validade = document.querySelector("#validade").value;
    let novo = {
        id: generateUUID(),
        cod: codigo, //TEMPORARIO
        descricao: descricao,
        categoria: categoria,
        quantidade: quantidade,
        valor: valor,
        data: data,
        fornecedor: fornecedor,
        validade
    };

    compras.compras.push(novo);
    return compras;
}

function deletarCompra(id){
    let elem = document.getElementById(`${id}-linha`);
    elem.parentNode.removeChild(elem);
    let obj = JSON.parse(localStorage.getItem("compras"));
    let index = obj.compras.indexOf(id);
   
    obj.compras.splice(index, 1);
    
    localStorage.setItem("compras",JSON.stringify(obj));
}

function abrirModalConfirmar(id){
    let btnModalConfirmar = document.querySelector("#modalConfirmar");

    btnModalConfirmar.innerHTML = `<button type="button" class="btn btn-success" id="${id}-modalBtnConfirmar">Confirmar</button>`;

    document.getElementById(`${id}-modalBtnConfirmar`).addEventListener("click",function () {
        confirmarCompra(id);
    })
}

function confirmarCompra(id){

    if(localStorage.getItem(`${CNPJdaEmpresa}`) === null){
        dados = {
            produtos: [
            ]
          };
        localStorage.setItem(`${CNPJdaEmpresa}`, JSON.stringify(dados));
    }
    if(isModalFilled()){
        cadastrarProdutoComprado(id);
        $("#modalConfirmarCompra").modal("hide");
    }
    else
        alert("Preencha os dados!");
}

function hasCompras() {
    has = true;
    obj = JSON.parse(localStorage.getItem("compras"));
    //
    if (localStorage.getItem("compras") === null) {
        has = false;
    }
    return has;
}

function isCompraUndefined(){
    obj = JSON.parse(localStorage.getItem("compras"));
    if(obj.compras === undefined){
        let value = {
            "compras": []
        };
        localStorage.setItem("compras", JSON.stringify(value));
    }
}

function compraIsEmpty() {
    has = false;
    obj = JSON.parse(localStorage.getItem("compras"));
    isCompraUndefined();
    if (obj.compras.length == 0) {
        has = true;
    }
    return has;
}

function leCompras() {
    let compras = JSON.parse(localStorage.getItem("compras"));
    return compras;
}

// checa se todos os campos foram preenchidos
function isAllFilled() {
    if (isEmpty('descricao') || isEmpty('codigo') || isEmpty('categoria') || isEmpty('quantidade') || isEmpty(
            'valor') || isEmpty('data') || isEmpty('fornecedores')) {
        return false;
    } else {
        return true;
    }
}

// checa se o campo está vazio
function isEmpty(item) {
    if (document.getElementById(item).value == undefined || document.getElementById(item).value == "" ||
        document.getElementById(item).length == 0) {
        return true;
    } else {
        return false;
    }
}

function isModalFilled(){
    if(isEmpty("preco") || isEmpty("armazem") || isEmpty("estante") || isEmpty("prateleira") || isEmpty("posicao"))
        return false;
    else
        return true;
}

// função para gerar códigos randômicos a serem utilizados como código de usuário
// Fonte: https://stackoverflow.com/questions/105034/how-to-create-guid-uuid
function generateUUID() { // Public Domain/MIT
    var d = new Date().getTime();//Timestamp
    var d2 = (performance && performance.now && (performance.now() * 1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16;//random number between 0 and 16
        if (d > 0) {//Use timestamp until depleted
            r = (d + r) % 16 | 0;
            d = Math.floor(d / 16);
        } else {//Use microseconds since page-load if supported
            r = (d2 + r) % 16 | 0;
            d2 = Math.floor(d2 / 16);
        }
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}

function encontrarObj(obj, name, value){
    for(i = 0;i < obj.length; i++)
    {
        if(obj[i][name] == value)
            return obj[i];
    }
}

function cadastrarProdutoComprado(id) {
    // Ler os dados do localStorage
    let objDados = leDados();
    let objDadosCompras = JSON.parse(localStorage.getItem("compras")).compras;

    let objCompra = encontrarObj(objDadosCompras, "id", id);

    // Incluir um produto comprado
    let strDescricao = objCompra.descricao;
    let strCodigo = objCompra.cod;
    let strCategoria = objCompra.categoria;
    let strQuantidade = objCompra.quantidade;
    let strValor = document.getElementById('preco').value;
    let strArmazem = document.getElementById('armazem').value;
    let strEstante = document.getElementById('estante').value;
    let strPrateleira = document.getElementById('prateleira').value;
    let strPosicao = document.getElementById('posicao').value;
    let valorInicial = strQuantidade;
    let isComprado = true;
    let dataAquisicao = objCompra.data;
    let fornecedor = objCompra.fornecedor;
    let valorAquisicao = objCompra.valor;
    let validade = objCompra.validade;
    let novoItem = {
        id: generateUUID(),
        descricao: strDescricao, 
        codigo: strCodigo, 
        categoria: strCategoria, 
        quantidade: strQuantidade,
        valor: strValor, 
        armazem: strArmazem, 
        estante: strEstante, 
        prateleira: strPrateleira, 
        posicao: strPosicao,
        valorInicial,
        isComprado,
        dataAquisicao,
        validade,
        fornecedor,
        valorAquisicao
    };
    objDados.produtos.push(novoItem);

    // Salvar os dados no localStorage novamente
    salvaDados(objDados);

    deletarCompra(id);

    alert("Compra cadastrado com sucesso!");
}

function leDados() {
    let strDados = localStorage.getItem(`${CNPJdaEmpresa}`);
    let objDados = {};
  
    if (strDados) {
      objDados = JSON.parse(strDados);
    }
  
    else {
      objDados = {
        produtos: [
         
        ]
      }
    }
  
    return objDados;
}

function salvaDados(dados) {
    localStorage.setItem(`${CNPJdaEmpresa}`, JSON.stringify(dados));
  }