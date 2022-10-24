import Footer from "./components/Footer";
import Header from "./components/Header";
import Home from "./pages/home/Home";
import Destino from "./pages/destino/Destino"
import Promocoes from "./pages/promocoes/Promocoes"
import Contato from "./pages/contato/Contato"
import {BrowserRouter,Routes,Route} from "react-router-dom"
import Sobre from "./pages/Sobre";
import CentralDeAjuda from "./pages/CentralDeAjuda";
import TermosDeUso from "./pages/TermosDeUso";
import TrabalheConosco from "./pages/TrabalheConosco";
 

function App() {
  return (
    <BrowserRouter>
    <Header />
      <Routes>
        <Route path="/" element={<Home />}/>
        <Route path="/destino" element={<Destino />}/>
        <Route path="/promocoes" element={<Promocoes />}/>
        <Route path="/contato" element={<Contato />}/>
        <Route path="/sobre" element={<Sobre />}/>
        <Route path="/central-de-ajuda" element={<CentralDeAjuda />}/>
        <Route path="/termos-de-uso" element={<TermosDeUso />}/>
        <Route path="/trabalhe-conosco" element={<TrabalheConosco />}/>
      
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
