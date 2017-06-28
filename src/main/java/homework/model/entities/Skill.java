package homework.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "skill")
    private String skill;

    public Skill() {
    }

    public Skill(String skill) {
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                '}';
    }
}
