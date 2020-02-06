package br.com.dayhan.cursomc.services.validation.utils;

import java.util.Arrays;
import java.util.List;

public class CNP {

	private CNP() {
		throw new IllegalStateException("Utility class");
	}

	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final List<String> invalidCPFs = Arrays.asList("11111111111", "22222222222", "33333333333", 
			"44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999", "00000000000");

	private static int calcularDigito(String str, int[] peso) {
		var soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCPF(String str) {
		String cpf = str.replaceAll("[^\\d]", "");
		if (cpf.length() != 11 || invalidCPFs.contains(cpf)) {
			return false;
		}

		int digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + "" + digito1 + "" + digito2);
	}

	public static boolean isValidCNPJ(String str) {
		String cnpj = str.replaceAll("[^\\d]", "");
		if (cnpj.length() != 14)
			return false;

		int digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		int digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + "" + digito1 + "" + digito2);
	}
}
