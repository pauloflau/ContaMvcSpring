<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Contas - Criar usuario</title>

<!-- Folha de estilos CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />


<!-- CSS pintar as mensagens de erro e criar uma borda no campo onde tem um erro -->
<link th:href="@{/css/style.css}" rel="stylesheet" />


</head>
<body class="bg-secondary">

	<div class="row mt-3">
		<div class="col-md-4 offset-md-4">
			<div class="card">
				<div class="card-body text-center">

					<h4>Sistema de controle de contas</h4>
					<p>Cadastre o seu usuario.</p>
					<hr />
					<!-- pego o atributo que criei no controlador-->
					<h5 th:if="${mensagem != null}" 
						th:text="${mensagem}" th:class="${mensagemTipo == 'erro' ? 'erro' : 'sucesso'}"></h5>


					<form action="criar-usuario-post" method="post" id="formCadastro" class="text-start">

						<div class="mb-2">
							<label>Nome do usuario:</label> 
							<input type="text" id="nome" name="nome" 
								class="form-control" placeholder="Digite seu nome aqui" />
						</div>

						<div class="mb-2">
							<label>Email do usuario:</label> 
							<input type="text" id="email" name="email"
								class="form-control" placeholder="Digite seu email aqui" />
						</div>

						<div class="mb-2">
							<label>Senha do usuario:</label> 
							<input type="password" id="senha" name="senha" 
								class="form-control" placeholder="Digite sua senha aqui" />
						</div>

						<div class="mb-2">
							<label>Confirme sua senha:</label> 
							<input type="password"  id="senhaConfirmacao" name="senhaConfirmacao"
								class="form-control" placeholder="Confirme sua senha aqui" />
						</div>

						<div class="mb-2 d-grid">
							<input type="submit" class="btn btn-primary" value="Realizar Cadastro" />
						</div>

						<div class="mb-2 d-grid">
							<a href="/contas" class="btn btn-light">Voltar para a pagina inicial.</a>
							</div>

					</form>

				</div>
			</div>
		</div>
	</div>

	<!-- JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
		
	<!-- JQuery -->	
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	
	<!--  JQuery Validation -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.21.0/localization/messages_pt_BR.min.js"></script>
	
<script>
	//executando quando a página abrir..
$(document).ready(function(){ 

//pego o formulario pelo ID e uso a funcao validade(VALIDACAO)
	$("#formCadastro").validate({
		rules: {
			"nome" : {
				required : true,
				minlength: 4,
				maxlength: 150
			},
			"email" : {
				required: true,
				email: true
			},
			"senha" : {
				required : true,
				minlength: 3,
				maxlength: 20
			},
			"senhaConfirmacao" : {
				required: true,
				equalTo: "#senha"
			} 
		}
	});			
});
</script>

</body>
</html>