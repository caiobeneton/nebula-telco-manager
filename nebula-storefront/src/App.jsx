import { useState, useEffect } from 'react'
import './App.css'

function App() {
  // 1. Estado: Onde guardamos a mensagem que vem do Java
  // Começa com "Carregando..." até o Java responder
  const [mensagem, setMensagem] = useState('Carregando sistema...')

  // 2. Efeito: Roda assim que a tela abre (o "OnLoad" do React)
  useEffect(() => {
    // Chama o seu Backend
    fetch('http://localhost:8080/api/hello')
      .then(response => response.text()) // Converte a resposta pra texto
      .then(data => {
        setMensagem(data) // Guarda o texto no estado
      })
      .catch(error => {
        console.error("Erro ao conectar:", error)
        setMensagem("Erro: O Backend parece estar offline 🔴")
      })
  }, [])

  // 3. O HTML que aparece na tela
  return (
    <div className="container">
      <h1>Nebula Storefront 🌌</h1>
      <div className="card">
        <h2>Status do Sistema:</h2>
        {/* Aqui mostramos a variável 'mensagem' */}
        <p className="status-message">{mensagem}</p>
      </div>
    </div>
  )
}

export default App