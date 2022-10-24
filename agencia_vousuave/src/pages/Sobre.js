import React from 'react'

const Sobre = () => {
    document.title =  "Agência VouSuave | Sobre"

    return (
        <main style={{marginBottom: "150px"}}>
            <article className="container mt-5">
                <div className="row">
                    <div className="col fs-4">
                        <h1 className="container">QUEM SOMOS</h1>
                        <p>Somos a agência de viagens VouSuave, uma agência especializada em viagens de qualidade e de muito conforto,
                            nossa empresa surgiu quando nos deparamos com uma dificuldade em conseguir viagens baratas e de qualidade,
                            então, decidi criar eu mesmo uma empresa que preza pelo conforto do cliente</p>

                        <p>Nossa missão é mostrar para as pessoas que qualquer um pode viajar, basta se organizar e criar
                            sua conta na VouSuave ;D.
                        </p>
                        <p>Aqui nós também trabalhamos com milhas, paras as pessoas que tem milhas sobrando, vão viajar e querem um desconto
                            ou até mesmo quer trocar por alguns produtos
                        </p>
                        <p>Para você que quer ganhar uma renda extra, venda alguma de nossas passagens ou pacotes e receba uma comissão!
                        </p>
                    </div>
                </div>
            </article>
        </main>
    )
}

export default Sobre