import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [produtos, setProdutos] = useState([])

  useEffect(() => {
    fetch('http://localhost:8080/api/products')
      .then(response => response.json())
      .then(data => setProdutos(data))
      .catch(error => console.error("Erro:", error))
  }, [])

  // NOVA FUNÇÃO: O Cérebro da Compra 🧠
  const handleAssinar = (produtoId, nomeProduto) => {
    // 1. Coletar dados do cliente (Simples e Rápido)
    const nome = window.prompt(`Você escolheu: ${nomeProduto}\n\nDigite seu NOME completo:`)
    if (!nome) return // Se cancelar, para tudo.

    const email = window.prompt("Agora digite seu EMAIL:")
    if (!email) return

    // 2. Montar o Pacote para o Correio (JSON)
    const pedido = {
      customerName: nome,
      customerEmail: email,
      productId: produtoId
    }

    // 3. Enviar para o Backend (POST)
    fetch('http://localhost:8080/api/orders', {
      method: 'POST', // "Estou ENVIANDO dados"
      headers: {
        'Content-Type': 'application/json' // "Estou mandando JSON"
      },
      body: JSON.stringify(pedido) // Transforma o objeto JS em texto JSON
    })
    .then(response => {
      if (response.ok) {
        alert("✅ Compra realizada com sucesso! Bem-vindo à Nebula.")
      } else {
        alert("❌ Erro ao processar compra. Tente novamente.")
      }
    })
    .catch(error => {
      console.error("Erro na compra:", error)
      alert("🔴 Erro de conexão com o servidor.")
    })
  }

  return (
    <div className="container">
      <h1>Nebula Store 🌌</h1>
      <p className="subtitle">Escolha o plano ideal para sua conexão intergaláctica.</p>

      <div className="product-grid">
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
              {/* O Botão agora tem vida! Chama a função handleAssinar */}
              <button
                className="buy-btn"
                onClick={() => handleAssinar(produto.id, produto.name)}
              >
                Assinar Agora
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default App