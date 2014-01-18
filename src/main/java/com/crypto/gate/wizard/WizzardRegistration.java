package com.crypto.gate.wizard;

import com.crypto.gate.rest.RestClient;
import org.primefaces.event.FlowEvent;
import sun.net.www.http.HttpClient;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import static com.crypto.gate.rest.RestClient.postRegistrationForm;

@ManagedBean
@SessionScoped
public class WizzardRegistration {

    private boolean skip;
    private RegistrationForm registrationForm;

    public WizzardRegistration() {
        registrationForm = new RegistrationForm();
    }


    public String onFlowProcess(FlowEvent event) {

        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public void save() {
        postRegistrationForm(registrationForm);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }
}
