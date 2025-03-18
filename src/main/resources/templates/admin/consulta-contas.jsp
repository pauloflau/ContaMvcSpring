<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Contas - CONSULTA</title>

<!-- Folha de estilos CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />

<link th:href="@{/css/style.css}" rel="stylesheet" />

</head>
<body>

	<!-- chama no formato thymeleaf a pagina menu.jsp -->
	<div th:replace="admin/components/menu :: menu"></div>

	<div class="container mt-3">
		<h4>Consulta de Contas</h4>
		<p>Pesquise as contas cadastradas de acordo com um periodo de datas.</p>
	</div>
	
	<!-- pego o atributo que criei no controlador-->
	<h5 th:if="${mensagem != null}" 
		th:text="${mensagem}" th:class="${mensagemTipo == 'erro' ? 'erro' : 'sucesso'}"></h5>
		
	<p th:text="${contas}">

	<form id="formConsulta" action="consulta-contas-post" method="post" class="mt-2 mb-2">
		<div class="row">
			<div class="col-md-2">
				<label>Data de inicio:</label> <input type="date" id="dataMin" name="dataMin" class="form-control" />
			</div>
			<div class="col-md-2">
				<label>Data de fim:</label> <input type="date" id="dataMax" name="dataMax" class="form-control" />
			</div>
			<div class="col-md-8">
				<input type="submit" class="btn btn-success mt-4" value="Pesquisar contas" />
			</div>
		</div>
	</form>

	<div class="table-responsive mt-3">
		<table class="table table-sm">
			<thead>
				<tr>
					<th>Data</th>
					<th>Valor</th>
					<th>Tipo</th>
					<th>Nome</th>
					<th>Descricao</th>
					<th>Operacoes</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<a href="/contas/admin/edicao-contas" class="btn btn-outline-primary btn-sm me-2">Editar</a> 
						<a href="/contas/admin/exclusao-contas" class="btn btn-outline-danger btn-sm">Excluir</a>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">Quantidade de registros: 0</td>
				</tr>
			</tfoot>
		</table>
	</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- JQuery -->	
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	
<!--  JQuery Validation -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.21.0/localization/messages_pt_BR.min.js"></script>

<script>

//executando quando a página abrir..
	$(document).ready(function(){ 
		$("#formConsulta").validate({
			rules: {			
				"dataMin" : {
					required: true
				},
				"dataMax" : {
					required: true
				}
			}
		});			
	});
</script>
</body>
</html>