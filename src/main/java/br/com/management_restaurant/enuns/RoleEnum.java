package br.com.management_restaurant.enuns;

public enum RoleEnum {
    ADMINISTRADOR(1, "ROLE_ADMINISTRADOR"),
    GERENTE(2, "ROLE_GERENTE"),
    FINACEIRO(3, "ROLE_FINANCEIRO"),
    GARCO(4, "ROLE_GARCOM"),
    CAIXA(5, "ROLE_CAIXA"),
    ESTOQUISTA(6, "ROLE_ESTOQUISTA"),
    PRODUCAO(7, "ROLE_PRODUCAO"),
    USER(8, "ROLE_USER");

    private int id;
    private String role;

    RoleEnum(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public static String getRoles(String value){
        if (value == null || value.isBlank()){
            return RoleEnum.CAIXA.getRole();
        }
        for (RoleEnum role: RoleEnum.values()){
            if(role.getRole().equals(value)){
                return role.getRole();
            }
        }
        return RoleEnum.CAIXA.getRole();
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}


