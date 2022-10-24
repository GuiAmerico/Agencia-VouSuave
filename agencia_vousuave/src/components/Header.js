import React from 'react'

const Header = () => {
    return (
        <header className="bg-info">
            <nav className="d-flex justify-content-center py-3">
                <ul className="nav nav-pills">
                    <li className="nav-item"><a href="/" className="nav-link text-white ">Home</a></li>
                    <li className="nav-item"><a href="/destino" className="nav-link text-white">Destino</a></li>
                    <li className="nav-item"><a href="/promocoes" className="nav-link text-white">Promoções</a></li>
                    <li className="nav-item"><a href="/contato" className="nav-link text-white">Contato</a></li>
                </ul>
            </nav>
        </header>
    )
}

export default Header