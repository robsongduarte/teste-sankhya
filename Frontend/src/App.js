import Routes from 'Routes';
import { MyContext } from "./contexts/myContext";
import {useState} from "react";

function App() {
  const [text, setText] = useState('teste');
  return (
      <div>
          <MyContext.Provider value={{text, setText}}>
              <Routes />
          </MyContext.Provider>
      </div>
  );
}

export default App;
