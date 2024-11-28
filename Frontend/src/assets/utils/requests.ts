
export const BASE_URL = 'http://localhost:8080';
export var idcliente= '0';
export var datainicio='';
export var datafim='';

export function setIdcliente(valor: string){
    idcliente = valor
    console.log(idcliente)
}
export function setdatainicio(valor: string){
    datainicio = valor
}
export function setdatafim(valor: string){
    datafim = valor
}