package com.example.model;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.String; 
import java.lang.String; 


@Entity
@Table(name = "datas")
public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "profession")
    private String profession;

	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setProfession(String profession) {this.profession = profession;}
	public String getProfession() {return profession;}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
}
