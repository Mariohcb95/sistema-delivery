package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	FotoRecuperada recuperar(String nomeArquivo);

	void armazenar(NovaFoto novaFoto);

	void remover(String nomeArquivo);

	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

	@Builder
	@Getter
	class NovaFoto {
		private String nomeArquivo;
		private String contentType;
		private InputStream inputStream;
		
		public String getNomeArquivo() {
			return nomeArquivo;
		}
		public void setNomeArquivo(String nomeArquivo) {
			this.nomeArquivo = nomeArquivo;
		}
		public String getContentType() {
			return contentType;
		}
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		public InputStream getInputStream() {
			return inputStream;
		}
		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		
		
	}

	@Builder
	@Getter
	class FotoRecuperada {
		private InputStream inputStream;
		private String url;

		public boolean temUrl() {
			return url != null;
		}

		public boolean temInputStream() {
			return inputStream != null;
		}
	}
}
