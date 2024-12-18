import CadastroCliente from "pages/CadastroCliente";
import CadastroAnuncio from "pages/CadastroAnuncio";
import Dashboard from "pages/Dashboard";
import Home from "pages/Home";
import { BrowserRouter, Route, Switch } from "react-router-dom";

const Routes = () => {

  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact>
          <Home />
        </Route>
        <Route path="/CadastroCliente">
          <CadastroCliente />
        </Route>
        <Route path="/CadastroAnuncio">
          <CadastroAnuncio />
        </Route>
        <Route path="/Dashboard">
          <Dashboard />
        </Route>
      </Switch>
    </BrowserRouter>
  );
};

export default Routes;
