import axios from "axios";
import { BASE_URL } from "assets/utils/requests";
import { useEffect, useState } from "react";
import { clientePage } from "types/cliente";

const Formu = () => {
  const gerarAnuncio = () => {
    var nomeField = (document.getElementById("name") as HTMLInputElement).value;
    var dataInicioField = (
      document.getElementById(" dataInicio") as HTMLInputElement
    ).value;
    var dataTerminoField = (
      document.getElementById("dataTermino") as HTMLInputElement
    ).value;
    var investimentoDiaField = (
      document.getElementById("investimentoDia") as HTMLInputElement
    ).value.replace(",", ".");
    var clienteField = (document.getElementById("cliente") as HTMLInputElement)
      .value;

    axios
      .post(`${BASE_URL}/anuncios`, {
        nome: `${nomeField}`,
        dataInicio: `${dataInicioField}`,
        dataTermino: `${dataTerminoField}`,
        investimentoDia: `${investimentoDiaField}`,
        cliente: { id: `${clienteField}` },
      })
      .then(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
  };

  const [activePage] = useState(0);
  const [page, setPage] = useState<clientePage>({
    first: true,
    last: true,
    number: 0,
    totalElements: 0,
    totalPages: 0,
  });

  useEffect(() => {
    axios
      .get(`${BASE_URL}/clientes?page=${activePage}&size=20`)
      .then((response) => {
        setPage(response.data);
      });
  }, [activePage]);

  return (
    <>
      <form action = "/" onSubmit={gerarAnuncio}>
        <div className="mb-3 col-6">
          <label htmlFor="exampleInputNameAnuncio" className="form-label">
            Nome do anuncio
          </label>
          <input
            className="form-control"
            id="name"
            aria-describedby="nameHelp"
          />
          <div id="nameHelp" className="form-text">
            informe um nome para o anuncio
          </div>
        </div>

        <div className="col-6 form-group mb-2">
          <label className="form-check-label" htmlFor="dt_in">
            <b>Data Inicial </b>
          </label>
          <div className="col-10">
            <input className="form-control" type="date" id=" dataInicio" />
          </div>
        </div>

        <div className="col-6 form-group mb-2">
          <label className="form-check-label" htmlFor="dt_in">
            <b>Data Termino: </b>
          </label>
          <div className="col-10">
            <input className="form-control" type="date" id="dataTermino" />
          </div>
        </div>

        <div className="col-6 mb-3">
          <label htmlFor="exampleInputInvestimento" className="form-label">
            Investimento diário
          </label>
          <input
            type="number"
            step="0.01"
            min="0"
            lang="en"
            className="form-control"
            id="investimentoDia"
          />
          <div id="InvestimentoHelp" className="form-text">
            informe um valor em reais, se necessário use , para separar os
            centavos
          </div>
        </div>

        <div className="mb-3 col-6">
          <label htmlFor="tipoCliente" className="form-label">
            Cliente
          </label>
          <select className="form-select" id="cliente" required>
            <option value="">Secione uma opção</option>
            {page.content?.map((item) => (
              <option value={item.id}>{item.nome}</option>
            ))}
          </select>
          <div className="invalid-feedback">
            Por favor selecione uma opção válida
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

export default Formu;
