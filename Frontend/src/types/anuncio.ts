import { cliente } from "./cliente"

export type Anuncio = {
  id: number;
  nome:string;
  dataInicio: string;
  dataTermino: string;
  investimentoDia: number;
  cliente: cliente;
}

export type AnuncioDTO = {
  id: number;
  nome:string;
  valorTotalInvetido: number;
  qtdMaxVisualizacao: number;
  qtdMaxCliques: number;
  qtdMaxCompartilhamentos: number;
}

export type AnuncioPage = {
  content?: AnuncioDTO[];
  last: boolean;
  totalElements: number;
  totalPages: number;
  size?: number;
  number: number;
  first: boolean;
  numberOfElements?: number;
  empty?: boolean;
}