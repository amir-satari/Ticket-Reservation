package ir.maktabsharif.model.models;

import ir.maktabsharif.model.basemodel.BaseModel;

public class Customer extends BaseModel {
    private String name;
    private String phone_number;

    public Customer(Long id, String name, String phone_number) {
        super(id);
        this.name = name;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
