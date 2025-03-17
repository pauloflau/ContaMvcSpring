<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Contas - Autenticar</title> 


<!-- Folha de estilos CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />


<link th:href="@{/css/style.css}" rel="stylesheet" />

</head>
<body class="bg-secondary">

	<div class="row mt-5">
		<div class="col-md-4 offset-md-4">

			<div class="card">
				<div class="card-body text-center">

					<div class="mt-2">
					    <img th:src="@{/images/logo.png}" alt="Logo JMP">
					    <br><br>
					</div>

					<h4>Sistema de controle de contas</h4>
					<p>Autenticacao de usuarios.</p>
					<hr />

					<h5 class="text-danger" th:text="${mensagem_erro}"></h5>
							
					<p>Entre com suas credenciais de acesso:</p>

					<form id="formAutenticar" class="text-start" action="/contas/autenticar-post" method="post">

						<div class="mt-2">
							<label>Entre com seu email:</label> 
							<input type="text" id="email" name="email" class="form-control" placeholder="Digite o email aqui." />
						</div>

						<div class="mt-2">
							<label>Entre com sua senha:</label> 
							<input type="password" id="senha" name="senha" class="form-control" placeholder="Digite a senha aqui." />
						</div>

						<div class="mt-2 d-grid">
							<input type="submit" value="Acessar Sistema" class="btn btn-primary" />
						</div>

						<div class="mt-2 d-grid">
							<a href="/contas/criar-usuario" class="btn btn-light">
								Nao	possui conta? <strong>Cadastre-se aqui!</strong>
							</a>

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
	$("#formAutenticar").validate({
		rules: {
			
			"email" : {
				required: true,
				email: true
			},
			"senha" : {
				required : true,
				minlength: 3,
				maxlength: 20
			}
		}
	});			
});
</script>
</body>
</html>