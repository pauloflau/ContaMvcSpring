<!-- src/main/resources/templates/admin/components/menu.html -->
<div th:fragment="menu">
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Controle Financeiro</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <!-- Link para o Dashboard -->
          <li class="nav-item">
            <a class="nav-link" href="/contas/admin/dashboard">Dashboard</a>
          </li>
          <!-- Link para Cadastrar Contas -->
          <li class="nav-item">
            <a class="nav-link" href="/contas/admin/cadastro-contas">Cadastrar Contas</a>
          </li>
          <!-- Link para Consultar Contas -->
          <li class="nav-item">
            <a class="nav-link" href="/contas/admin/consulta-contas">Consultar Contas</a>
          </li>
        </ul>
        <!-- Nome do Usuário e Botão de Sair -->
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <span class="navbar-text me-3">
                  <!-- Exibindo o nome do usuário ou uma mensagem padrão -->
                  <strong th:text="${usuario_auth != null ? usuario_auth.nome : 'Usuario Desconhecido'}"></strong>
                  |
                  <strong th:text="${usuario_auth != null ? usuario_auth.email : ''}"></strong>  
            </span>
          </li>
          <li class="nav-item">
            <a class="btn btn-outline-light" href="/contas/logout" 
            	onclick = "return confirm('Deseja realmente sair do sistema?');">Sair</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>