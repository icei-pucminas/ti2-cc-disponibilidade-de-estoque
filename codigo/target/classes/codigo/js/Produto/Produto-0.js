"use strict";

// // todo: método estático armazenar objetos da classe em uma array;


class Produto {
  static valorEmEstoque; // valor do estoque inteiro
  static arr_ProdutosEmEstoque; // array com os produtos em estoque
  
  constructor(desc,qtd,vUn,cod,ctg,val,loc) {
    this._desc = desc; // descrição do produto
    this._qtd = this.set_qtd(qtd); //qtd em estoque
    this._vUn = this.set_vUn(vUn); //valor unitário
    this._vLot = this.set_vLot(); //valor do lote
    this._id = this.set_id(); //id ou número de lote, gerado automaticamente
    this._cod = cod; //código a critério da empresa
    this._ctg = ctg; //categoria
    this._val = val; // validade
    this._loc = loc; //localização no estoque... sugestão "EI-20-A-4" (estoque interno, estante, prateleira, posição)
    this._dataRg = new Date(); //data de registro do produto
    Produto.arr_ProdutosEmEstoque.push(this); // coloca objeto dentro da lista de estoque
    Produto.get_valorEmEstoque(); // recalcula valor total do estoque
  }


  destroy() {  // Remover produto da lista do estoque
    let listaDoEstoque = Produto.arr_ProdutosEmEstoque; // coloca lista de estoque na variável
    listaDoEstoque.splice(listaDoEstoque.indexOf(this),1); // remove o produto da lista do estoque
    
    this._desc = '(FORA DE ESTOQUE) ' + this._desc; // adiciona a observação na descrição do produto
    this._loc = '(FORA DE ESTOQUE) ' + this._loc;
    this._dataRg = Date(); //data de modificação do produto

    Produto.get_valorEmEstoque(); // recalcula valor total do estoque
  }


  // GETTERS N SETTERS //
  
  // DESCRIÇÃO //
  get_desc() {
    return this._desc;
  }
  set_desc(desc) {
    this._desc = desc; return desc;
  }

  // QUANTIDADE //
  get_qtd() {
    return this._qtd;
  }
  set_qtd(qtd) {
    let valido = qtd >= 0;
    if(!valido) {
      console.log("Valor inválido");
      qtd = 0;
    }
    this._qtd = qtd;
    this.set_vLot(); // recalcula valor do lote
    Produto.get_valorEmEstoque(); // recalcula valor total do estoque
    return qtd;
  }

  // VALOR UNITÁRIO
  get_vUn() {
    return this._vUn;
  }
  set_vUn(valor) {
    let valido = valor >= 0;
    if(valido) {
      this._vUn = valor;
    }
    else {
      console.log("Valor inválido");
      this._vUn = 0;
      valor = 0;
    }
    this.set_vLot(); // recalcula valor do lote
    Produto.get_valorEmEstoque(); // recalcula valor do estoque
    return valor;
  }

  // VALOR DO LOTE //
  get_vLot() {
    return this._qtd * this._vUn;
  }
  set_vLot() {
    this._vLot = this._qtd * this._vUn;
    return this._vLot;
  }

  // ID (NÚMERO DO LOTE) //
  get_id() {
    return this._id;
  }
  set_id() {
    let performance;
    var d = new Date().getTime();//Timestamp
    var d2 = (performance && performance.now && (performance.now() * 1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
    return 'yxxx-yxxx-yxxx-yxxx'.replace(/[xy]/g, function (c) {
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

  // CÓDIGO //
  get_cod() {
    return this._cod;
  }
  set_cod(cod) {
    this.cod = cod; return cod;
  }

  // CATEGORIA //
  get_ctg() {
    return this._ctg;
  }
  set_ctg(ctg) {
    this._ctg = ctg; return ctg;
  }

  //  VALIDADE //
  get_val() {
    return this._val;
  }
  // todo: rejeitar valores inválidos
  set_val(val) { 
    this.val = val;
  }

  // LOCALIZAÇÃO //
  get_loc() {
    return this._loc;
  }
  set_loc(loc) {
    this._loc = loc; return loc;
  }
  
  static get_valorEmEstoque() {
    let valor = 0;
    for(let i = 0 ; i<Produto.arr_ProdutosEmEstoque.length ; i++) {
      valor += Produto.arr_ProdutosEmEstoque[i].get_vLot();
    }
    Produto.valorEmEstoque = valor;
    return valor;
  }

  // Pesquisa - retorna booleano // 
  pesquisa(pesq) { // pesq: chave de pesquisa
    // se a chave de pesquisa não estiver em branco e estiver contida na descrição do objeto
    let achado = pesq != '' && this._desc.includes(pesq);
    return achado;
  }

}
Produto.valorEmEstoque = 0;
Produto.arr_ProdutosEmEstoque = [];



//***********
// TESTES 
//***********

let p1 = new Produto("maçã",100,-1);
console.log(Produto.valorEmEstoque);

p1.destroy();
p1 = new Produto("maçã",100,5);
console.log('p1 recriado: ' + Produto.valorEmEstoque);


let p2 = new Produto("banana",200,0.50);

console.log('valor em estoque: ' + Produto.get_valorEmEstoque());
console.log('array: '); console.log(Produto.arr_ProdutosEmEstoque);