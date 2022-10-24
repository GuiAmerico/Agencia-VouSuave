import React from 'react'

const Footer = () => {
    return (
        <footer >
            <hr/>
            <table className="table text-center table-borderless mt-4">
                <thead>
                    <tr>
                        <th className="col-4">VouSuave</th>
                        <th className="col-4">Televendas</th>
                        <th className="col-4">Central de Atendimento</th>
                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <td><a className="text-dark text-decoration-none" href="/sobre">Sobre Nós</a></td>
                        <td>0800 0800</td>
                        <td>Suporte Pós Venda - 21 88888 9999</td>
                    </tr>
                    <tr>
                        <td><a href="/trabalhe-conosco" className="text-dark text-decoration-none">Trabalhe Conosco</a></td>
                        <td>Segunda a sexta: 09h às 21h</td>
                        <td>SAC: 21 99999 9999</td>
                    </tr>
                    <tr>
                        <td><a href="/termos-de-uso" className="text-dark text-decoration-none">Termos de Uso</a></td>
                        <td>Sábado: 09h às 21h</td>
                        <td><a href="/central-de-ajuda" className="text-dark text-decoration-none">Central de Ajuda</a></td>
                    </tr>
                </tbody>
            </table>
            <br /><br />
            <hr />
            <div className="container px-5 py-5" id="icon-grid">
                <div className="row g-0 px-5 py-5 ">
                    <div className="col d-flex align-items-start">
                        <div>
                            <a href="#" className="text-decoration-none text-dark">
                                <h4 className="fw-bold mb-0">Baixe Nosso App</h4>
                                <img src="https://cdn.discordapp.com/attachments/1028712344110514176/1033493910724554893/logo_icon.png" className="ms-4" alt="" />
                            </a>
                        </div>
                    </div>
                    <div className="col d-flex align-items-star" id="redesSociais">
                        <div>
                            <p className="fw-bold mb-0">Nos Siga Nas Redes Sociais</p>
                            <a href="#">
                                <img className="w-25" src="https://cdn.discordapp.com/attachments/1028712344110514176/1033492252112203806/iconFacebook.png" alt="link Facebook" />
                            </a>
                            <a href="#">
                                <img className="w-25" src="https://cdn.discordapp.com/attachments/1028712344110514176/1033492252594544720/iconInstagram.png" alt="link Twitter" />
                            </a>
                            <a href="#">
                                <img className="w-25" src="https://cdn.discordapp.com/attachments/1028712344110514176/1033492253026566224/iconTwitter.png" alt="link Instagram" />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div className="text-center">
                <h6 className="mt-5">Formas de Pagamento</h6>
                <img className="formaDePagamento" src="https://cdn.discordapp.com/attachments/1028712344110514176/1033492251550158858/formasDePagamento.png" alt="formas de pagamento" />
            </div>
        </footer>
    )
}

export default Footer