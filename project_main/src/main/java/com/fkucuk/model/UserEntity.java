package com.fkucuk.model;

import javax.persistence.*;

/**
 * Created by FATIH.KUCUK on 30.11.2016.
 */
@Entity
@Table(name = "User", schema = "", catalog = "")
public class UserEntity {
    private Integer userId;

    @Id
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private String name;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private float height;

    @Basic
    @Column(name = "height")
    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    private float weight;

    @Basic
    @Column(name = "weight")
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (height != null ? !height.equals(that.height) : that.height != null) return false;
//        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (height != null ? height.hashCode() : 0);
//        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
