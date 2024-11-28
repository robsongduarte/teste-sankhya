import {gerarCliente} from "assets/utils/clienteUtil"

const formu = () => {
  return (
    <>
      <form action = "/" onSubmit={gerarCliente}>
        <div className="mb-3 col-6">
          <label htmlFor="exampleInputName" className="form-label">
            Nome ou Nome Fantasia
          </label>
          <input
            type="text"
            className="form-control"
            id="name"
            aria-describedby="nameHelp"
          />
          <div id="nameHelp" className="form-text">
            informe um nome para o cliente
          </div>
        </div>

        <div className="mb-3 col-6">
          <label htmlFor="exampleInputEmail1" className="form-label">
            Endereço de Email
          </label>
          <input
            type="email"
            className="form-control"
            id="email"
            aria-describedby="emailHelp"
          />
          <div id="emailHelp" className="form-text">
            informe um endereço de email válido
          </div>
        </div>

        <div className="mb-3 col-6">
          <label htmlFor="tipoCliente" className="form-label">
            Tipo de cliente
          </label>
          <select className="form-select" id="tipo" required>
            <option value="1">Pessoa Fisica</option>
            <option value="2">Pessoa jurídica</option>
          </select>
          <div className="invalid-feedback">
            Por favor selecione uma opção válida
          </div>
        </div>

        <div className="mb-3 col-6">
          <label htmlFor="exampleInputCpfouCnpj" className="form-label">
            CPF ou CNPJ
          </label>
          <input type="text" className="form-control" id="CpfouCnpj" />
          <div id="emailHelp" className="form-text">
            informe o CPF ou CNPJ
          </div>
        </div>

        <div className="mb-3">
          <button type="submit" className="btn btn-primary">
            Enviar
          </button>
        </div>
      </form>
    </>
  );
};

export default formu;
