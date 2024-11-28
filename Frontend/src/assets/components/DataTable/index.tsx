import { BASE_URL } from "assets/utils/requests";
import { idcliente } from "assets/utils/requests";
import { datainicio } from "assets/utils/requests";
import { datafim } from "assets/utils/requests";
import axios from "axios";
import {useContext, useEffect, useState} from "react";
import { AnuncioPage } from "types/anuncio";
import Pagination from "../Pagination";
import {MyContext} from "../../../contexts/myContext";

export default function DataTable() {
  const [activePage, setActivePage] = useState(0);
  const [page, setPage] = useState<AnuncioPage>({
    first: true,
    last: true,
    number: 0,
    totalElements: 0,
    totalPages: 0,
  });
  const usuario = useContext(MyContext);

  useEffect(() => {
    axios
      .get(`${BASE_URL}/anuncios?page=${activePage}&idcliente=${idcliente}&datainicio=${datainicio}&datafim=${datafim}`)
      .then((response) => {
        setPage(response.data);
      });
  }, [activePage]);

  const changePage = (index: number) => {
    setActivePage(index);
  };

  return (
    <>
      <Pagination page={page} onPageChange={changePage} />

      <div className="table-responsive">
        <table className="table table-striped table-sm">
          <thead>
            <tr>
              <th>Nome anuncio</th>
              <th>Total investido</th>
              <th>Q. Máxima de visualizações</th>
              <th>Q. Máxima de cliques</th>
              <th>Q. Máxima de Compartilhamentos</th>
            </tr>
          </thead>
          <tbody>
            {page.content?.map((item) => (
              <tr key={item.id}>
                <td>{item.nome}</td>
                <td>{item.valorTotalInvetido}</td>                
                <td>{item.qtdMaxVisualizacao}</td>                
                <td>{item.qtdMaxCliques}</td>                
                <td>{item.qtdMaxCompartilhamentos}</td>                
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};
