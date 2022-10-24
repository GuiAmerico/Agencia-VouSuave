import React from 'react'

const TrabalheConosco = () => {
    document.title =  "Agência VouSuave | Trabalhe Conosco"
    
    return (
        <main className='container mb-5'>
            <div className="container">
                <h1 className="h3 pt-5">QUER TRABALHAR NA MELHOR AGÊNCIA DE VIAGENS?</h1>
                <h2 className="h5">Estamos Contrando:</h2>
                <ul>
                    <li>Desenvolvedor Back-End</li>
                    <li>Desenvolvedor Front-End</li>
                    <li>Desenvolvedor Full-Stack</li>
                    <li>Designer UX/UI</li>
                    <li>Analista de Dados</li>
                    <li>Copywriting</li>
                    <li>Gestor de Trafego</li>
                </ul>
                <div className="input-group w-50 mt-5">
                    <span className="input-group-text">Nome e Sobrenome</span>
                    <input type="text" aria-label="First name" className="form-control" />
                    <input type="text" aria-label="Last name" className="form-control" />
                </div>
                <div className="mb-3 w-50">
                    <label for="exampleFormControlInput1" className="form-label"></label>
                    <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="Digite seu email" />
                </div>
                <div className="input-group mb-3 w-50">
                    <label className="input-group-text" for="inputGroupFile01">Anexe seu currículo</label>
                    <input type="file" className="form-control" id="inputGroupFile01" />
                </div>
                <input className="btn btn-info" type="submit" value="Enviar" />
            </div>
        </main>
    )
}

export default TrabalheConosco