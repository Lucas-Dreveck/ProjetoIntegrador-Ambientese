    package com.Ambientese.Empresa.DTO;

    import com.Ambientese.Empresa.Model.EnderecoModel;
    import com.Ambientese.Empresa.Model.Enums.PorteEnum;

    public class EmpresaRequest {

        private String nomeFantasia;
        private String nomeSolicitante;
        private String telefoneSolicitante;
        private String razaoSocial;
        private String cnpj;
        private String inscricaoSocial;
        private String email;
        private String telefoneEmpresas;
        private String ramo;
        private PorteEnum porteEmpresas;
        private EnderecoModel enderecoModel;

        // Getters e Setters

        public String getNomeFantasia() {
            return nomeFantasia;
        }

        public void setNomeFantasia(String nomeFantasia) {
            this.nomeFantasia = nomeFantasia;
        }

        public String getNomeSolicitante() {
            return nomeSolicitante;
        }

        public void setNomeSolicitante(String nomeSolicitante) {
            this.nomeSolicitante = nomeSolicitante;
        }

        public String getTelefoneSolicitante() {
            return telefoneSolicitante;
        }

        public void setTelefoneSolicitante(String telefoneSolicitante) {
            this.telefoneSolicitante = telefoneSolicitante;
        }

        public String getRazaoSocial() {
            return razaoSocial;
        }

        public void setRazaoSocial(String razaoSocial) {
            this.razaoSocial = razaoSocial;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getInscricaoSocial() {
            return inscricaoSocial;
        }

        public void setInscricaoSocial(String inscricaoSocial) {
            this.inscricaoSocial = inscricaoSocial;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefoneEmpresas() {
            return telefoneEmpresas;
        }

        public void setTelefoneEmpresas(String telefoneEmpresas) {
            this.telefoneEmpresas = telefoneEmpresas;
        }

        public String getRamo() {
            return ramo;
        }

        public void setRamo(String ramo) {
            this.ramo = ramo;
        }

        public PorteEnum getPorteEmpresas() {
            return porteEmpresas;
        }

        public void setPorteEmpresas(PorteEnum porteEmpresas) {
            this.porteEmpresas = porteEmpresas;
        }

        public EnderecoModel getEndereco() {
            return enderecoModel;
        }

        public void setEndereco(EnderecoModel enderecoModel) {
            this.enderecoModel = enderecoModel;
        }
    }
