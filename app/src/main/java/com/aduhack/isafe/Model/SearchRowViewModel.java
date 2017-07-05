package com.aduhack.isafe.Model;

/**
 * Created by Jee.Gloria on 3/21/2015.
 */
public class SearchRowViewModel {

    private String id;
    private String location;
    private String details;
    private String detailsconcat;
    private String IncidentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetailsconcat() {
        return detailsconcat;
    }

    public void setDetailsconcat(String detailsconcat) {
        this.detailsconcat = detailsconcat;
    }

    public String getIncidentType() {
        return IncidentType;
    }

    public void setIncidentType(String incidentType) {
        IncidentType = incidentType;
    }
}
