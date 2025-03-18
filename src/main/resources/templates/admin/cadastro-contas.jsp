<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Contas - CADASTRO</title>

<!-- Folha de estilos CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

<link th:href="@{/css/style.css}" rel="stylesheet" />

</head>
<body>

	<!-- chama no formato thymeleaf a pagina menu.jsp -->
	<div th:replace="admin/components/menu :: menu"></div>

	<div class="container mt-3">
		<h4>Cadastro de Contas</h4>
		<p>Preencha os campos para incluir uma conta a pagar ou receber.</p>
	</div>

	<!-- pego o atributo que criei no controlador-->
	<h5 th:if="${mensagem != null}" 
		th:text="${mensagem}" th:class="${mensagemTipo == 'erro' ? 'erro' : 'sucesso'}"></h5>
		
	<form id="formCadastro" action="cadastro-contas-post" method="post" class="mt-2 mb-2">
		<div class="row mb-2">
			<div class="col">
				<label>Nome da conta:</label> <input type="text" id="nome" name="nome" class="form-control" />
			</div>
			<div class="col">
				<label>Data da conta:</label> <input type="date" id="data" name="data" class="form-control" />
			</div>
			<div class="col">
				<label>Valor da conta:</label> <input type="text" id="valor" name="valor" class="form-control" />
			</div>

			<div class="col">
				<label>Tipo da conta:</label> 
				<select class="form-select" id="tipo" name="tipo">
					<option value="">Selecione uma opcao</option>
					<option value="1">Conta a receber</option>
					<option value="2">Conta a pagar</option>
				</select>
			</div>
		</div>

		<div class="row mb-2">
			<div class="col">
				<label>Descricao / observacoes da conta:</label>
				<textarea class="form-control" id="descricao" name="descricao"></textarea>
			</div>
		</div>

		<div class="row mb-2">
			<div class="col">
				<input type="submit" class="btn btn-success" value="Realizar Cadastro" /> 
					<a href="/contas/admin/consulta-contas" class="btn btn-light">
						Ir para a consulta
					</a>
			</div>

		</div>
		</form>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"> </script>
		
<!-- JQuery -->	
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	
<!--  JQuery Validation -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.21.0/localization/messages_pt_BR.min.js"></script>

<script>

//executando quando a página abrir..
	$(document).ready(function(){ 
		$("#formCadastro").validate({
			rules: {			
				"nome" : {
					required: true,
					minlength: 4,
					maxlength: 200
				},
				"data" : {
					required: true
				},
				"valor" : {
					required: true,
					min: 1
				},
				"tipo" : {
					required: true
				},
				"descricao" : {
					required : true,
					minlength: 4,
					maxlength: 500
				}
			}
		});			
	});
</script>
</body>
</html>