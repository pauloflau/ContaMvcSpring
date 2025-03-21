<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Contas - EDICAO</title>

<!-- Folha de estilos CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

<link th:href="@{/css/style.css}" rel="stylesheet" />

</head>
<body>

	<!-- chama no formato thymeleaf a pagina menu.jsp -->
	<div th:replace="admin/components/menu :: menu"></div>

	<div class="container mt-3">
		<h4>Edicao de Contas</h4>
		<p>Utilize o formulario para modificar os dados da conta selecionada.</p>
	</div>
	
	<!-- pego o atributo que criei no controlador-->
	<h5 th:if="${mensagem != null}" th:text="${mensagem}"
		th:class="${mensagemTipo == 'erro' ? 'erro' : 'sucesso'}"></h5>

	<form class="mt-2 mb-2" id="formEdicao" action="edicao-contas-post" method="post">

		<!-- campo oculto (armazena o id do campo da conta que quero editar) -->
		<!-- ai quando eu for alterar eu sei qual e o id -->
		<input type="hidden" th:value="${conta.id}" id="idConta" name="idConta"/>
 
		<div class="row mb-2">
			<div class="col">
				<label>Nome da conta:</label> 
				<!-- dentro de um input eu devo usar o th com o value-->
				<input class="form-control" th:value="${conta.nome}" id="nome" name="nome" /> 
			</div>

			<div class="col">
				<label>Data da conta:</label> 
				<input type="date" class="form-control" id="data" name="data" th:value="${#dates.format(conta.data, 'yyyy-MM-dd')}" />
			</div>

			<div class="col">
				<label>Valor da conta:</label> 
				<div class="input-group">
        			<span class="input-group-text">R$</span>
					<input type="number" min="1.00" step="0.010" class="form-control" id="valor" name="valor" th:value="${conta.valor}" />
        		</div>
			</div>

			<div class="col">
			    <label>Tipo da conta:</label>
			    <select class="form-select" id="tipo" name="tipo">
			        <option value="">Selecione uma opcao</option>
			        <option value="1" th:selected="${conta.tipo == 1}">Conta a receber</option>
			        <option value="2" th:selected="${conta.tipo == 2}">Conta a pagar</option>
			    </select>
			</div>
		</div>

		<div class="row mb-2">
			<div class="col">
				<label>Descricao / observacoes da conta:</label>
				<!-- campo textarea eu devo usar o th com um text -->
				<textarea class="form-control" id="descricao" name="descricao" th:text="${conta.descricao}" ></textarea>
			</div>
		</div>

		<div class="row mb-2">
			<div class="col">
				<input type="submit" class="btn btn-primary" value="Salvar alteracoes" /> 
				<a href="/contas/admin/consulta-contas" class="btn btn-light">Ir para a consulta</a>
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
		$("#formEdicao").validate({
			rules: {			
				"nome" : {
					required: true
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
					required: true,
					minlength: 4,
					maxlength: 500
				}
			}
		});			
	});
</script>


</body>
</html>