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


	<form class="mt-2 mb-2">

		<div class="row mb-2">
			<div class="col">
				<label>Nome da conta:</label> 
				<!-- dentro de um input eu devo usar o th com o value -->
				<input class="form-control" th:value="${conta.nome}" />
			</div>

			<div class="col">
				<label>Data da conta:</label> 
				<input class="form-control" th:value="${#dates.format(conta.data, 'dd/MM/yyyy')}" />
			</div>

			<div class="col">
				<label>Valor da conta:</label> 
				<input class="form-control" th:value="${#numbers.formatCurrency(conta.valor)}" />
			</div>

<div class="col">
    <label>Tipo da conta:</label>
    <select class="form-select">
        <option value="">Selecione uma opção</option>
        <option value="1" th:selected="${conta.tipo == 1}">Conta a receber</option>
        <option value="2" th:selected="${conta.tipo == 2}">Conta a pagar</option>
    </select>
</div>
		</div>

		<div class="row mb-2">
			<div class="col">
				<label>Descricao / observacoes da conta:</label>
				<!-- campo textarea eu devo usar o th com um text -->
				<textarea class="form-control" th:text="${conta.descricao}" ></textarea>
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
</body>
</html>