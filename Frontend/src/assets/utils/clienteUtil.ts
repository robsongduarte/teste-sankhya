import axios from "axios";
import { BASE_URL } from "assets/utils/requests";

export const gerarCliente = () => {

    var nomeField = (document.getElementById("name") as HTMLInputElement).value;
    var emailField = (document.getElementById("email") as HTMLInputElement).value;
    var cpfOuCnpjField = (document.getElementById("CpfouCnpj") as HTMLInputElement).value;
    var tipoField = (document.getElementById("tipo") as HTMLInputElement).value;

    axios
        .post(`${BASE_URL}/clientes`, {
            nome: `${nomeField}`,
            email: `${emailField}`,
            cpfOuCnpj: `${cpfOuCnpjField}`,
            tipo: `${tipoField}`,
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

