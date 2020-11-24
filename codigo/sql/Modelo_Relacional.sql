----------------------------
-- Table Sacolao
-- -----------------------------------------------------
CREATE TABLE Sacolao (
  id SERIAL,
  nome VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  senha VARCHAR(45) NULL,
  empresa VARCHAR(45) NULL,
  cnpj CHAR(14) NULL,
  PRIMARY KEY (cnpj));
 


-- -----------------------------------------------------
-- Table NotaFiscal
-- -----------------------------------------------------
CREATE TABLE NotaFiscal (
  numeroNF SERIAL,
  data DATE NULL,
  valor REAL NULL,
  Sacolao_cnpj CHAR(14) NOT NULL,
  PRIMARY KEY (numeroNF),
  CONSTRAINT fk_NotaFiscal_Sacolao1
    FOREIGN KEY (Sacolao_cnpj)
    REFERENCES Sacolao (cnpj)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Item_NF
-- -----------------------------------------------------
CREATE TABLE Item_NF (
  idItem SERIAL,
  descricao VARCHAR(45) NULL,
  quant_item INT NULL,
  PRIMARY KEY (idItem));



-- -----------------------------------------------------
-- Table Lote
-- -----------------------------------------------------
CREATE TABLE Lote (
  codigo SERIAL,
  data_Entrada DATE NULL,
  Valor REAL NULL,
  quant_estoque INT NULL,
  preco_unitario REAL NULL,
  categoria VARCHAR(20) NULL,
  descricao VARCHAR(45) NULL,
  data_validade DATE NULL,
  Item_NF_idItem INT NOT NULL,
  Sacolao_cnpj CHAR(14) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_Lote_Item_NF1
    FOREIGN KEY (Item_NF_idItem)
    REFERENCES Item_NF (idItem)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Lote_Sacolao1
    FOREIGN KEY (Sacolao_cnpj)
    REFERENCES Sacolao (cnpj)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Fornecedor
-- -----------------------------------------------------
CREATE TABLE Fornecedor (
  codigo SERIAL,
  nome VARCHAR(45) NULL,
  endereco VARCHAR(45) NULL,
  telefone BIGINT NULL,
  Lote_codigo INT NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_Fornecedor_Lote1
    FOREIGN KEY (Lote_codigo)
    REFERENCES Lote (codigo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Funcionario
-- -----------------------------------------------------
CREATE TABLE Funcionario (
  codigo SERIAL,
  salario REAL NULL,
  cargo VARCHAR(45) NULL,
  nome VARCHAR(45) NULL,
  PRIMARY KEY (codigo));



-- -----------------------------------------------------
-- Table Sacolao_has_Funcionario
-- -----------------------------------------------------
CREATE TABLE Sacolao_has_Funcionario (
  Sacolao_cnpj CHAR(14) NOT NULL,
  Funcionario_codigo INT NOT NULL,
  PRIMARY KEY (Sacolao_cnpj, Funcionario_codigo),
  CONSTRAINT fk_Sacolao_has_Funcionario_Sacolao1
    FOREIGN KEY (Sacolao_cnpj)
    REFERENCES Sacolao (cnpj)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Sacolao_has_Funcionario_Funcionario1
    FOREIGN KEY (Funcionario_codigo)
    REFERENCES Funcionario (codigo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table NotaFiscal_has_Item_NF
-- -----------------------------------------------------
CREATE TABLE NotaFiscal_has_Item_NF (
  NotaFiscal_numeroNF INT NOT NULL,
  Item_NF_idItem INT NOT NULL,
  PRIMARY KEY (NotaFiscal_numeroNF, Item_NF_idItem),
  CONSTRAINT fk_NotaFiscal_has_Item_NF_NotaFiscal1
    FOREIGN KEY (NotaFiscal_numeroNF)
    REFERENCES NotaFiscal (numeroNF)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_NotaFiscal_has_Item_NF_Item_NF1
    FOREIGN KEY (Item_NF_idItem)
    REFERENCES Item_NF (idItem)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
