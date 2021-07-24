SELECT empregados.cpf,
	empregados.enome,
	departamentos.dnome
FROM empregados
INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero
WHERE empregados.cpf NOT IN
		(SELECT empregados.cpf
			FROM empregados
			INNER JOIN trabalha ON trabalha.cpf_emp = empregados.cpf)
ORDER BY empregados.cpf