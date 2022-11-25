import React from 'react'
import "./assests/destino.css"
const Destino = () => {
    document.title =  "Agência VouSuave | Destino"

    let [passagem, setPassagem] = React.useState(null)

    React.useEffect(() => {
        const fetchPassagens = async (url) => {
            try {
                const response = await fetch(url)
                const json = await response.json()
                setPassagem(json)
            } catch (error) {
                console.log(error)
            }
        }

        fetchPassagens("http://localhost:8080/api/passagens")
    }, [])

    if (passagem === null) return null
    return (
        <main>
            <a href="#" id='arrow-top'><svg xmlns="http://www.w3.org/2000/svg" width="38" height="38" fill="currentColor" className="bi bi-arrow-up-circle-fill" viewBox="0 0 16 16">
                <path d="M16 8A8 8 0 1 0 0 8a8 8 0 0 0 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z" />
                </svg>
            </a>
            <section id='bg-destino'></section>
            <div className="card mx-5 my-5 text-center ">
                <div className="card-header bg-info">
                    Aproveite
                </div>
                <div className="card-body">
                    <h5 className="card-title">Tem Milhas Sobrando?</h5>
                    <p className="card-text">Transforme suas milhas em desconto.</p>
                    <a href="https://hotmilhas.com.br/?utm_source=123Milhas&utm_medium=MenuPrincipal&utm_campaign=Capa" target="_blank" className="btn btn-info">Ver desconto</a>
                </div>
            </div>
            <hr />
            <section className='container my-5'>
                <div className="text-center">
                    <i className="fa-solid fa-plane-departure"></i>
                    <h2 className="h3 d-inline" id="avião">Passagens Aéreas</h2>
                    <p className="text-muted">Saindo do Rio de Janeiro</p>
                    <h3 className="h2 mb-5">Voos com preços imperdíveis</h3>
                </div>
                <div className="row row-cols-1 row-cols-md-3 g-4">
                    {passagem.map(p => {
                        return p.tiposPassagem === "AVIAO" && <div>
                            <div key={p.id}>
                                <div className="">
                                    <div className="card rounded-5 shadow-blue">
                                        <img src={p.caminhoImagem} className="card-img-top rounded-4" alt="Imagem Salvador" />
                                        <div className="card-body">
                                            <h5 className="card-title">{p.destino}</h5>
                                            <p className="card-text mb-1 fs-5">Agende sua viagem para {p.destino} agora mesmo</p>
                                            <p className="mt-3 mb-0">Passagens a partir de</p>
                                            <p className="mb-0 mt-1 text-decoration-line-through">R${p.preco}</p>
                                            <p className="h4 text-danger">R$<strong>{p.preco * (1 - p.desconto)}</strong></p>
                                            <hr />
                                            <a className="btn btn-info mb-2" href="#" role="button">Conferir</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    })}
                </div>
            </section>
            <section className='container'>
                <div className="text-center">
                    <i className="fa-solid fa-plane-departure"></i>
                    <h2 className="h3 d-inline" id="avião">Passagens De Ônibus</h2>
                    <p className="text-muted">Saindo do Rio de Janeiro</p>
                    <h3 className="h2 mb-5">Passagens com preços imperdíveis</h3>
                </div>
                <div className="row row-cols-1 row-cols-md-3 g-4">
                    {passagem.map(p => {
                        return p.tiposPassagem === "ONIBUS" && <div>
                            <div key={p.id}>
                                <div className="">
                                    <div className="card rounded-5 shadow-blue">
                                        <img src={p.caminhoImagem} className="card-img-top rounded-4" alt="Imagem Salvador" />
                                        <div className="card-body">
                                            <h5 className="card-title">{p.destino}</h5>
                                            <p className="card-text mb-1 fs-5">Agende sua viagem para {p.destino} agora mesmo</p>
                                            <p className="mt-3 mb-0">Passagens a partir de</p>
                                            <p className="mb-0 mt-1 text-decoration-line-through">R${p.preco}</p>
                                            <p className="h4 text-danger">R$<strong>{Math.round(p.preco * (1 - p.desconto))}</strong></p>
                                            <hr />
                                            <a className="btn btn-info mb-2" href="#" role="button">Conferir</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    })}
                </div>
            </section>
            <section className="container mt-5 pt-5" id="cruzeiro">
                <div className="text-center">
                    <i className="fa-solid fa-anchor"></i>
                    <h2 className="h3 d-inline" id="cruzeiro">Cruzeiros</h2>
                    <p className="text-muted">All Inclusive</p>
                    <h3 className="h2 mb-5 pb-5">Cruzeiros com preços imperdíveis</h3>
                </div>

                {passagem.map(p => {
                    return p.tiposPassagem === "CRUZEIRO" && <div className="card mb-3 rounded-5">
                        <div className="row g-0">
                            <div className="col-md-4">
                                <img src={p.caminhoImagem} className="img-fluid rounded-start h-100" alt="..." />
                            </div>
                            <div className="col-md-4">
                                <div className="card-body">
                                    <h3 className="card-title d-inline">América do Sul,</h3>
                                    <p className="card-text d-inline fs-5">3 Noites</p>
                                    <p className="card-text mt-5 mb-0">Ponto de Embarque: {p.origem}</p>
                                    <p className="card-text mb-5">Ponto de Desembarque: {p.destino}</p>
                                    <p>Datas Disponiveis:</p>
                                    <p className="d-inline bg-info mx-2">{p.disponibilidade}</p>
                                    <a className="btn btn-info" href="#" role="button">Itinerário</a>
                                </div>
                            </div>
                            <div className="col-md-4 bg-info rounded-5">
                                <div className="card-body text-center">
                                    <p className="card-title fs-5">Primeiro hóspede a partir de</p>
                                    <p className="h3 card-text d-inline">R${Math.round(p.preco * (1 - p.desconto))}</p>
                                    <p className="card-text d-inline">*p.p</p>
                                    <p className="mt-5">Em até 12x de <b>R${Math.round(p.preco / 12)}</b></p>
                                    <a className="btn btn-outline-light mt-5" href="#" role="button">Reservar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                })}

            </section>
        </main>
    )
}

export default Destino