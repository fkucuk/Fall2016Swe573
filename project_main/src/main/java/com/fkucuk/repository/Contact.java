package com.fkucuk.repository;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

public class Contact {

    public int id;
    public String name;
    public List<Address> addresses;

    public Contact() {}

    public Contact(int id, String name, List<Address> addresses) {
        this.name = name;
        this.id = id;
        this.addresses =
                (addresses != null) ? new LinkedList<>(addresses) : null;
    }
}