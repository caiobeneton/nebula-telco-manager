import { useState, useEffect } from 'react'
import './App.css'

function App() {
  // Agora nosso estado é uma LISTA ([]) de produtos, não mais um texto
  const [produtos, setProdutos] = useState([])

  useEffect(() => {
    // 1. Chama a URL nova (/api/products)
    fetch('http://localhost:8080/api/products')
      // 2. IMPORTANTE: Avisa pro React que a resposta é JSON (não texto)
      .then(response => response.json())
      .then(data => {
        console.log("Produtos carregados:", data) // Mostra no console do navegador (F12) pra debug
        setProdutos(data)
      })
      .catch(error => console.error("Erro ao buscar produtos:", error))
  }, [])

  return (
    <div className="container">
      <h1>Nebula Store 🌌</h1>
      <p className="subtitle">Escolha o plano ideal para sua conexão intergaláctica.</p>

      <div className="product-grid">
        {/* Aqui acontece a Mágica: Para cada produto na lista, cria um Card */}
        {produtos.map(produto => (
          <div key={produto.id} className="product-card">
            <div className="card-header">
              <span className="badge">5G</span>
              <h2>{produto.name}</h2>
            </div>

            <div className="card-body">
              <p className="description">{produto.description}</p>
              <div className="price-tag">
                <span className="currency">R$</span>
                <span className="value">{produto.price.toFixed(2)}</span>
                <span className="period">/mês</span>
              </div>
              <button className="buy-btn">Assinar Agora</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default App