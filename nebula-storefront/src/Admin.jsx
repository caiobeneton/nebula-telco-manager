import { useState, useEffect } from 'react'
import './App.css'

function Admin() {
  // Estado do Formulário
  const [formData, setFormData] = useState({
    name: '', description: '', price: '', technicalSpec: ''
  })

  // Estado da Lista de Pedidos
  const [pedidos, setPedidos] = useState([])

  // Carrega os pedidos assim que a tela abre
  useEffect(() => {
    carregarPedidos()
  }, [])

  const carregarPedidos = () => {
    fetch('http://localhost:8080/api/orders')
      .then(res => res.json())
      .then(data => setPedidos(data))
  }

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value })
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    const payload = { ...formData, price: parseFloat(formData.price) }

    fetch('http://localhost:8080/api/products', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    })
    .then(response => {
      if (response.ok) {
        alert("✅ Produto criado!")
        setFormData({ name: '', description: '', price: '', technicalSpec: '' })
      }
    })
  }

  // A Lógica do Cancelamento 🚫
  const handleCancelar = (id) => {
    if(!window.confirm("Tem certeza que deseja cortar a internet deste cliente?")) return;

    fetch(`http://localhost:8080/api/orders/${id}/cancel`, {
      method: 'PUT'
    })
    .then(() => {
      alert("🚫 Serviço cancelado com sucesso.")
      carregarPedidos() // Recarrega a lista para mostrar o status novo
    })
  }

  return (
    <div className="container">
      <h1>Painel BSS 🔒</h1>

      {/* --- CADASTRO DE PRODUTOS --- */}
      <div className="product-card" style={{ padding: '20px', marginBottom: '40px' }}>
        <h2>Novo Produto</h2>
        <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '10px', flexDirection: 'column' }}>
          <input name="name" value={formData.name} onChange={handleChange} placeholder="Nome" style={{padding: '10px'}} />
          <input name="description" value={formData.description} onChange={handleChange} placeholder="Descrição" style={{padding: '10px'}} />
          <input name="price" value={formData.price} onChange={handleChange} placeholder="Preço" style={{padding: '10px'}} />
          <input name="technicalSpec" value={formData.technicalSpec} onChange={handleChange} placeholder="Spec Técnica" style={{padding: '10px'}} />
          <button type="submit" className="buy-btn">Cadastrar</button>
        </form>
      </div>

      {/* --- GESTÃO DE ASSINANTES --- */}
      <h2>Base de Assinantes</h2>
      <div style={{ display: 'grid', gap: '10px' }}>
        {pedidos.map(pedido => (
          <div key={pedido.id} className="product-card" style={{
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
            padding: '20px',
            borderLeft: pedido.status === 'CANCELED' ? '5px solid red' : '5px solid #00ff88'
          }}>
            <div style={{ textAlign: 'left' }}>
              <h3>{pedido.customerName}</h3>
              <p style={{ color: '#888' }}>{pedido.product ? pedido.product.name : 'Produto Antigo'}</p>
              <small>{pedido.customerEmail}</small>
            </div>

            <div style={{ textAlign: 'right' }}>
              <span className="badge" style={{
                backgroundColor: pedido.status === 'CANCELED' ? '#ff4444' : '#00ff88',
                color: 'black'
              }}>
                {pedido.status || 'ACTIVE'}
              </span>

              {pedido.status !== 'CANCELED' && (
                <button
                  onClick={() => handleCancelar(pedido.id)}
                  style={{ display: 'block', marginTop: '10px', background: 'transparent', border: '1px solid #ff4444', color: '#ff4444', cursor: 'pointer', padding: '5px' }}
                >
                  Cancelar
                </button>
              )}
            </div>
          </div>
        ))}
      </div>

      <br/>
      <a href="/" style={{ color: '#00ff88' }}>⬅ Voltar para a Loja</a>
    </div>
  )
}

export default Admin