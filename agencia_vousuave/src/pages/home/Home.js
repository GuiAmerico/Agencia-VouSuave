import React from 'react'
import "./assests/home.css"
const Home = () => {
    document.title =  "Agência VouSuave | Home"

    return (
        <main>
            <section id='bg-home'>

            </section>
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
            <section className="container-fluid pt-5">
                <h3 className="container pt">
                    NOVIDADES!
                </h3>
                <hr />
                <div className="row row-cols-1 row-cols-md-3 g-4">
                    <div className="col">
                        <div className="card">
                            <img src="https://cdn.discordapp.com/attachments/1028712344110514176/1033487161984553000/cruzeiro.jpg" className="card-img-top" alt="imagem de cruzeiro" />
                            <div className="card-body">
                                <h5 className="card-title">Agora você pode viajar de cruzeiro também</h5>
                                <p className="card-text">A Agência de Viagens VouSuave Agora Conta Também Com Viagens de Cruzeiro!</p>
                            </div>
                            <a href="/destino#cruzeiro" target="_blank" className="btn btn-info w-50 buttomCards">Clique Aqui</a>
                        </div>
                    </div>

                    <div className="col">
                        <div className="card">
                            <img src="https://cdn.discordapp.com/attachments/1028712344110514176/1033487162630488154/ferias.jpg" className="card-img-top" alt="pacotes de viagens" />
                            <div className="card-body">
                                <h5 className="card-title">Pacotes com os melhores preços!</h5>
                                <p className="card-text">Escolha Já o Pacote de Viagens Para as Suas Próximas Férias.</p>
                            </div>
                            <a href="/promocoes" target="_blank" className="btn btn-info w-50 buttomCards">Clique Aqui</a>
                        </div>
                    </div>

                    <div className="col">
                        <div className="card">
                            <img src="https://cdn.discordapp.com/attachments/1028712344110514176/1033487163020546149/hoteis.jpg" className="card-img-top" alt="Hotel" />
                            <div className="card-body">
                                <h5 className="card-title">Os hotéis mais recomendados estão aqui.</h5>
                                <p className="card-text">Aqui Está a Hospedagem Que Você Precisava!</p>
                            </div>
                            <a href="/promocoes#nacionais" target="_blank" className="btn btn-info w-50 buttomCards mt-4">Clique Aqui</a>
                        </div>
                    </div>
                </div>
                <hr />

            </section>
            <div className='container'>
                <a href="#" class="text-decoration-none">
                    <div class="container my-5 py-4 bg-info text-white rounded-5 row">
                        <h5 class="container mt-5 col-3">App VouSuave já está no ar</h5>
                        <p className='col-3 mt-5'>Ganhe Cashback ao usar o aplicativo!</p>
                        <img class="w-25 col-3" src="https://cdn.discordapp.com/attachments/1028712344110514176/1033489413197537350/googlePlay.png" alt=""/>
                    </div>
                </a>

            </div>
            <br/>
        </main>
    )
}

export default Home