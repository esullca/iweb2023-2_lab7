package com.example.webapphr1_2023.Beans;

public class Department {

    private int departmentId;
    private String departmentName;
    private Employee manager;
    private Location location;

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the managerId
     */


    /**
     * @param manager the managerId to set
     */


    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the locationId to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    // ahora con los nuevos
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
