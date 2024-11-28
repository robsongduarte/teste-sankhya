export type cliente = {
    id: number;
    nome: string;
    email: string;
    cpfOuCnpj: string;
    tipo: string;
}

export type clientePage = {
    content?: cliente[];
    last: boolean;
    totalElements: number;
    totalPages: number;
    size?: number;
    number: number;
    first: boolean;
    numberOfElements?: number;
    empty?: boolean;
  }