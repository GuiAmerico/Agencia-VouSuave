import React from 'react'
import "./assets/promocoes.css"

const Promocoes = () => {
    document.title =  "Agência VouSuave | Promoções"

    let [pacote, setPacote] = React.useState(null);

    React.useEffect(() => {
        const fetchPacote = async (url) => {
            try {
                const response = await fetch(url);
                const json = await response.json();
                setPacote(json);
            } catch (error) {
                console.log(error)
            }

        }
        fetchPacote("http://localhost:8080/api/pacotes");
    }, [])
    if (pacote === null) return null
    return (
        <main className='mb-5'>
            <a href="#" id='arrow-top'><svg xmlns="http://www.w3.org/2000/svg" width="38" height="38" fill="currentColor" className="bi bi-arrow-up-circle-fill" viewBox="0 0 16 16">
                <path d="M16 8A8 8 0 1 0 0 8a8 8 0 0 0 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z" />
                </svg>
            </a>
            <section id='bg-promocoes'></section>
            <h1 className='text-center bg-info text-white p-4'>PROMOÇÕES VÁLIDAS ATÉ 01/11</h1>
            <section className='container' >
                <div className="container mt-5 mb-3 py-5 row shadow-lg rounded-5">
                    <h2 className="text-center mb-5" id="nacionais">Pacotes Nacionais <i className="fa-solid fa-suitcase"></i></h2>
                    {pacote.map(p => {
                        return p.internacional === false && <div key={p.id} className="card text-bg-info mb-3 mx-2 rounded-5" style={{ maxWidth: "21rem" }}>
                            <img src={p.caminhoImagem} className="img-fluid rounded-5" alt={"Imagem " + p.destino} />
                            <div className="card-header"></div>
                            <div className="card-body">
                                <h5 className="card-title">{p.destino}</h5>
                                <p className="card-text">Voo + Hotel</p>
                                <p><i className="bi bi-calendar"></i> {p.diarias} Diárias</p>
                                <p><i className="bi bi-check-circle-fill"></i> Quarto + Café da Manhã</p>
                                <hr className="container" />
                                <p className="d-inline">A partir de <b>R${p.preco}</b></p>
                                <button className="btn btn-light d-inline ms-4">Agendar</button>
                            </div>
                        </div>
                    })}
                </div>
            </section>
            <section className='container'>
                <div className="container mt-5 mb-3 py-5 row shadow-lg rounded-5">
                    <h2 className="text-center mb-5" id="internacionais">Pacotes Internacionais <i className="fa-solid fa-suitcase"></i></h2>
                    {pacote.map(p => {
                        return p.internacional === true && <div key={p.id} className="card text-bg-info mb-3 mx-2 rounded-5" style={{ maxWidth: "21rem" }}>
                            <img src={p.caminhoImagem} className="img-fluid rounded-5" alt={"Imagem " + p.destino} />
                            <div className="card-header"></div>
                            <div className="card-body">
                                <h5 className="card-title">{p.destino}</h5>
                                <p className="card-text">Voo + Hotel</p>
                                <p><i className="bi bi-calendar"></i> {p.diarias} Diárias</p>
                                <p><i className="bi bi-check-circle-fill"></i> Quarto + Café da Manhã</p>
                                <p><i className="bi bi-translate"></i> Tradutor + Passeio</p>
                                <hr className="container" />
                                <p className="d-inline">A partir de <b>R${p.preco}</b></p>
                                <button className="btn btn-light d-inline ms-4">Agendar</button>
                            </div>
                        </div>
                    })}
                </div>
            </section>
        </main>
    )
}

export default Promocoes
