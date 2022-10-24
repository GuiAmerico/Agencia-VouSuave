import React from 'react'

const CentralDeAjuda = () => {
    document.title =  "Agência VouSuave | Central de Ajuda"

    return (
        <main className="container mt-5">
            <h1 className="container h3 pt-5">Central de Ajuda Ao Cliente!</h1>
            <p>Muito bem, deixe seu email e sua dúvida e o respoderemos o mais breve possivel!</p>
            <div className="mb-3">
                <label for="exampleFormControlInput1" className="form-label">Digite seu email</label>
                <input type="email" className="form-control w-50" id="exampleFormControlInput1" placeholder="nome@exemplo.com" />
            </div>
            <div className="mb-3 w-50">
                <label for="exampleFormControlTextarea1" className="form-label">Escreva sua dúvida</label>
                <textarea className="form-control" id="exampleFormControlTextarea1" rows="6"></textarea>
            </div>
            <input className="btn btn-info" type="submit" value="Enviar" />
        </main>
    )
}

export default CentralDeAjuda