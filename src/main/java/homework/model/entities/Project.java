package homework.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "company_id")
    private int companyId;
    @Column (name = "customer_id")
    private int customerId;
    @Column (name = "cost")
    private int cost;

    public Project() {
    }

    public Project(String name, int companyId, int customerId, int cost) {
        this.name = name;
        this.companyId = companyId;
        this.customerId = customerId;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                ", customerId=" + customerId +
                ", cost=" + cost +
                '}';
    }
}
